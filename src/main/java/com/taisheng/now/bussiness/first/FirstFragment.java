package com.taisheng.now.bussiness.first;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;
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
        tv_location_city= (TextView) rootView.findViewById(R.id.tv_location_city);
        ll_search=rootView.findViewById(R.id.ll_search);

        bannerContaner = (FrameLayout)rootView.findViewById(R.id.bannerContaner);
        bannerContaner.setVisibility(View.GONE);
        bannerViewPager = new BannerViewPager(mActivity);

    }


    void initData() {


    }


    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }
}
