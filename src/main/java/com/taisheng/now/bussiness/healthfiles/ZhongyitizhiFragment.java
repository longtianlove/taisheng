package com.taisheng.now.bussiness.healthfiles;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.base.BaseFragment;
import com.taisheng.now.bussiness.bean.post.HealthCheckListPostBean;
import com.taisheng.now.bussiness.bean.result.CheckHistoryBean;
import com.taisheng.now.bussiness.bean.result.CheckHistoryResultBean;
import com.taisheng.now.bussiness.bean.result.DoctorBean;
import com.taisheng.now.bussiness.doctor.DoctorDetailActivity;
import com.taisheng.now.bussiness.doctor.DoctorFragment;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.util.DialogUtil;
import com.taisheng.now.view.DoctorLabelWrapLayout;
import com.taisheng.now.view.ScoreStar;
import com.taisheng.now.view.TaishengListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dragon on 2019/7/1.
 */

public class ZhongyitizhiFragment extends BaseFragment {
    TaishengListView lv_history;
    CheckHistoryAdapter madapter;

    public String assessmentType;


    public TextView tv_completeBatch;
    public TextView tv_remarks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_zhongyitizhi, container, false);
        initView(rootView);
        initData();
        return rootView;
    }

    void initView(View rootView) {
        tv_completeBatch= (TextView) rootView.findViewById(R.id.tv_completeBatch);
        tv_remarks= (TextView) rootView.findViewById(R.id.tv_remarks);
        lv_history = (TaishengListView) rootView.findViewById(R.id.lv_history);
        madapter=new CheckHistoryAdapter(mActivity);
        lv_history.setAdapter(madapter);
        lv_history.setOnUpLoadListener(new TaishengListView.OnUpLoadListener() {
            @Override
            public void onUpLoad() {
                initData();
            }
        });

    }


    int PAGE_NO = 1;
    int PAGE_SIZE = 10;

    void initData() {

        HealthCheckListPostBean bean = new HealthCheckListPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.pageNo = PAGE_NO;
        bean.pageSize = PAGE_SIZE;
        bean.subjectdbType = "1";
        bean.assessmentType = assessmentType;
        DialogUtil.showProgress(mActivity, "");
        ApiUtils.getApiService().answerRecordList(bean).enqueue(new TaiShengCallback<BaseBean<CheckHistoryResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<CheckHistoryResultBean>> response, BaseBean<CheckHistoryResultBean> message) {
                DialogUtil.closeProgress();
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        if (message.result.records != null && message.result.records.size() > 0) {
                            lv_history.setLoading(false);

                            //有消息
                            PAGE_NO++;
                            madapter.mData.addAll(message.result.records);
                            if (message.result.records.size() < 10) {
                                lv_history.setHasLoadMore(false);
                                lv_history.setLoadAllViewText("暂时只有这么多结果");
                                lv_history.setLoadAllFooterVisible(true);
                            } else {
                                lv_history.setHasLoadMore(true);
                            }
                            madapter.notifyDataSetChanged();
                        } else {
                            //没有消息
                            lv_history.setHasLoadMore(false);
                            lv_history.setLoadAllViewText("暂时只有这么多结果");
                            lv_history.setLoadAllFooterVisible(true);
                        }
                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<CheckHistoryResultBean>> call, Throwable t) {
                DialogUtil.closeProgress();
            }
        });


    }

    class CheckHistoryAdapter extends BaseAdapter {

        public Context mcontext;

        List<CheckHistoryBean> mData = new ArrayList<CheckHistoryBean>();

        public CheckHistoryAdapter(Context context) {
            this.mcontext = context;
        }

        @Override
        public int getCount() {
            if (mData == null) {
                return 0;
            } else {
                return mData.size();
            }
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
                convertView = inflater.inflate(R.layout.item_healthcheckhistory, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_createTime = (TextView) convertView.findViewById(R.id.tv_createTime);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }


            CheckHistoryBean bean = mData.get(position);

            if(position==0){
                tv_completeBatch.setText(bean.completeBatch);
                tv_remarks.setText(bean.remarks);
            }
            util.ll_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, HealthCheckResultHistoryActivity.class);
                    intent.putExtra("completeBatch", bean.completeBatch);
                    intent.putExtra("remarks", bean.remarks);
                    startActivity(intent);
                }
            });
            util.tv_createTime.setText(bean.createTime);


            return convertView;
        }


        class Util {
            View ll_all;
            TextView tv_createTime;

        }
    }


}
