package com.taisheng.now.bussiness.me;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.view.dialog.BleedDialog;

/**
 * Created by dragon on 2019/7/9.
 */

public class FillInMessageSecondActivity extends BaseActivity implements BleedDialog.OnPickNumberListener {


    View ll_blood;
    TextView tv_blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillinmessagesecond);
        initView();
    }
    void initView(){
        ll_blood=findViewById(R.id.ll_blood);
        ll_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BleedDialog dialog = new BleedDialog(FillInMessageSecondActivity.this);
                dialog.setOnPickNumberListener(FillInMessageSecondActivity.this);
                dialog.show();
            }
        });
        tv_blood= (TextView) findViewById(R.id.tv_blood);
    }

    @Override
    public void onConfirmNumber(String number) {
        tv_blood.setText(number);
    }
}
