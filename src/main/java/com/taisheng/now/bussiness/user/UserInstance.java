package com.taisheng.now.bussiness.user;


import com.taisheng.now.Constants;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.result.PictureBean;
import com.taisheng.now.bussiness.bean.result.UserInfo;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.util.SPUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
            userInstance.userInfo = new UserInfo();

            userInstance.userInfo.id = SPUtil.getUid();
            userInstance.userInfo.token = SPUtil.getToken();
            userInstance.userInfo.nickName = SPUtil.getNickname();
            userInstance.userInfo.phone=SPUtil.getPhone();
            userInstance.userInfo.userName=SPUtil.getZhanghao();

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


    //上传头像信息
    public void uploadImage(final String path) {
        try {

            //把Bitmap保存到sd卡中
            File fImage = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), fImage);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", fImage.getName(), requestFile);
            ApiUtils.getApiService().uploadLogo(body).enqueue(new TaiShengCallback<BaseBean<PictureBean>>() {

                                                                  @Override
                                                                  public void onSuccess(Response<BaseBean<PictureBean>> response, BaseBean<PictureBean> message) {
                                                                      switch (message.code) {
                                                                          case Constants.HTTP_SUCCESS:
                                                                              String path = message.result.path;
                                                                              break;
                                                                      }


                                                                  }

                                                                  @Override
                                                                  public void onFail(Call<BaseBean<PictureBean>> call, Throwable t) {

                                                                  }
                                                              }
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void clearUserInfo() {
//        uid = "";
        this.userInfo.id = "";
        SPUtil.putUid("");
        this.userInfo.token = "";
        SPUtil.putToken("");
        this.userInfo.nickName="";
        SPUtil.putNickname("");
        this.userInfo.phone="";
        SPUtil.putPhone("");
        this.userInfo.userName="";
        SPUtil.putZhanghao("");


    }

    public void saveUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        SPUtil.putUid(userInfo.id);
        SPUtil.putToken(userInfo.token);
        SPUtil.putNickname(userInfo.nickName);
        SPUtil.putPhone(userInfo.phone);
        SPUtil.putZhanghao(userInfo.userName);

    }

    public String getUid() {
        return userInfo.id;
    }

    public String getToken() {
        return userInfo.token;
    }

    public String getNickname(){
        return userInfo.nickName;
    }
    public String getPhone(){
        return userInfo.phone;
    }
    public String getZhanghao(){
        return userInfo.userName;
    }

}
