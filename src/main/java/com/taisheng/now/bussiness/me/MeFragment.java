package com.taisheng.now.bussiness.me;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Administrator on 2015/6/12.
 */

@SuppressLint("WrongConstant")
public class MeFragment extends BaseFragment {






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);




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
