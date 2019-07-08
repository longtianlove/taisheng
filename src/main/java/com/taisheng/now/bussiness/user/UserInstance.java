package com.taisheng.now.bussiness.user;


import com.taisheng.now.bussiness.bean.UserInfo;
import com.taisheng.now.util.SPUtil;

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
            userInstance.userInfo = new UserInfo();

            userInstance.userInfo.id = SPUtil.getUid();
            userInstance.userInfo.token = SPUtil.getToken();

        }
        return userInstance;
    }


//    public String uid;
//
//    public String token;

    public UserInfo userInfo;


//    //获取用户基本信息
//    public void getUserInfo() {
//        //获取用户基本信息
//        ApiUtils.getApiService().getUserInfo(UserInstance.getInstance().getUid(),
//                UserInstance.getInstance().getToken()
//        ).enqueue(new TaiShengCallback<UserBean>() {
//            @Override
//            public void onSuccess(Response<UserBean> response, UserBean message) {
////                HttpCode ret = HttpCode.valueOf(message.status);
////                switch (ret) {
////                    case EC_SUCCESS:
//                        UserInstance.getInstance().saveUserInfo(message);
////                        break;
////                }
////                EventBus.getDefault().post(new EventManage.getUserInfoEvent());
//
//            }
//
//            @Override
//            public void onFail(Call<UserBean> call, Throwable t) {
////                EventBus.getDefault().post(new EventManage.getUserInfoEvent());
//            }
//        });
//
//    }


    public void clearUserInfo() {
//        uid = "";
        this.userInfo.id = "";
        SPUtil.putUid("");
        this.userInfo.token = "";
        SPUtil.putToken("");

    }

    public void saveUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        SPUtil.putUid(userInfo.id);
        SPUtil.putToken(userInfo.token);
    }

    public String getUid() {
        return userInfo.id;
    }

    public String getToken() {
        return userInfo.token;
    }

}
