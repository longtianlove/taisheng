package com.taisheng.now.bussiness.first;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;

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






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);




//        EventBus.getDefault().register(this);
        initData();

        return rootView;
    }

    void initData() {


    }






    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }
}
