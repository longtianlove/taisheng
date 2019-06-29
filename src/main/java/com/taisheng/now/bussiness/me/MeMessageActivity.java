package com.taisheng.now.bussiness.me;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;

/**
 * Created by dragon on 2019/6/29.
 */

public class MeMessageActivity extends BaseActivity {
    ImageView iv_back;
    View ll_nickname;
    View ll_bindphone;
    View ll_updatepwd;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memessage);
        initView();
    }
    void initView(){
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ll_nickname=findViewById(R.id.ll_nickname);
        ll_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MeMessageActivity.this,UpdateNickActivity.class);
                startActivity(intent);
            }
        });
        ll_bindphone=findViewById(R.id.ll_bindphone);
        ll_bindphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MeMessageActivity.this,BindPhoneActivity.class);
                startActivity(intent);
            }
        });
        ll_updatepwd=findViewById(R.id.ll_updatepwd);
        ll_updatepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MeMessageActivity.this,UpdatePasswordFirstActivity.class);
                startActivity(intent);
            }
        });
    }
}
