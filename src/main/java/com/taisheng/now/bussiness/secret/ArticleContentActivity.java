package com.taisheng.now.bussiness.secret;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.ArticleContentBean;
import com.taisheng.now.bussiness.bean.ArticleContentPostBean;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dragon on 2019/7/8.
 */

public class ArticleContentActivity extends BaseActivity {

    TextView tv_content;
    View ll_collect;
    TextView tv_collect;
    TextView tv_collect_label;
    TextView tv_typename;
    TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_content);
        initView();
        initData();
    }

    void initView() {
        tv_content = (TextView) findViewById(R.id.tv_content);

        ll_collect=findViewById(R.id.ll_collect);
        ll_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("YES".equals(collectionFlag)){
                    collectionFlag="NO";
                    tv_collect.setEnabled(false);
                    tv_collect_label.setText(" 收藏");
                }else{
                    collectionFlag="YES";
                    tv_collect.setEnabled(true);
                    tv_collect_label.setText("已收藏");
                }


            }
        });
        tv_collect= (TextView) findViewById(R.id.tv_collect);
        tv_collect_label= (TextView) findViewById(R.id.tv_collect_label);
        tv_typename= (TextView) findViewById(R.id.tv_typename);
        tv_time= (TextView) findViewById(R.id.tv_time);
    }


    String collectionFlag;

    void initData() {
        Intent intent = getIntent();
        String articleId = intent.getStringExtra("articleId");
        ArticleContentPostBean bean = new ArticleContentPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.articleId = articleId;
        ApiUtils.getApiService().articleQeryById(bean).enqueue(new TaiShengCallback<BaseBean<ArticleContentBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArticleContentBean>> response, BaseBean<ArticleContentBean> message) {
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        tv_content.setText(message.result.content);
                        collectionFlag=message.result.collectionFlag;
                        if("YES".equals(message.result.collectionFlag)){
                            tv_collect.setEnabled(true);
                            tv_collect_label.setText("已收藏");
                        }else{
                            tv_collect.setEnabled(false);
                            tv_collect_label.setText("收藏该文章");
                        }
                        tv_typename.setText(message.result.typeName);
                        tv_time.setText(message.result.createTime);
                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArticleContentBean>> call, Throwable t) {

            }
        });
    }
}
