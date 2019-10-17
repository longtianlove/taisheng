package com.taisheng.now.bussiness.market;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import com.taisheng.now.bussiness.article.ArticleContentActivity;
import com.taisheng.now.bussiness.bean.post.BaseListPostBean;
import com.taisheng.now.bussiness.bean.post.BasePostBean;
import com.taisheng.now.bussiness.bean.result.ArticleBean;
import com.taisheng.now.bussiness.bean.result.CainixihuanResultBean;
import com.taisheng.now.bussiness.bean.result.HotGoodsBean;
import com.taisheng.now.bussiness.bean.result.JifenzhuanquBean;
import com.taisheng.now.bussiness.bean.result.MallBannerBean;
import com.taisheng.now.bussiness.bean.result.MallBannerResultBanner;
import com.taisheng.now.bussiness.bean.result.MallYouhuiquanResultBanner;
import com.taisheng.now.bussiness.bean.result.RemenshangpinBean;
import com.taisheng.now.bussiness.first.FirstFragment;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.view.WithScrolleViewListView;
import com.taisheng.now.view.banner.BannerViewPager;
import com.taisheng.now.view.refresh.MaterialDesignPtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Response;

public class MarketFragment extends BaseFragment {

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

    com.taisheng.now.view.WithScrolleViewListView lv_articles;
    ArticleAdapter madapter;


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
                Intent intent = new Intent(getActivity(), GouWuCheActivity.class);
                startActivity(intent);

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


        tv_moreyouhuijuan = rootView.findViewById(R.id.tv_moreyouhuijuan);
        tv_moreyouhuijuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoreYouhuijuanActivity.class);
                startActivity(intent);
            }
        });

        rv_hot_goods = rootView.findViewById(R.id.rv_hot_goods);
        //设置布局管理器为2列，纵向
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        hotGoodsAdapter = new HotGoodsAdapter(getActivity());
        rv_hot_goods.setLayoutManager(mLayoutManager);
        rv_hot_goods.setAdapter(hotGoodsAdapter);


        lv_articles = (WithScrolleViewListView) rootView.findViewById(R.id.lv_jifenduihuan);
        madapter = new ArticleAdapter(mActivity);
        lv_articles.setAdapter(madapter);
        initYouhuijuan(rootView);
    }

    void initData() {
        getBanner();
        getYouhuiquan();
        getHotGoodsJifenduihuan();
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
                        ArrayList<String> pictureUrls = new ArrayList<>();
                        if (message.result.records != null && !message.result.records.isEmpty()) {

                            for (MallBannerBean bean :
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



    public void initYouhuijuan(View rootView) {

    }

    public void getYouhuiquan() {

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

    public void getHotGoodsJifenduihuan() {
        BasePostBean bean = new BasePostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        ApiUtils.getApiService().cainixihuan(bean).enqueue(new TaiShengCallback<BaseBean<CainixihuanResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<CainixihuanResultBean>> response, BaseBean<CainixihuanResultBean> message) {
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        if (message.result.hotGoodsList != null && !message.result.hotGoodsList.isEmpty()) {
                            hotGoodsAdapter.mData = message.result.hotGoodsList;
                            hotGoodsAdapter.notifyDataSetChanged();
                        }


                        if (message.result.scoreList != null && !message.result.scoreList.isEmpty()) {
                            madapter.mData = message.result.scoreList;
                            madapter.notifyDataSetChanged();
                        }
                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<CainixihuanResultBean>> call, Throwable t) {

            }
        });
    }


    public class HotGoodsAdapter extends RecyclerView.Adapter {

        private Context mContext;
        public List<RemenshangpinBean> mData; //定义数据源

        //定义构造方法，默认传入上下文和数据源
        public HotGoodsAdapter(Context context) {
            mContext = context;

        }

        @Override  //将ItemView渲染进来，创建ViewHolder
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_goods, null);
            return new MyViewHolder(view);
        }

        @Override  //将数据源的数据绑定到相应控件上
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder holder2 = (MyViewHolder) holder;
            RemenshangpinBean hotGoodsBean = mData.get(position);
            if (hotGoodsBean != null) {

                Uri uri = Uri.parse(hotGoodsBean.picUrl);
                holder2.sdv_header.setImageURI(uri);
                holder2.tv_goods_name.setText(hotGoodsBean.name);
                holder2.tv_goods_jiage.setText("¥" + hotGoodsBean.counterPrice + "");
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


    class ArticleAdapter extends BaseAdapter {

        public Context mcontext;

        List<JifenzhuanquBean> mData = new ArrayList<JifenzhuanquBean>();

        public ArticleAdapter(Context context) {
            this.mcontext = context;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 声明内部类
            ArticleAdapter.Util util = null;
            // 中间变量
            final int flag = position;
            if (convertView == null) {
                util = new ArticleAdapter.Util();
                LayoutInflater inflater = LayoutInflater.from(mcontext);
                convertView = inflater.inflate(R.layout.item_jifenduihuan, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.sdv_article = convertView.findViewById(R.id.sdv_article);
                util.tv_name = convertView.findViewById(R.id.tv_name);
                util.tv_counterprice = convertView.findViewById(R.id.tv_counterprice);
                util.tv_retailprice = convertView.findViewById(R.id.tv_retailprice);

                convertView.setTag(util);
            } else {
                util = (ArticleAdapter.Util) convertView.getTag();
            }
            JifenzhuanquBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ArticleContentActivity.class);

                    startActivity(intent);
                }
            });

            String temp_url = bean.picUrl;
            if (temp_url == null || "".equals(temp_url)) {
                util.sdv_article.setBackgroundResource(R.drawable.article_default);

            } else {
                Uri uri = Uri.parse(temp_url);
                util.sdv_article.setImageURI(uri);
            }
            util.tv_name.setText(bean.name);
            util.tv_counterprice.setText(bean.counterPrice + "");
            util.tv_retailprice.setText(bean.retailPrice + "");
            util.tv_retailprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );

            return convertView;
        }


        class Util {
            View ll_all;
            SimpleDraweeView sdv_article;
            TextView tv_name;
            TextView tv_counterprice;
            TextView tv_retailprice;

        }
    }
}
