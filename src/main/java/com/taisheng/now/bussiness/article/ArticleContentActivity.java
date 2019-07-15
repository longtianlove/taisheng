package com.taisheng.now.bussiness.article;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.post.CollectAddorRemovePostBean;
import com.taisheng.now.bussiness.bean.result.ArticleContentBean;
import com.taisheng.now.bussiness.bean.post.ArticleContentPostBean;
import com.taisheng.now.bussiness.bean.result.CollectAddorRemoveResultBean;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.util.DoubleClickUtil;
import com.zzhoujay.richtext.RichText;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dragon on 2019/7/8.
 */

public class ArticleContentActivity extends BaseActivity {


    View iv_back;
    TextView tv_title;
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
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);

        ll_collect = findViewById(R.id.ll_collect);
        ll_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (DoubleClickUtil.isFastMiniDoubleClick()) {
                    return;
                }
                CollectAddorRemovePostBean bean = new CollectAddorRemovePostBean();
                bean.userId = UserInstance.getInstance().getUid();
                bean.token = UserInstance.getInstance().getToken();
                bean.collectionType = "2";
                bean.dataId = articleId;
                ApiUtils.getApiService().collectionaddOrRemove(bean).enqueue(new TaiShengCallback<BaseBean<CollectAddorRemoveResultBean>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<CollectAddorRemoveResultBean>> response, BaseBean<CollectAddorRemoveResultBean> message) {
                        switch (message.code) {
                            case Constants.HTTP_SUCCESS:
                                String resultFeedback = message.result.resultFeedback;
                                if ("0".equals(resultFeedback)) {
                                    collectionFlag = "NO";
                                    tv_collect.setEnabled(false);
                                    tv_collect_label.setText("  收藏");
                                } else {
                                    collectionFlag = "YES";
                                    tv_collect.setEnabled(true);
                                    tv_collect_label.setText("已收藏");
                                }
                                break;
                        }
                    }

                    @Override
                    public void onFail(Call<BaseBean<CollectAddorRemoveResultBean>> call, Throwable t) {

                    }
                });
            }
        });
        tv_collect = (TextView) findViewById(R.id.tv_collect);
        tv_collect_label = (TextView) findViewById(R.id.tv_collect_label);
        tv_typename = (TextView) findViewById(R.id.tv_typename);
        tv_time = (TextView) findViewById(R.id.tv_time);
    }


    String collectionFlag;
    String articleId;

    void initData() {
        Intent intent = getIntent();
        articleId = intent.getStringExtra("articleId");
        ArticleContentPostBean bean = new ArticleContentPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.articleId = articleId;
        ApiUtils.getApiService().articleQeryById(bean).enqueue(new TaiShengCallback<BaseBean<ArticleContentBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArticleContentBean>> response, BaseBean<ArticleContentBean> message) {
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        tv_title.setText(message.result.title);
//                        tv_content.setText(message.result.content);
                        if (message.result.content != null) {
//                            tv_content.setMovementMethod(LinkMovementMethod.getInstance());
//                            RichText.fromHtml(message.result.content).into(tv_content);
                            try {
                                tv_content.setMovementMethod(LinkMovementMethod.getInstance());
                                RichText.fromHtml(message.result.content).into(tv_content);

                            } catch (Exception e) {
                                Log.e("article", e.getMessage());
                            }
                        }
                        collectionFlag = message.result.collectionFlag;
                        if ("YES".equals(message.result.collectionFlag)) {
                            tv_collect.setEnabled(true);
                            tv_collect_label.setText("已收藏");
                        } else {
                            tv_collect.setEnabled(false);
                            tv_collect_label.setText("  收藏");
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
