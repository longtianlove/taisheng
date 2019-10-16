package com.taisheng.now.bussiness.market;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.base.BaseFragment;
import com.taisheng.now.bussiness.bean.post.BaseListPostBean;
import com.taisheng.now.bussiness.bean.result.HotGoodsBean;
import com.taisheng.now.bussiness.bean.result.MallBannerBean;
import com.taisheng.now.bussiness.bean.result.MallBannerResultBanner;
import com.taisheng.now.bussiness.bean.result.MallYouhuiquanResultBanner;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.view.banner.BannerViewPager;
import com.taisheng.now.view.refresh.MaterialDesignPtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Response;

public class MarketFragment extends BaseFragment{

    View iv_gouwuche;
    EditText et_doctor_search;
    View iv_search_guanbi;
    View tv_search;

    MaterialDesignPtrFrameLayout ptr_refresh;

    private FrameLayout bannerContaner;
    BannerViewPager bannerViewPager;
    private View bannerView;

    public View tv_moreyouhuijuan;


    RecyclerView rv_hot_goods;
    RecyclerView.LayoutManager mLayoutManager;
    HotGoodsAdapter hotGoodsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_market, container, false);

        initView(rootView);


//        EventBus.getDefault().register(this);
        initData();

        return rootView;
    }

    void initView(View rootView) {
        iv_gouwuche = rootView.findViewById(R.id.iv_gouwuche);
        iv_gouwuche.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//todo 进入购物车

            }
        });

        iv_search_guanbi = rootView.findViewById(R.id.iv_search_guanbi);
        iv_search_guanbi.setVisibility(View.GONE);
        iv_search_guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_doctor_search.setText("");
            }
        });
        et_doctor_search = (EditText) rootView.findViewById(R.id.et_doctor_search);
        et_doctor_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    iv_search_guanbi.setVisibility(View.VISIBLE);
                } else {
                    iv_search_guanbi.setVisibility(View.GONE);
                }
                String searchkey = s.toString();
//                nickName = searchkey;
//                PAGE_NO = 1;
//                madapter.mData.clear();
//                getDoctors();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tv_search = rootView.findViewById(R.id.tv_search);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchkey = et_doctor_search.getText().toString();
//                nickName = searchkey;
//                PAGE_NO = 1;
//                madapter.mData.clear();
//                getDoctors();

            }
        });



        ptr_refresh = (MaterialDesignPtrFrameLayout) rootView.findViewById(R.id.ptr_refresh);
        /**
         * 下拉刷新
         */
        ptr_refresh.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                initData();
            }
        });
//

        bannerContaner = (FrameLayout) rootView.findViewById(R.id.bannerContaner);
        bannerContaner.setVisibility(View.VISIBLE);
        bannerViewPager = new BannerViewPager(mActivity);

        bannerView = bannerViewPager.getContentView();

        bannerContaner.addView(bannerView);


        tv_moreyouhuijuan=rootView.findViewById(R.id.tv_moreyouhuijuan);
        tv_moreyouhuijuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MoreYouhuijuanActivity.class);
                startActivity(intent);
            }
        });

        rv_hot_goods = rootView.findViewById(R.id.rv_hot_goods);
        //设置布局管理器为2列，纵向
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        hotGoodsAdapter = new HotGoodsAdapter(getActivity());
        rv_hot_goods.setLayoutManager(mLayoutManager);
        rv_hot_goods.setAdapter(hotGoodsAdapter);
    }

    void initData() {
        getBanner();
        getYouhuiquan();
    }


    public void getBanner() {
        BaseListPostBean bean = new BaseListPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.pageNo = 1;
        bean.pageSize = 5;
        ApiUtils.getApiService().banner(bean).enqueue(new TaiShengCallback<BaseBean<MallBannerResultBanner>>() {
            @Override
            public void onSuccess(Response<BaseBean<MallBannerResultBanner>> response, BaseBean<MallBannerResultBanner> message) {
                ptr_refresh.refreshComplete();
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        ArrayList<String> pictureUrls=new ArrayList<>();
                        if(message.result.records!=null&&!message.result.records.isEmpty()) {

                            for (MallBannerBean bean:
                            message.result.records) {
                                pictureUrls.add(bean.url);
                            }
                            bannerViewPager.setPictureUrls(pictureUrls);
                            bannerViewPager.setmScrollSpeed(500);
                            bannerViewPager.setOnItemClickListener(new BannerViewPager.ViewPagerItemListener() {
                                @Override
                                public void onViewPagerItemClick(int i) {

                                }
                            });
                            bannerViewPager.madapter.notifyDataSetChanged();
                        }

                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<MallBannerResultBanner>> call, Throwable t) {
                ptr_refresh.refreshComplete();
            }
        });
    }

    public void getYouhuiquan(){

        BaseListPostBean bean = new BaseListPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.pageNo = 1;
        bean.pageSize = 10;
        ApiUtils.getApiService().coupon(bean).enqueue(new TaiShengCallback<BaseBean<MallYouhuiquanResultBanner>>() {
            @Override
            public void onSuccess(Response<BaseBean<MallYouhuiquanResultBanner>> response, BaseBean<MallYouhuiquanResultBanner> message) {
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:

                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<MallYouhuiquanResultBanner>> call, Throwable t) {

            }
        });
    }




    public class HotGoodsAdapter extends RecyclerView.Adapter {

        private Context mContext;
        private List<HotGoodsBean> mData; //定义数据源

        //定义构造方法，默认传入上下文和数据源
        public HotGoodsAdapter(Context context) {
            mContext = context;
//            mData = data;
        }

        @Override  //将ItemView渲染进来，创建ViewHolder
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_goods, null);
            return new MyViewHolder(view);
        }

        @Override  //将数据源的数据绑定到相应控件上
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder holder2 = (MyViewHolder) holder;
            HotGoodsBean hotGoodsBean = mData.get(position);

            //todo 加字段
            if (hotGoodsBean != null) {
                Uri uri = Uri.parse(Constants.Url.File_Host);
                holder2.sdv_header.setImageURI(uri);
            }

        }

        @Override
        public int getItemCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }

        //定义自己的ViewHolder，将View的控件引用在成员变量上
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public SimpleDraweeView sdv_header;
            public TextView tv_goods_name;
            public TextView tv_goods_jiage;
            public View iv_jifennduihuan;


            public MyViewHolder(View itemView) {
                super(itemView);
                sdv_header = (SimpleDraweeView) itemView.findViewById(R.id.sdv_header);
                tv_goods_name = itemView.findViewById(R.id.tv_goods_name);
                tv_goods_jiage = itemView.findViewById(R.id.tv_goods_jiage);
                iv_jifennduihuan = itemView.findViewById(R.id.iv_jifennduihuan);


            }
        }
    }


}
