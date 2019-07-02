package com.taisheng.now.bussiness.doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.Toast;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.shipin.TRTCGetUserIDAndUserSig;
import com.taisheng.now.shipin.TRTCMainActivity;
import com.taisheng.now.util.DialogUtil;
import com.taisheng.now.util.DoubleClickUtil;
import com.taisheng.now.view.chenjinshi.StatusBarUtil;
import com.tencent.trtc.TRTCCloudDef;

import java.util.ArrayList;

/**
 * Created by dragon on 2019/7/1.
 */

public class DoctorDetailActivity extends Activity {


    private TRTCGetUserIDAndUserSig mUserInfoLoader;

    View ll_zixun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);


        //沉浸式代码配置
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        //用来设置整体下移，状态栏沉浸
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);


        setContentView(R.layout.activity_doctor_detail);
        initView();

        // 如果配置有config文件，则从config文件中选择userId
        mUserInfoLoader = new TRTCGetUserIDAndUserSig(this);

    }

    void initView() {
        ll_zixun = findViewById(R.id.ll_zixun);
        ll_zixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DoubleClickUtil.isFastMiniDoubleClick()) {
                    return;
                }
                DialogUtil.showzixunDialog(DoctorDetailActivity.this,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onJoinRoom(999,"Android_trtc_01");

                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(DoctorDetailActivity.this, TRTCMainActivity.class);
                                startActivity(intent);
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }
                );
            }
        });
    }






    /**
     *  Function: 读取用户输入，并创建（或加入）音视频房间
     *
     *  此段示例代码最主要的作用是组装 TRTC SDK 进房所需的 TRTCParams
     *
     *  TRTCParams.sdkAppId => 可以在腾讯云实时音视频控制台（https://console.cloud.tencent.com/rav）获取
     *  TRTCParams.userId   => 此处即用户输入的用户名，它是一个字符串
     *  TRTCParams.roomId   => 此处即用户输入的音视频房间号，比如 125
     *  TRTCParams.userSig  => 此处示例代码展示了两种获取 usersig 的方式，一种是从【控制台】获取，一种是从【服务器】获取
     *
     * （1）控制台获取：可以获得几组已经生成好的 userid 和 usersig，他们会被放在一个 json 格式的配置文件中，仅适合调试使用
     * （2）服务器获取：直接在服务器端用我们提供的源代码，根据 userid 实时计算 usersig，这种方式安全可靠，适合线上使用
     *
     *  参考文档：https://cloud.tencent.com/document/product/647/17275
     */
    private String mUserId = "";
    private String mUserSig= "";
    private void onJoinRoom(final int roomId, final String userId) {
        final Intent intent = new Intent(DoctorDetailActivity.this, TRTCMainActivity.class);

        intent.putExtra("roomId", roomId);
        intent.putExtra("userId", userId);

//        RadioButton rbLive = (RadioButton) findViewById(R.id.rb_live);
//        if (rbLive.isChecked()) {
//            intent.putExtra("AppScene", TRTCCloudDef.TRTC_APP_SCENE_LIVE);
//            RadioButton rbAnchor = (RadioButton) findViewById(R.id.rb_anchor);
//            if (rbAnchor.isChecked())  {
//                intent.putExtra("role", TRTCCloudDef.TRTCRoleAnchor);
//            } else {
//                intent.putExtra("role", TRTCCloudDef.TRTCRoleAudience);
//            }
//        } else {
            intent.putExtra("AppScene", TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL);
//        }


//        boolean isCustomVideoCapture = ((RadioButton)findViewById(R.id.rb_video_file)).isChecked();
//        if (TextUtils.isEmpty(mVideoFile)) isCustomVideoCapture = false;
//        intent.putExtra("customVideoCapture", isCustomVideoCapture);
//        intent.putExtra("videoFile", mVideoFile);

//        int sdkAppId = mUserInfoLoader.getSdkAppIdFromConfig();
//        if (sdkAppId > 0) {
//            //（1） 从控制台获取的 json 文件中，简单获取几组已经提前计算好的 userid 和 usersig
//            ArrayList<String> userIdList = mUserInfoLoader.getUserIdFromConfig();
//            ArrayList<String> userSigList = mUserInfoLoader.getUserSigFromConfig();
//            int position = userIdList.indexOf(userId);
//            String userSig = "";
//            if (userSigList != null && userSigList.size() > position) {
//                userSig = userSigList.get(position);
//            }
//            intent.putExtra("sdkAppId", sdkAppId);
//            intent.putExtra("userSig", userSig);
//            startActivity(intent);
//        } else {
            //appId 可以在腾讯云实时音视频控制台（https://console.cloud.tencent.com/rav）获取
            int sdkAppId = 1400224786;
//            if(!TextUtils.isEmpty(mUserId) && mUserId.equalsIgnoreCase(userId) && !TextUtils.isEmpty(mUserSig)) {
//                intent.putExtra("sdkAppId", sdkAppId);
//                intent.putExtra("userSig", mUserSig);
////                saveUserInfo(String.valueOf(roomId), userId, mUserSig);
//                startActivity(intent);
//            } else {
                //（2） 通过 http 协议向一台服务器获取 userid 对应的 usersig
        //todo 需要修改
                final int finalSdkAppId = 1400224786;
        intent.putExtra("sdkAppId", finalSdkAppId);
        intent.putExtra("userSig", "eJxlj0FPgzAAhe-8CsLZaNtR2Ew8YOMMhA1wG1ovDYOyVbfSlMo2jf9dxSWS*K7fl-fyPizbtp1lvLgsyrJ5k4aZk*KOfW07wLn4g0qJihWGjXT1D-KjEpqzojZc9xBijBEAQ0dUXBpRi7MRyEo3341Gm5IBOBDb6pX1a79NLgAIuf7YGypi08PZ3YqEGVEiTmiZxdm*S0S9G*cNToNH7cqnnSbP-lWeovi9QLeT8BBuVsmaHFxC85d72k3nx5rT6XI2X-vbaBGRaPsQRMZrM5pCejOYNGLPz9c8OPE9CNCAdly3opG9gADEEI3ATxzr0-oClrRghg__");
        startActivity(intent);
                mUserInfoLoader.getUserSigFromServer(sdkAppId, roomId, userId, "12345678", new TRTCGetUserIDAndUserSig.IGetUserSigListener() {
                    @Override
                    public void onComplete(String userSig, String errMsg) {
                        if (!TextUtils.isEmpty(userSig)) {
                            intent.putExtra("sdkAppId", finalSdkAppId);
                            intent.putExtra("userSig", userSig);
//                            saveUserInfo(String.valueOf(roomId), userId, userSig);
                            startActivity(intent);
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(DoctorDetailActivity.this, "从服务器获取userSig失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

        }
    }









