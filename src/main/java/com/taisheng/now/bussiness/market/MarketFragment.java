package com.taisheng.now.bussiness.market;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;
import com.taisheng.now.bussiness.bean.result.HotGoodsBean;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.view.banner.BannerViewPager;

import java.util.List;

public class MarketFragment extends BaseFragment {

    View iv_gouwuche;
    EditText et_doctor_search;
    View iv_search_guanbi;
    View tv_search;

    private FrameLayout bannerContaner;
    BannerViewPager bannerViewPager;
    private View bannerView;


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



        bannerContaner = (FrameLayout) rootView.findViewById(R.id.bannerContaner);
        bannerContaner.setVisibility(View.VISIBLE);
        bannerViewPager = new BannerViewPager(mActivity);
        bannerViewPager.setLocalPictureIds();
        bannerViewPager.setmScrollSpeed(500);
        bannerViewPager.madapter.notifyDataSetChanged();
        bannerView = bannerViewPager.getContentView();
        bannerViewPager.setOnItemClickListener(new BannerViewPager.ViewPagerItemListener() {
            @Override
            public void onViewPagerItemClick(int i) {

            }
        });
        bannerContaner.addView(bannerView);




        rv_hot_goods = rootView.findViewById(R.id.rv_hot_goods);
        //设置布局管理器为2列，纵向
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        hotGoodsAdapter = new HotGoodsAdapter(getActivity());
        rv_hot_goods.setLayoutManager(mLayoutManager);
        rv_hot_goods.setAdapter(hotGoodsAdapter);
    }

    void initData() {

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
                tv_goods_name=itemView.findViewById(R.id.tv_goods_name);
                tv_goods_jiage=itemView.findViewById(R.id.tv_goods_jiage);
                iv_jifennduihuan=itemView.findViewById(R.id.iv_jifennduihuan);



            }
        }
    }


}
