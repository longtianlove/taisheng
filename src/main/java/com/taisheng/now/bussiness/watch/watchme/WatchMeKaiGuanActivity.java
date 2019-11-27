package com.taisheng.now.bussiness.watch.watchme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;

/**
 * Created by dragon on 2019/6/29.
 */

public class WatchMeKaiGuanActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    ImageView iv_back;

    View ll_kaiguan_gps;
    ImageView iv_kaiguan_gps;

    View ll_kaiguan_sos;
    ImageView iv_kaiguan_sos;

    View ll_kaiguan_jinjilianxiren;

    View ll_kaiguan_didianduanxin;
    ImageView iv_kaiguan_didianduanxin;

    View ll_kaiguan_quxiashouhuan;
    ImageView iv_kaiguan_quxiashouhuan;

    View ll_kaiguan_jibu;
    ImageView iv_kaiguan_jibu;

    View ll_kaiguan_fanzhuan;
    ImageView iv_kaiguan_fanzhuan;

    View ll_kaiguan_miandarao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchme_kaiguan);
        initView();
//        EventBus.getDefault().register(this);
    }

    void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_kaiguan_gps = findViewById(R.id.ll_kaiguan_gps);
        ll_kaiguan_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_kaiguan_gps.isSelected()) {
                    iv_kaiguan_gps.setSelected(false);
                } else {
                    iv_kaiguan_gps.setSelected(true);
                }
                //todo 访问网络
            }
        });
        iv_kaiguan_gps = findViewById(R.id.iv_kaiguan_gps);
        iv_kaiguan_gps.setSelected(false);

        ll_kaiguan_sos = findViewById(R.id.ll_kaiguan_sos);
        ll_kaiguan_sos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (iv_kaiguan_sos.isSelected()) {
                    iv_kaiguan_sos.setSelected(false);
                } else {
                    iv_kaiguan_sos.setSelected(true);
                }
                //todo 访问网络
            }
        });

        ll_kaiguan_jinjilianxiren = findViewById(R.id.ll_kaiguan_jinjilianxiren);
        ll_kaiguan_jinjilianxiren.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //todo 进入紧急联系人
            }
        });
        iv_kaiguan_sos = findViewById(R.id.iv_kaiguan_sos);
        iv_kaiguan_sos.setSelected(false);

        ll_kaiguan_didianduanxin = findViewById(R.id.ll_kaiguan_didianduanxin);
        ll_kaiguan_didianduanxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_kaiguan_didianduanxin.isSelected()) {
                    iv_kaiguan_didianduanxin.setSelected(false);
                } else {
                    iv_kaiguan_didianduanxin.setSelected(true);
                }
                //todo 访问网络
            }
        });
        iv_kaiguan_didianduanxin = findViewById(R.id.iv_kaiguan_didianduanxin);
        iv_kaiguan_didianduanxin.setSelected(false);

        ll_kaiguan_quxiashouhuan = findViewById(R.id.ll_kaiguan_quxiashouhuan);
        ll_kaiguan_quxiashouhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_kaiguan_quxiashouhuan.isSelected()) {
                    iv_kaiguan_quxiashouhuan.setSelected(false);
                } else {
                    iv_kaiguan_quxiashouhuan.setSelected(true);
                }
                //todo 访问网络
            }
        });
        iv_kaiguan_quxiashouhuan = findViewById(R.id.iv_kaiguan_quxiashouhuan);
        iv_kaiguan_quxiashouhuan.setSelected(false);


        ll_kaiguan_jibu = findViewById(R.id.ll_kaiguan_jibu);
        ll_kaiguan_jibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_kaiguan_jibu.isSelected()) {
                    iv_kaiguan_jibu.setSelected(false);
                } else {
                    iv_kaiguan_jibu.setSelected(true);
                }
                //todo 访问网络
            }
        });
        iv_kaiguan_jibu = findViewById(R.id.iv_kaiguan_jibu);
        iv_kaiguan_jibu.setSelected(false);


        ll_kaiguan_fanzhuan = findViewById(R.id.ll_kaiguan_fanzhuan);
        ll_kaiguan_fanzhuan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (iv_kaiguan_fanzhuan.isSelected()) {
                    iv_kaiguan_fanzhuan.setSelected(false);
                } else {
                    iv_kaiguan_fanzhuan.setSelected(true);
                }
                //todo 访问网络
            }
        });
        iv_kaiguan_fanzhuan = findViewById(R.id.iv_kaiguan_fanzhuan);
        iv_kaiguan_fanzhuan.setSelected(false);


        ll_kaiguan_miandarao = findViewById(R.id.ll_kaiguan_miandarao);
        ll_kaiguan_miandarao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 设置免打扰时间段
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
