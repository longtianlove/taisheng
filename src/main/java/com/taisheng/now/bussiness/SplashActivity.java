package com.taisheng.now.bussiness;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.WindowManager;

import com.taisheng.now.EventManage;
import com.taisheng.now.R;
import com.taisheng.now.SampleAppLike;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.bussiness.me.FillInMessageActivity;
import com.taisheng.now.bussiness.me.FillInMessageSecondActivity;
import com.taisheng.now.bussiness.user.LoginActivity;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.push.XMPushManagerInstance;
import com.taisheng.now.util.Apputil;
import com.taisheng.now.util.SPUtil;
import com.xiaomi.mipush.sdk.MiPushClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;


/**
 * Created by long on 2017/4/13.
 */

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        toWhere();//判断跳转逻辑

    }

    //判断跳转逻辑
    void toWhere() {
        if (TextUtils.isEmpty(SPUtil.getUid())) {
            SPUtil.putAPP_VERSION(Apputil.getVersionCode() + "");
            SampleAppLike.mainHandler = new Handler(getMainLooper());
            SampleAppLike.mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        } else if (TextUtils.isEmpty(SPUtil.getRealname())) {
            SampleAppLike.mainHandler = new Handler(getMainLooper());
            SampleAppLike.mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
//            SPUtil.putAPP_VERSION(Apputil.getVersionCode() + "");
//            EventBus.getDefault().register(this);
//            //获取基本信息
//            UserInstance.getInstance().getUserInfo();
                    MiPushClient.registerPush(SampleAppLike.mcontext, XMPushManagerInstance.APP_ID, XMPushManagerInstance.APP_KEY);
                    SPUtil.putAPP_VERSION(Apputil.getVersionCode() + "");
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, FillInMessageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        } else if (TextUtils.isEmpty(SPUtil.getHEIGHT())) {
            SampleAppLike.mainHandler = new Handler(getMainLooper());
            SampleAppLike.mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
//            SPUtil.putAPP_VERSION(Apputil.getVersionCode() + "");
//            EventBus.getDefault().register(this);
//            //获取基本信息
//            UserInstance.getInstance().getUserInfo();
                    MiPushClient.registerPush(SampleAppLike.mcontext, XMPushManagerInstance.APP_ID, XMPushManagerInstance.APP_KEY);
                    SPUtil.putAPP_VERSION(Apputil.getVersionCode() + "");
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, FillInMessageSecondActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        } else {
            SampleAppLike.mainHandler = new Handler(getMainLooper());
            SampleAppLike.mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    MiPushClient.registerPush(SampleAppLike.mcontext, XMPushManagerInstance.APP_ID, XMPushManagerInstance.APP_KEY);
                    SPUtil.putAPP_VERSION(Apputil.getVersionCode() + "");
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }

    }


//    //网络获取用户信息成功
//    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0)
//    public void next(EventManage.getUserInfoEvent event) {
//
//        EventBus.getDefault().unregister(this);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//
//        }
//        Intent intent = new Intent();
//        intent.setClass(SplashActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();
//
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
