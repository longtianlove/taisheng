package com.taisheng.now.bussiness.me;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;

/**
 * Created by dragon on 2019/6/28.
 */

public class UpdateNickActivity extends BaseActivity {
    View iv_back;
    EditText et_nickname;
    ImageView iv_nickname_guanbi;
    View btn_update;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatenickname);
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
        btn_update=findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        et_nickname= (EditText) findViewById(R.id.et_nickname);
        et_nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    iv_nickname_guanbi.setVisibility(View.VISIBLE);
                }else{
                    iv_nickname_guanbi.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iv_nickname_guanbi= (ImageView) findViewById(R.id.iv_nickname_guanbi);
        iv_nickname_guanbi.setVisibility(View.INVISIBLE);
        iv_nickname_guanbi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                et_nickname.setText("");
            }
        });

    }
}
