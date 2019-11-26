package com.taisheng.now.bussiness.watch.watchfirst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.bussiness.watch.watchme.WatchNaoZhongXinzengActivity;

/**
 * Created by dragon on 2019/6/29.
 */

public class WatchFirstGuijiActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    ImageView iv_back;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchfirstguiji);
        initView();

    }

    void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }



}
