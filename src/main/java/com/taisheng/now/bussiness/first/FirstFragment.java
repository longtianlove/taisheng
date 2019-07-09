package com.taisheng.now.bussiness.first;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.base.BaseFragment;
import com.taisheng.now.bussiness.MainActivity;
import com.taisheng.now.bussiness.bean.post.BasePostBean;
import com.taisheng.now.bussiness.bean.result.ArticleBean;
import com.taisheng.now.bussiness.bean.result.ArticleResultBean;
import com.taisheng.now.bussiness.bean.result.DoctorBean;
import com.taisheng.now.bussiness.healthfiles.HealthCheckActivity;
import com.taisheng.now.bussiness.secret.ArticleContentActivity;
import com.taisheng.now.bussiness.secret.SearchResultActivity;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.util.DialogUtil;
import com.taisheng.now.util.SPUtil;
import com.taisheng.now.view.DoctorLabelWrapLayout;
import com.taisheng.now.view.GuideView;
import com.taisheng.now.view.ScoreStar;
import com.taisheng.now.view.TaishengListView;
import com.taisheng.now.view.WithScrolleViewListView;
import com.taisheng.now.view.banner.BannerViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by Administrator on 2015/6/12.
 */

@SuppressLint("WrongConstant")
public class FirstFragment extends BaseFragment {
    TextView tv_location_city;
    View ll_search;

    private FrameLayout bannerContaner;
    BannerViewPager bannerViewPager;
    private View bannerView;
    ViewPager vp_zhuanjia;

    TextView tv_doctor_more;
    TextView tv_secret_more;


    View ll_shishizixun;
    View ll_sushenhufu;
    View ll_yiliaoyangsheng;
    View ll_muyingyunyu;
    View ll_yingjizixun;
    View ll_jianshenyundong;
    View ll_yongyaozhidao;

    View ll_jiankangceping;


    com.taisheng.now.view.WithScrolleViewListView lv_articles;
    ArticleAdapter madapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        initView(rootView);


//        EventBus.getDefault().register(this);
        initData();


        return rootView;
    }

    void initView(View rootView) {
        ll_shishizixun = rootView.findViewById(R.id.ll_shishizixun);
        ll_shishizixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(1);
            }
        });
        ll_sushenhufu = rootView.findViewById(R.id.ll_sushenhufu);
        ll_sushenhufu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(2);
            }
        });
        ll_yiliaoyangsheng = rootView.findViewById(R.id.ll_yiliaoyangsheng);
        ll_yiliaoyangsheng.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(2);
            }
        });
        ll_muyingyunyu = rootView.findViewById(R.id.ll_muyingyunyu);
        ll_muyingyunyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(2);
            }
        });
        ll_yingjizixun = rootView.findViewById(R.id.ll_yingjizixun);
        ll_yingjizixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转到视频聊天页面
            }
        });
        ll_jianshenyundong = rootView.findViewById(R.id.ll_jianshenyundong);
        ll_jianshenyundong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(2);
            }
        });
        ll_yongyaozhidao = rootView.findViewById(R.id.ll_yongyaozhidao);
        ll_yongyaozhidao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(2);
            }
        });
        ll_jiankangceping = rootView.findViewById(R.id.ll_jiankangceping);
        ll_jiankangceping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthCheckActivity.class);
                startActivity(intent);
            }
        });

        tv_location_city = (TextView) rootView.findViewById(R.id.tv_location_city);
        ll_search = rootView.findViewById(R.id.ll_search);

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

        vp_zhuanjia = (ViewPager) rootView.findViewById(R.id.vp_zhuanjia);
        tv_doctor_more = (TextView) rootView.findViewById(R.id.tv_doctor_more);
        tv_doctor_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(1);
            }
        });
        tv_secret_more = (TextView) rootView.findViewById(R.id.tv_secret_more);
        tv_secret_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(2);
            }
        });
        lv_articles = (WithScrolleViewListView) rootView.findViewById(R.id.lv_articles);
        madapter = new ArticleAdapter(mActivity);
        lv_articles.setAdapter(madapter);
        if (!SPUtil.getGUIDE()) {
//文字图片
            final ImageView iv1 = new ImageView(getActivity());
            iv1.setImageResource(R.drawable.guide_word);
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iv1.setLayoutParams(params1);

            //我知道啦
            final ImageView iv2 = new ImageView(getActivity());
            iv2.setImageResource(R.drawable.guide_know);
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iv2.setLayoutParams(params2);

            GuideView guideView = GuideView.Builder
                    .newInstance(getActivity())
                    .setTargetView(ll_jiankangceping) //设置目标view
                    .setTextGuideView(iv1)   //设置文字图片
                    .setCustomGuideView(iv2)  //设置 我知道啦图片
                    .setOffset(0, 80)      //偏移量 x=0 y=80
                    .setDirction(GuideView.Direction.BOTTOM)  //方向
                    .setShape(GuideView.MyShape.CIRCULAR)  //圆形
                    .setRadius(0)               //圆角
                    .setContain(false)             //透明的方块时候包含目标view 默认false
                    .setBgColor(getResources().getColor(R.color.bg_shadow))  //背景颜色

                    .build();
            iv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guideView.hide();
                    SPUtil.putGUIDE(true);

                }
            });
            guideView.show();
        }

    }


    void initData() {
        getHotArticle();

    }

    void getHotArticle() {

        BasePostBean bean = new BasePostBean();
        bean.token = UserInstance.getInstance().getToken();
        bean.userId = UserInstance.getInstance().getUid();

        DialogUtil.showProgress(mActivity, "");
        ApiUtils.getApiService().hotArticleList(bean).enqueue(new TaiShengCallback<BaseBean<ArrayList<ArticleBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<ArticleBean>>> response, BaseBean<ArrayList<ArticleBean>> message) {
                DialogUtil.closeProgress();
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:

                        if (message.result != null && message.result.size() > 0) {
                            //有消息
//                            PAGE_NO++;
                            madapter.mData.addAll(message.result);
//                            if(message.result.size()<10){
//                                lv_articles.setHasLoadMore(false);
//                                lv_articles.setLoadAllViewText("暂时只有这么多文章");
//                                lv_articles.setLoadAllFooterVisible(false);
//                            }else{
//                                lv_articles.setHasLoadMore(true);
//                            }
                            madapter.notifyDataSetChanged();
                        } else {
//                            //没有消息
//                            lv_articles.setHasLoadMore(false);
//                            lv_articles.setLoadAllViewText("暂时只有这么多文章");
//                            lv_articles.setLoadAllFooterVisible(false);
                        }

                        break;

                }

            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<ArticleBean>>> call, Throwable t) {
                DialogUtil.closeProgress();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    /**
     * Created by louisgeek on 2016/3/23.
     */
    public class MyPagerAdapter extends PagerAdapter {
        private List<DoctorBean> doctors;

        public MyPagerAdapter(List<DoctorBean> doctors) {
            this.doctors = doctors;
        }

        @Override
        public int getCount() {
            return doctors.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // return super.instantiateItem(container, position);
            DoctorBean view=doctors.get(position);
           View allView= mActivity.getLayoutInflater().inflate(R.layout.item_zhuanjia,container);
//            container.addView(view);
            SimpleDraweeView sdv_header= (SimpleDraweeView) allView.findViewById(R.id.sdv_header);
            TextView tv_doctor_name= (TextView) allView.findViewById(R.id.tv_doctor_name);
            TextView tv_workage= (TextView) allView.findViewById(R.id.tv_workage);
            DoctorLabelWrapLayout dlwl_doctor_label= (DoctorLabelWrapLayout) allView.findViewById(R.id.dlwl_doctor_label);
            ScoreStar scorestar= (ScoreStar) allView.findViewById(R.id.scorestar);
            return  view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
//            container.removeView(views.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return super.getPageTitle(position);
            return "标题"+position;
        }
    }


    class ArticleAdapter extends BaseAdapter {

        public Context mcontext;

        List<ArticleBean> mData = new ArrayList<ArticleBean>();

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
                convertView = inflater.inflate(R.layout.item_article, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.sdv_article = (SimpleDraweeView) convertView.findViewById(R.id.sdv_article);
                util.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                util.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
                util.tv_typename = (TextView) convertView.findViewById(R.id.tv_typename);
                util.tv_createtime = (TextView) convertView.findViewById(R.id.tv_createtime);

                convertView.setTag(util);
            } else {
                util = (ArticleAdapter.Util) convertView.getTag();
            }
            ArticleBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ArticleContentActivity.class);
                    intent.putExtra("articleId", bean.id);
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
            util.tv_title.setText(bean.title);
            util.tv_content.setText(bean.content);
            util.tv_typename.setText(bean.typeName);
            util.tv_createtime.setText(bean.createTime);

            return convertView;
        }


        class Util {
            View ll_all;
            SimpleDraweeView sdv_article;
            TextView tv_title;
            TextView tv_content;
            TextView tv_typename;
            TextView tv_createtime;

        }
    }

    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }
}
