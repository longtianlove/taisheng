package com.taisheng.now.bussiness.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;

/**
 * Created by dragon on 2019/6/28.
 */

public class DoctorCommentActivity extends BaseActivity {
    View iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorcomment);
        initView();
        initData();
    }
    void initView(){
        iv_back=findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    String doctorId;
    void initData() {
        Intent intent=getIntent();
        doctorId=intent.getStringExtra("id");
    }
}
