package com.taisheng.now.bussiness.healthfiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;

/**
 * Created by dragon on 2019/6/28.
 */

public class HealthCheckActivity extends BaseActivity {
    View iv_back;
    View tv_checkhistory;

    TextView btn_zhongyitizhi;
    TextView btn_jichudaixie;
    TextView btn_fukejiankang;
    TextView btn_xinfeigongneng;
    TextView btn_yaojingjianbei;
    TextView btn_piweiganshen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check);
        initView();
    }
    void initView(){
        iv_back=findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_checkhistory=findViewById(R.id.tv_checkhistory);
        tv_checkhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HealthCheckActivity.this,HealthCheckHistoryActivity.class);
                startActivity(intent);
            }
        });

        btn_zhongyitizhi= (TextView) findViewById(R.id.btn_zhongyitizhi);
        btn_jichudaixie= (TextView) findViewById(R.id.btn_jichudaixie);
        btn_fukejiankang= (TextView) findViewById(R.id.btn_fukejiankang);
        btn_xinfeigongneng= (TextView) findViewById(R.id.btn_xinfeigongneng);
        btn_yaojingjianbei= (TextView) findViewById(R.id.btn_yaojingjianbei);
        btn_piweiganshen= (TextView) findViewById(R.id.btn_piweiganshen);


    }
}
