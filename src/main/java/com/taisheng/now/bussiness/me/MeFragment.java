package com.taisheng.now.bussiness.me;

import android.annotation.SuppressLint;
import android.content.Intent;
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


    View ll_mypingjia;
    View ll_tousuzhongxin;
    View ll_yijianfankui;
    View ll_aboutus;
View ll_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);

        initView(rootView);


//        EventBus.getDefault().register(this);
        initData();

        return rootView;
    }

    void initView(View rootView) {
        ll_mypingjia = rootView.findViewById(R.id.ll_mypingjia);
        ll_mypingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyPingjiaActivity.class);
                startActivity(intent);
            }
        });
        ll_tousuzhongxin=rootView.findViewById(R.id.ll_tousuzhongxin);
        ll_tousuzhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TousuzhongxinActivity.class);
                startActivity(intent);
            }
        });
        ll_yijianfankui=rootView.findViewById(R.id.ll_yijianfankui);
        ll_yijianfankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YijianfankuiActivity.class);
                startActivity(intent);
            }
        });
        ll_aboutus=rootView.findViewById(R.id.ll_aboutus);
        ll_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        ll_setting=rootView.findViewById(R.id.ll_setting);
        ll_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

    }

    void initData() {


    }


    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }
}
