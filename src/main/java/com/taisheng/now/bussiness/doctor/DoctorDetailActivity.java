package com.taisheng.now.bussiness.doctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.post.CollectAddorRemovePostBean;
import com.taisheng.now.bussiness.bean.post.DoctorCommentPostBean;
import com.taisheng.now.bussiness.bean.post.DoctorNumberPostBean;
import com.taisheng.now.bussiness.bean.post.GuanzhuPostBean;
import com.taisheng.now.bussiness.bean.result.CollectAddorRemoveResultBean;
import com.taisheng.now.bussiness.bean.result.DoctorCommentBean;
import com.taisheng.now.bussiness.bean.result.DoctorCommentResultBean;
import com.taisheng.now.bussiness.bean.result.DoctorNumberResultBean;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.shipin.TRTCMainActivity;
import com.taisheng.now.util.DialogUtil;
import com.taisheng.now.util.DoubleClickUtil;
import com.taisheng.now.view.DoctorLabelWrapLayout;
import com.taisheng.now.view.ScoreStar;
import com.taisheng.now.view.StarGrade;
import com.taisheng.now.view.TaishengListView;
import com.taisheng.now.view.chenjinshi.StatusBarUtil;
import com.tencent.trtc.TRTCCloudDef;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dragon on 2019/7/1.
 */

public class DoctorDetailActivity extends Activity {

    View iv_back;

    TextView tv_doctor_name;
    TextView tv_title;
    TextView tv_workage;
    ScoreStar scorestar;
    TextView tv_jobintroduce;
    DoctorLabelWrapLayout dlwl_doctor_label;


    TextView tv_servicenumber;
    TextView tv_comment;
    TextView tv_guanzu;


    TextView tv_comment2;

    View ll_collect;
    TextView tv_collect_label;
    TextView tv_collect_show;
    View ll_zixun;
    TaishengListView lv_comments;
    DoctorCommentAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //沉浸式代码配置
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        //用来设置整体下移，状态栏沉浸
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        setContentView(R.layout.activity_doctor_detail);


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


        tv_doctor_name = (TextView) findViewById(R.id.tv_doctor_name);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_workage = (TextView) findViewById(R.id.tv_workage);
        scorestar = (ScoreStar) findViewById(R.id.scorestar);

        tv_jobintroduce = (TextView) findViewById(R.id.tv_jobintroduce);
        dlwl_doctor_label = (DoctorLabelWrapLayout) findViewById(R.id.dlwl_doctor_label);

        tv_servicenumber = (TextView) findViewById(R.id.tv_servicenumber);
        tv_comment = (TextView) findViewById(R.id.tv_comment);
        tv_guanzu = (TextView) findViewById(R.id.tv_guanzu);
        tv_comment2 = (TextView) findViewById(R.id.tv_comment2);

        lv_comments = (TaishengListView) findViewById(R.id.lv_comments);
        madapter = new DoctorCommentAdapter(this);
        lv_comments.setAdapter(madapter);
        lv_comments.setOnUpLoadListener(new TaishengListView.OnUpLoadListener() {
            @Override
            public void onUpLoad() {
                getDoctorComment();
            }
        });


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
                bean.collectionType = "1";
                bean.dataId = doctorId;
                ApiUtils.getApiService().collectionaddOrRemove(bean).enqueue(new TaiShengCallback<BaseBean<CollectAddorRemoveResultBean>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<CollectAddorRemoveResultBean>> response, BaseBean<CollectAddorRemoveResultBean> message) {
                        switch (message.code) {
                            case Constants.HTTP_SUCCESS:
                                String resultFeedback = message.result.resultFeedback;
                                if ("0".equals(resultFeedback)) {
                                    tv_collect_label.setEnabled(false);
                                    tv_collect_show.setText("收藏");
                                } else {
                                    tv_collect_label.setEnabled(true);
                                    tv_collect_show.setText("已收藏");
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
        tv_collect_label = (TextView) findViewById(R.id.tv_collect_label);
        tv_collect_show = (TextView) findViewById(R.id.tv_collect_show);
        ll_zixun = findViewById(R.id.ll_zixun);
        ll_zixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DoubleClickUtil.isFastMiniDoubleClick()) {
                    return;
                }
                DialogUtil.showzixunDialog(DoctorDetailActivity.this,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //todo 修改用户名和房间号
                                onJoinRoom(113355, "Android_trtc_04");

                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //todo 修改用户名和房间号
                                onJoinRoom(113355, "Android_trtc_04");
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }
                );
            }
        });
    }


    void initData() {
        Intent intent = getIntent();
        doctorId = intent.getStringExtra("id");
        String nickName = intent.getStringExtra("nickName");
        tv_doctor_name.setText(nickName);
        String title = intent.getStringExtra("title");
        tv_title.setText(title);
        String fromMedicineTime = intent.getStringExtra("fromMedicineTime");
        tv_workage.setText(getWorkYear(fromMedicineTime));
        String jobIntroduction = intent.getStringExtra("jobIntroduction");
        tv_jobintroduce.setText(jobIntroduction);

        String goodDiseases = intent.getStringExtra("goodDiseases");
        if (goodDiseases != null) {
            String[] doctorlabel = goodDiseases.split(",");
            dlwl_doctor_label.setData(doctorlabel, DoctorDetailActivity.this, 10, 5, 1, 5, 1, 4, 0, 4, 0);
        }
        String score = intent.getStringExtra("score");
        scorestar.setScore(score);


        PAGE_NO = 1;
        PAGE_SIZE = 10;
        bean = new DoctorCommentPostBean();
        getDoctorComment();
        getServiceNumber();
        getBeCommentedNum();
        getBeDoctorAttentionNum();
    }

    String getWorkYear(String fromMedicineTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            ParsePosition pos = new ParsePosition(0);
        Date strtodate = null;
        try {
            strtodate = formatter.parse(fromMedicineTime);
            Date currentTime = new Date();
            return currentTime.getYear() - strtodate.getYear() <= 0 ? "1" : currentTime.getYear() - strtodate.getYear() + "";

        } catch (Exception e) {
            Log.e("firstfragment-getwork", e.getMessage());
            return "1";
        }


    }


    void getServiceNumber() {
        DoctorNumberPostBean bean = new DoctorNumberPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.id = doctorId;
        ApiUtils.getApiService().getDoctorServerNum(bean).enqueue(new TaiShengCallback<BaseBean<DoctorNumberResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<DoctorNumberResultBean>> response, BaseBean<DoctorNumberResultBean> message) {
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        tv_servicenumber.setText(message.result.countNum + "");
                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<DoctorNumberResultBean>> call, Throwable t) {

            }
        });
    }

    void getBeCommentedNum() {
        DoctorNumberPostBean bean = new DoctorNumberPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.id = doctorId;
        ApiUtils.getApiService().getBeCommentedNum(bean).enqueue(new TaiShengCallback<BaseBean<DoctorNumberResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<DoctorNumberResultBean>> response, BaseBean<DoctorNumberResultBean> message) {
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        tv_comment.setText(message.result.countNum + "");
                        tv_comment2.setText(message.result.countNum + "");
                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<DoctorNumberResultBean>> call, Throwable t) {

            }
        });
    }

    void getBeDoctorAttentionNum() {
        GuanzhuPostBean bean = new GuanzhuPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.dataId = doctorId;
        bean.collectionType = "1";
        ApiUtils.getApiService().getBeDoctorAttentionNum(bean).enqueue(new TaiShengCallback<BaseBean<DoctorNumberResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<DoctorNumberResultBean>> response, BaseBean<DoctorNumberResultBean> message) {
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        tv_guanzu.setText(message.result.countNum + "");
                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<DoctorNumberResultBean>> call, Throwable t) {

            }
        });

    }

    DoctorCommentPostBean bean;
    String doctorId;
    int PAGE_NO = 1;
    int PAGE_SIZE = 10;


    void getDoctorComment() {
        bean.pageNo = PAGE_NO;
        bean.pageSize = PAGE_SIZE;
        bean.token = UserInstance.getInstance().getToken();
        bean.userId = UserInstance.getInstance().getUid();
        bean.doctorId = doctorId;
        DialogUtil.showProgress(this, "");
        ApiUtils.getApiService().doctorScoreList(bean).enqueue(new TaiShengCallback<BaseBean<DoctorCommentResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<DoctorCommentResultBean>> response, BaseBean<DoctorCommentResultBean> message) {
                DialogUtil.closeProgress();
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:

                        if (message.result == null) {
                            return;
                        }
                        if (message.result.records != null && message.result.records.size() > 0) {
                            //有消息
                            PAGE_NO++;
                            madapter.mData.addAll(message.result.records);
                            if (message.result.records.size() < 10) {
                                lv_comments.setHasLoadMore(false);
                                lv_comments.setLoadAllViewText("暂时只有这么多评论");
                                lv_comments.setLoadAllFooterVisible(true);
                            } else {
                                lv_comments.setHasLoadMore(true);
                            }
                            madapter.notifyDataSetChanged();
                        } else {
                            //没有消息
                            lv_comments.setHasLoadMore(false);
                            lv_comments.setLoadAllViewText("暂时只有这么多评论");
                            lv_comments.setLoadAllFooterVisible(true);
                        }

                        break;

                }
            }

            @Override
            public void onFail(Call<BaseBean<DoctorCommentResultBean>> call, Throwable t) {
                DialogUtil.closeProgress();

            }
        });
    }


    class DoctorCommentAdapter extends BaseAdapter {

        public Context mcontext;

        List<DoctorCommentBean> mData = new ArrayList<DoctorCommentBean>();

        public DoctorCommentAdapter(Context context) {
            this.mcontext = context;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 声明内部类
            Util util = null;
            // 中间变量
            final int flag = position;
            if (convertView == null) {
                util = new Util();
                LayoutInflater inflater = LayoutInflater.from(mcontext);
                convertView = inflater.inflate(R.layout.item_comment, null);
                util.sdv_header = (SimpleDraweeView) convertView.findViewById(R.id.sdv_header);
                util.tv_nickname = (TextView) convertView.findViewById(R.id.tv_nickname);
                util.tv_createTime = (TextView) convertView.findViewById(R.id.tv_createTime);
                util.starGrade = (StarGrade) convertView.findViewById(R.id.starGrade);
                util.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
//                util.tv_createtime= (TextView) convertView.findViewById(R.id.tv_createtime);

                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            DoctorCommentBean bean = mData.get(position);


            String temp_url = Constants.Url.Host + bean.avatar;
            if (bean.avatar == null || "".equals(bean.avatar)) {
                //todo 默认头像
                util.sdv_header.setBackgroundResource(R.drawable.article_default);

            } else {
                Uri uri = Uri.parse(temp_url);
                util.sdv_header.setImageURI(uri);
            }
            util.tv_nickname.setText(bean.nickName);
            util.tv_createTime.setText(bean.createTime);
            util.starGrade.setScore(bean.consultationScore);
            util.tv_content.setText(bean.content);
            return convertView;
        }


        class Util {
            SimpleDraweeView sdv_header;
            TextView tv_nickname;
            TextView tv_createTime;

            com.taisheng.now.view.StarGrade starGrade;
            TextView tv_content;

        }
    }


    /**
     * Function: 读取用户输入，并创建（或加入）音视频房间
     * <p>
     * 此段示例代码最主要的作用是组装 TRTC SDK 进房所需的 TRTCParams
     * <p>
     * TRTCParams.sdkAppId => 可以在腾讯云实时音视频控制台（https://console.cloud.tencent.com/rav）获取
     * TRTCParams.userId   => 此处即用户输入的用户名，它是一个字符串
     * TRTCParams.roomId   => 此处即用户输入的音视频房间号，比如 125
     * TRTCParams.userSig  => 此处示例代码展示了两种获取 usersig 的方式，一种是从【控制台】获取，一种是从【服务器】获取
     * <p>
     * （1）控制台获取：可以获得几组已经生成好的 userid 和 usersig，他们会被放在一个 json 格式的配置文件中，仅适合调试使用
     * （2）服务器获取：直接在服务器端用我们提供的源代码，根据 userid 实时计算 usersig，这种方式安全可靠，适合线上使用
     * <p>
     * 参考文档：https://cloud.tencent.com/document/product/647/17275
     */
    private String mUserId = "";
    private String mUserSig = "";

    private void onJoinRoom(final int roomId, final String userId) {
        final Intent intent = new Intent(DoctorDetailActivity.this, TRTCMainActivity.class);

        intent.putExtra("roomId", roomId);
        intent.putExtra("userId", userId);
        intent.putExtra("AppScene", TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL);

        //todo 需要修改
        int finalSdkAppId = 1400227841;
        intent.putExtra("sdkAppId", finalSdkAppId);
        intent.putExtra("userSig", "eJxlj11PgzAARd-5FYRXjfYDVmbiA5Lqlm1mAiPqS4O0QMUV7CqbGP*7iksk8b6ek3tzPyzbtp1kGZ9led68KcPMeysc*8J2gHP6B9tWcpYZhjX-B8WhlVqwrDBCDxB6nocAGDuSC2VkIY9GoLhuvhuNNjkD7kjc8ZoNa79NLgAIEd*FY0WWA1zRTTi-eZl2NHrF3WQe0mR2HUN4fts-rmYBT6l37y9wlWi33-P0OZA0OCwUqTfpA7jbNoWp1*RkXy6n-MrE1ZMKItpXIkQR0QkuL0eTRm7F8doEEexjn4xoJ-RONmoQEIAeRBj8xLE*rS9DC2A5");
        startActivity(intent);


//                mUserInfoLoader.getUserSigFromServer(finalSdkAppId, roomId, userId, "12345678", new TRTCGetUserIDAndUserSig.IGetUserSigListener() {
//                    @Override
//                    public void onComplete(String userSig, String errMsg) {
//                        if (!TextUtils.isEmpty(userSig)) {
//                            intent.putExtra("sdkAppId", finalSdkAppId);
//                            intent.putExtra("userSig", userSig);
////                            saveUserInfo(String.valueOf(roomId), userId, userSig);
//                            startActivity(intent);
//                        } else {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(DoctorDetailActivity.this, "从服务器获取userSig失败", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    }
//                });

    }
}









