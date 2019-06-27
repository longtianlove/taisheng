package com.taisheng.now.bussiness.user;


import com.taisheng.now.SampleApplication;
import com.taisheng.now.bussiness.bean.UserBean;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.util.SPUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by long on 17/4/7.
 */

public class UserInstance {
    private static UserInstance userInstance;

    private UserInstance() {
    }

    public static UserInstance getInstance() {
        if (userInstance == null) {
            userInstance = new UserInstance();

            userInstance.uid = SPUtil.getUid();


        }
        return userInstance;
    }


    public String uid;

    public String token;


    //获取用户基本信息
    public void getUserInfo() {
        //获取用户基本信息
        ApiUtils.getApiService().getUserInfo(UserInstance.getInstance().getUid(),
                UserInstance.getInstance().getToken()
        ).enqueue(new TaiShengCallback<UserBean>() {
            @Override
            public void onSuccess(Response<UserBean> response, UserBean message) {
//                HttpCode ret = HttpCode.valueOf(message.status);
//                switch (ret) {
//                    case EC_SUCCESS:
                        UserInstance.getInstance().saveUserInfo(message);
//                        break;
//                }
//                EventBus.getDefault().post(new EventManage.getUserInfoEvent());

            }

            @Override
            public void onFail(Call<UserBean> call, Throwable t) {
//                EventBus.getDefault().post(new EventManage.getUserInfoEvent());
            }
        });

    }


    public void clearUserInfo() {
        uid = "";
        SPUtil.putUid("");

    }

    public void saveUserInfo(UserBean userBean) {
        uid = userBean.uid;
        SPUtil.putUid(userBean.uid);
    }

    public String getUid() {
        return uid;
    }
    public String getToken() {
        return token;
    }

}
