package com.taisheng.now.bussiness.healthfiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;

/**
 * Created by dragon on 2019/7/10.
 */

public class HealthCheckResultActivity extends BaseActivity {
    View iv_back;

    TextView tv_completeBatch;

    ProgressBar progressBar;

    TextView tv_remarks;

    ImageView iv_xinqing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check_result);
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

        tv_completeBatch= (TextView) findViewById(R.id.tv_completeBatch);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        tv_remarks= (TextView) findViewById(R.id.tv_remarks);
        iv_xinqing= (ImageView) findViewById(R.id.iv_xinqing);
    }

    void initData(){
        Intent intent=getIntent();
        String completeBatch=intent.getStringExtra("completeBatch");
        String remarks=intent.getStringExtra("remarks");
        int score=intent.getIntExtra("score",0);

        tv_completeBatch.setText(completeBatch);
        tv_remarks.setText(remarks);

        if(score<=40){
            iv_xinqing.setImageDrawable(getResources().getDrawable(R.drawable.check_result_ku));
        }else{
            iv_xinqing.setImageDrawable(getResources().getDrawable(R.drawable.check_result_weixiao));
        }

        progressBar.setProgress(score);
        if(score<=20){
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.myprogressbar20));
        }else if(score<=40){
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.myprogressbar40));
        }else if(score<=60){
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.myprogressbar60));
        }else if(score<=80){
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.myprogressbar80));
        }else{
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.myprogressbar100));

        }

    }
}
