package com.taisheng.now.bussiness.user;

import android.annotation.SuppressLint;
import android.widget.Toast;


import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by long on 17/4/7.
 */
@SuppressLint("WrongConstant")
public class LoginPresenter {
    private WeakReference<LoginView> loginView;
//    private WeakReference<LogoutView> logoutView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = new WeakReference<>(loginView);
    }

//    public LoginPresenter(LogoutView logoutView) {
//        this.logoutView = new WeakReference<LogoutView>(logoutView);
//    }
//    public LoginPresenter(){
//
//    }

    /**
     * 获取验证码
     *
     * @param phone

     */
    public void getVerifyCode(String phone) {
//        final LoginView tloginView = loginView.get();
//
//        if (tloginView != null) {
//            tloginView.showDialog();
//            ApiUtils.getApiService().getVerifyCode(phone, deviceType).enqueue(new XMQCallback<MessageBean>() {
//                @Override
//                public void onSuccess(Response<MessageBean> response, MessageBean message) {
//                    HttpCode ret = HttpCode.valueOf(message.status);
//                    switch (ret) {
//                        case EC_SUCCESS:
//                            if (tloginView != null) {
//                                tloginView.getVerifyNextTime(message.next_req_interval);
//                            }
//                            break;
//                        case EC_FREQ_LIMIT:
//                            ToastUtil.showTost("发送验证码频率多");
//                            break;
//                        default:
//                            ToastUtil.showTost("网络出错");
//
//                    }
//                    tloginView.dismissDialog();
//                }
//
//                @Override
//                public void onFail(Call<MessageBean> call, Throwable t) {
//                    Toast.makeText(PetAppLike.mcontext, "验证码发送失败", Toast.LENGTH_LONG).show();
//                    tloginView.dismissDialog();
//                }
//            });
//        }

    }

    /**
登录
     *账号密码
     * /
     */
    public void loginUsername(String zhanghao,String password){

    }



    /**
     * 登录
     *
     * @param phone      手机号
     * @param verifyCode 验证码
     */
    public void loginPhone(final String phone, String verifyCode) throws IOException {
//        final LoginView tloginView = loginView.get();
//        if (tloginView != null) {
//            tloginView.showDialog();
//            ApiUtils.getApiService().login(phone, verifyCode, deviceType, deviceId, URLEncoder.encode(android.os.Build.MODEL.replaceAll(" ", ""), "UTF-8")).enqueue(new XMQCallback<LoginBean>() {
//                @Override
//                public void onSuccess(Response<LoginBean> response, LoginBean message) {
//
//                    HttpCode ret = HttpCode.valueOf(message.status);
//                    switch (ret) {
//                        case EC_SUCCESS:
//                            //保存登录状态
//                            UserInstance.getInstance().saveLoginState(message, phone);
//                            //小米push
//                            XMPushManagerInstance.getInstance().init();
//                           UserInstance.getInstance().getUserInfo();
//
//                            break;
//                        case EC_INVALID_VERIFY_CODE:
//                            ToastUtil.showTost("验证码无效");
//                            if (tloginView != null) {
//                                tloginView.dismissDialog();
//                            }
//                            break;
//                        default:
//                            ToastUtil.showTost("登录异常");
//                            if (tloginView != null) {
//                                tloginView.dismissDialog();
//                            }
//                            break;
//                    }
//
//                }
//
//                @Override
//                public void onFail(Call<LoginBean> call, Throwable t) {
//                    if (tloginView != null) {
//                        tloginView.dismissDialog();
//                    }
//
//                }
//            });
//        }
    }

    //退出登录
    public void logout() {
//        ApiUtils.getApiService().logout(UserInstance.getInstance().getUid(), UserInstance.getInstance().getToken()).enqueue(new XMQCallback<BaseBean>() {
//            @Override
//            public void onSuccess(Response<BaseBean> response, BaseBean message) {
//                HttpCode ret = HttpCode.valueOf(message.status);
//                if (ret == HttpCode.EC_SUCCESS) {//退出登陆成功
//                    MainActivity.getLocationWithOneMinute=false;
//                    XMPushManagerInstance.getInstance().stop();
//                    UserInstance.getInstance().clearLoginInfo();
//                    PetInfoInstance.getInstance().clearPetInfo();
//                    DeviceInfoInstance.getInstance().clearDeviceInfo();
//
//                    SPUtil.putLAST_SHOW_ACTIVITY(0);
//
//                    final LogoutView togoutView = logoutView.get();
//                    if (togoutView != null) {
//                        //退出登录成功
//                        togoutView.success();
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFail(Call<BaseBean> call, Throwable t) {
//
//            }
//        });
    }
}
