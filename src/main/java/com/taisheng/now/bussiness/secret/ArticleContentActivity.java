package com.taisheng.now.bussiness.secret;

import android.os.Bundle;
import android.widget.TextView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;

/**
 * Created by dragon on 2019/7/8.
 */

public class ArticleContentActivity extends BaseActivity {

    TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_content);
        initView();
    }
    void initView(){
        tv_content= (TextView) findViewById(R.id.tv_content);
    }
}
