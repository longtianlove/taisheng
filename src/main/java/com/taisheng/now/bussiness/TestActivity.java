package com.taisheng.now.bussiness;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.alibaba.fastjson.JSONObject;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.bussiness.me.FuwuxieyiActivity;
import com.taisheng.now.bussiness.me.YisixieyiActivity;
import com.taisheng.now.util.Apputil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import okhttp3.Request;


/**
 * Created by dragon on 2019/6/28.
 */

public class TestActivity extends BaseActivity {
    View iv_back;
    EditText et_qian;
    View tv_gozhifu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
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
        et_qian = findViewById(R.id.et_qian);
        tv_gozhifu = findViewById(R.id.tv_gozhifu);
        tv_gozhifu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String qian=et_qian.getText().toString();





                IWXAPI api= WXAPIFactory.createWXAPI(TestActivity.this, "wxAPPID",false);//填写自己的APPIDapi.registerApp("wxAPPID");//填写自己的APPID，注册本身
                PayReq req = new PayReq();//PayReq就是订单信息对象
                req.appId= "wx8888888888888888";//你的微信appid
                req.partnerId       = "1900000109";//商户号
                req.prepayId        = "WX1217752501201407033233368018";//预支付交易会话ID
                req.nonceStr        = "5K8264ILTKCH16CQ2502SI8ZNMTM67VS";//随机字符串
                req.timeStamp       = "1412000000";//时间戳
                req.packageValue    = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
                req.sign            = "C380BEC2BFD727A4B6845133519F3AD6";//签名
                api.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求


            }
        });


    }


}
