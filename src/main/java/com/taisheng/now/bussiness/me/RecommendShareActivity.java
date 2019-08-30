package com.taisheng.now.bussiness.me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mob.MobSDK;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.util.Apputil;
import com.taisheng.now.util.DensityUtil;
import com.taisheng.now.util.ZXingUtils;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by dragon on 2019/6/28.
 */

public class RecommendShareActivity extends BaseActivity {
    View iv_back;


    SimpleDraweeView sdv_header;
    TextView tv_nickname;

    ImageView iv_share_qr;


    View ll_weixin;
    View ll_pengyouquan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendshare);
        initView();
    }

    void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sdv_header = (SimpleDraweeView) findViewById(R.id.sdv_header);
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);

        iv_share_qr = (ImageView) findViewById(R.id.iv_share_qr);

        ll_weixin=findViewById(R.id.ll_weixin);
        ll_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare(Wechat.NAME);
            }
        });

        ll_pengyouquan=findViewById(R.id.ll_pengyouquan);
        ll_pengyouquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare(WechatMoments.NAME);
            }
        });

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



    }

    Bitmap bitmap;
    @Override
    protected void onResume() {
        super.onResume();
        //todo 修改分享的url
        bitmap = ZXingUtils.createQRImage("http://www.baidu.com", DensityUtil.dip2px(this,230), DensityUtil.dip2px(this,230));
        iv_share_qr.setImageBitmap(bitmap);
    }



    private void showShare(String platform) {
        final OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        oks.setTitle("泰晟健康");
        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl(Constants.Url.Article.articleContent+articleId);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，确保SDcard下面存在此张图片
//        oks.setImagePath("/sdcard/test.jpg");
        oks.setImageData(bitmap);
        // url在微信、Facebook等平台中使用
//        oks.setUrl(Constants.Url.Article.articleContent+articleId);
        //启动分享
        oks.show(MobSDK.getContext());
    }
}
