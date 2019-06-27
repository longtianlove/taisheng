package com.taisheng.now.bussiness.user;

import android.os.Bundle;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dragon on 2019/6/27.
 */

public class LoginActivity extends BaseActivity  implements LoginView {



    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initListener();
        initData();
        loginPresenter = new LoginPresenter(this);
//        EventBus.getDefault().register(this);
    }
    void initView() {

        setContentView(R.layout.activity_login);
    }
    void initListener() {

    }
    void initData() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

//        EventBus.getDefault().unregister(this);
    }
}
