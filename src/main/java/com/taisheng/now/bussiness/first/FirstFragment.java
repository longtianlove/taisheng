package com.taisheng.now.bussiness.first;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;
import com.taisheng.now.bussiness.MainActivity;
import com.taisheng.now.bussiness.healthfiles.HealthCheckActivity;
import com.taisheng.now.util.SPUtil;
import com.taisheng.now.view.GuideView;
import com.taisheng.now.view.banner.BannerViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.Date;

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
    ViewPager vp_zhuanjia;

    TextView tv_doctor_more;
    TextView tv_secret_more;


    View ll_jiankangceping;


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
        ll_jiankangceping=rootView.findViewById(R.id.ll_jiankangceping);
        ll_jiankangceping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HealthCheckActivity.class);
                startActivity(intent);
            }
        });

        tv_location_city = (TextView) rootView.findViewById(R.id.tv_location_city);
        ll_search = rootView.findViewById(R.id.ll_search);

        bannerContaner = (FrameLayout) rootView.findViewById(R.id.bannerContaner);
        bannerContaner.setVisibility(View.GONE);
        bannerViewPager = new BannerViewPager(mActivity);

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

    }


    void initData() {


    }

    @Override
    public void onStart() {
        super.onStart();
        if(!SPUtil.getGUIDE()){
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
                    //todo 上线删掉
//                    SPUtil.putGUIDE(true);

                }
            });
            guideView.show();
        }
    }

    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }
}
