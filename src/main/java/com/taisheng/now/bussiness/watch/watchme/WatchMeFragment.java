package com.taisheng.now.bussiness.watch.watchme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;
import com.taisheng.now.bussiness.user.UserInstance;


/**
 * Created by Administrator on 2015/6/12.
 */

@SuppressLint("WrongConstant")
public class WatchMeFragment extends BaseFragment {
    View iv_back;
    View ll_togerenxinxi;
    SimpleDraweeView sdv_header;
    TextView tv_nickname;
    TextView tv_zhanghao;
    View tv_qiandao;

    View ll_naozhong;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_watchme, container, false);

        initView(rootView);


//        EventBus.getDefault().register(this);
        initData();

        return rootView;
    }

    View.OnClickListener toMeMessageActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), WatchMeMessageActivity.class);
            startActivity(intent);
        }
    };

    void initView(View rootView) {
        iv_back = rootView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        ll_togerenxinxi = rootView.findViewById(R.id.ll_togerenxinxi);
        ll_togerenxinxi.setOnClickListener(toMeMessageActivityListener);
        sdv_header = (SimpleDraweeView) rootView.findViewById(R.id.sdv_header);
        sdv_header.setOnClickListener(toMeMessageActivityListener);
        tv_zhanghao = rootView.findViewById(R.id.tv_zhanghao);
        tv_nickname = (TextView) rootView.findViewById(R.id.tv_nickname);
        tv_nickname.setOnClickListener(toMeMessageActivityListener);
        tv_qiandao = rootView.findViewById(R.id.tv_qiandao);
        tv_qiandao.setOnClickListener(toMeMessageActivityListener);


        ll_naozhong = rootView.findViewById(R.id.ll_naozhong);
        ll_naozhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WatchMeNaozhongListActivity.class);
                startActivity(intent);
            }
        });

    }

    void initData() {


    }

    @Override
    public void onStart() {
        super.onStart();
        if (UserInstance.getInstance().userInfo.avatar != null) {
            Uri uri = Uri.parse(Constants.Url.File_Host + UserInstance.getInstance().userInfo.avatar);
            sdv_header.setImageURI(uri);
        }
        if (!TextUtils.isEmpty(UserInstance.getInstance().userInfo.nickName)) {
            tv_nickname.setText(UserInstance.getInstance().userInfo.nickName);
        }
        if (!TextUtils.isEmpty(UserInstance.getInstance().userInfo.userName)) {
            tv_zhanghao.setText(UserInstance.getInstance().userInfo.userName);
        }
    }

    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }
}
