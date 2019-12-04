package com.taisheng.now.bussiness.watch.watchme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.bussiness.watch.WatchInstance;

/**
 * Created by dragon on 2019/6/29.
 */

public class WatchMeMiandaraoActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    ImageView iv_back;

    View ll_kaiguan_gps;
    ImageView iv_kaiguan_gps;


    View ll_kaiguan_miandarao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchme_miandarao);
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
        if("1".equals(WatchInstance.getInstance().watchSilencetimeSwitch)){
            iv_kaiguan_gps.setSelected(true);
        }else{
            iv_kaiguan_gps.setSelected(false);
        }



        ll_kaiguan_miandarao = findViewById(R.id.ll_kaiguan_miandarao);
        ll_kaiguan_miandarao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WatchMeMiandaraoActivity.this, WatchMianDaraoSettingliActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
