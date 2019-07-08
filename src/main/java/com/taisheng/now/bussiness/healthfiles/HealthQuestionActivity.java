package com.taisheng.now.bussiness.healthfiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.post.QuestionPostBean;
import com.taisheng.now.bussiness.bean.result.QuestionResultBean;
import com.taisheng.now.bussiness.bean.result.Records;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dragon on 2019/6/28.
 */

public class HealthQuestionActivity extends BaseActivity {
    View iv_back;
    ProgressBar progressBar;
    TextView tv_now_postion;
    TextView tv_all_size;
    TextView tv_question;

    View ll_a;
    View ll_b;
    View ll_c;
    View ll_d;
    View ll_e;

    QuestionResultBean allBean;
    List<Records> records;
    Records question;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_question);
        initView();
        initData();
    }

    void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tv_now_postion = (TextView) findViewById(R.id.tv_now_postion);
        tv_all_size = (TextView) findViewById(R.id.tv_all_size);
        tv_question = (TextView) findViewById(R.id.tv_question);
        ll_a = findViewById(R.id.ll_a);
        ll_b = findViewById(R.id.ll_b);
        ll_c = findViewById(R.id.ll_c);
        ll_d = findViewById(R.id.ll_d);
        ll_e = findViewById(R.id.ll_e);


    }

    String assessmentType;
    String subjectdbType;

    void initData() {
        Intent intent = getIntent();
        assessmentType = intent.getStringExtra("assessmentType");
        subjectdbType = "1";
        QuestionPostBean bean = new QuestionPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.assessmentType = assessmentType;
        bean.subjectdbType = subjectdbType;
        bean.sign = "";
        ApiUtils.getApiService().getExtractionSubjectDb(bean).enqueue(new TaiShengCallback<BaseBean<QuestionResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<QuestionResultBean>> response, BaseBean<QuestionResultBean> message) {
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        allBean = message.result;
                        records = allBean.records;
                        updateView();
                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<QuestionResultBean>> call, Throwable t) {

            }
        });

    }

    int position = 0;

    void updateView() {
        question = records.get(position);
        updatePosition(position);
        updateAnswer();
    }

    void updatePosition(int i) {
        progressBar.setProgress((i * 100) / allBean.size);
        if (i < 10) {
            tv_now_postion.setText("0" + i);
        } else {
            tv_now_postion.setText(i);
        }
        tv_all_size.setText("/" + allBean.size);
        tv_question.setText(question.name);
    }

    void updateAnswer() {
        switch (question.assessmentOptionsList.size()) {
            case 1:
                ll_a.setVisibility(View.VISIBLE);

                ll_b.setVisibility(View.GONE);
                ll_c.setVisibility(View.GONE);
                ll_d.setVisibility(View.GONE);
                ll_e.setVisibility(View.GONE);
                break;
        }
    }
}
