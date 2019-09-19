package com.taisheng.now.bussiness.me;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.util.Apputil;

/**
 * Created by dragon on 2019/6/28.
 */

public class QuqiandaoActivity extends BaseActivity {
    View iv_back;

    SimpleDraweeView sdv_header;
    TextView tv_nickname;
    TextView tv_jifen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quqiandao);
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
        tv_nickname=findViewById(R.id.tv_nickname);
        tv_jifen=findViewById(R.id.tv_jifen);



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

}
