package com.taisheng.now.bussiness.me;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.util.Apputil;

/**
 * Created by dragon on 2019/6/28.
 */

public class AboutUsActivity extends BaseActivity {
    View iv_back;
    TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
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

        tv_version = (TextView) findViewById(R.id.tv_version);
        String version = Apputil.getVersionName(this);
        tv_version.setText("版本号："+version);
    }
}
