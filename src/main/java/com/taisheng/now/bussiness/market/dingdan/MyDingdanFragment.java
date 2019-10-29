package com.taisheng.now.bussiness.market.dingdan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.base.BaseFragment;
import com.taisheng.now.bussiness.bean.post.KanjuanPostBean;
import com.taisheng.now.bussiness.bean.post.OrderListPostBean;
import com.taisheng.now.bussiness.bean.result.JifenzhuanquBean;
import com.taisheng.now.bussiness.bean.result.MallYouhuiquanBean;
import com.taisheng.now.bussiness.bean.result.MallYouhuiquanResultBanner;
import com.taisheng.now.bussiness.bean.result.market.OrderBean;
import com.taisheng.now.bussiness.bean.result.market.OrderListResultBean;
import com.taisheng.now.bussiness.market.ShangPinxiangqingActivity;
import com.taisheng.now.bussiness.market.youhuijuan.KanjuanFragment;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.util.DialogUtil;
import com.taisheng.now.view.TaishengListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MyDingdanFragment extends BaseFragment {

    public String assessmentType;


    //    MaterialDesignPtrFrameLayout ptr_refresh;
    TaishengListView list_kajuan;

    DingdanAdapter madapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_dingdandaifukuan, container, false);

        switch (assessmentType) {
            case "1":
                rootView = inflater.inflate(R.layout.fragment_dingdandaifukuan, container, false);
                break;
            case "2":
                rootView = inflater.inflate(R.layout.fragment_dingdandaifahuo, container, false);
                break;
            case "3":
                rootView = inflater.inflate(R.layout.fragment_dingdandaisouhuo, container, false);
                break;
            case "4":
                rootView = inflater.inflate(R.layout.fragment_dingdanyiwancheng, container, false);
                break;

        }

        initView(rootView);
        initData();
        return rootView;
    }

    void initView(View rootView) {
        list_kajuan = (TaishengListView) rootView.findViewById(R.id.list_kajuan);


        madapter = new DingdanAdapter(getContext());
        list_kajuan.setAdapter(madapter);
        list_kajuan.setOnUpLoadListener(new TaishengListView.OnUpLoadListener() {
            @Override
            public void onUpLoad() {
                getDoctors();
            }
        });

    }

    void initData() {
        getDoctors();
    }


    int PAGE_NO = 1;
    int PAGE_SIZE = 10;

    void getDoctors() {
        OrderListPostBean bean = new OrderListPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        switch (assessmentType) {
            case "1":
                bean.status = 1;
                break;
            case "2":
                bean.status = 2;
                break;
            case "3":
                bean.status = 3;
                break;
            case "4":
                bean.status = 4;
                break;

        }
        DialogUtil.showProgress(getActivity(), "");

        ApiUtils.getApiService().orderList(bean).enqueue(new TaiShengCallback<BaseBean<OrderListResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<OrderListResultBean>> response, BaseBean<OrderListResultBean> message) {
//                ptr_refresh.refreshComplete();
                DialogUtil.closeProgress();
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        if (message.result.records != null && message.result.records.size() > 0) {
                            list_kajuan.setLoading(false);
                            if (PAGE_NO == 1) {
                                madapter.mData.clear();
                            }
                            //有消息
//                            PAGE_NO++;
                            madapter.mData.addAll(message.result.records);
                            if (message.result.records.size() < 10) {
                                list_kajuan.setHasLoadMore(false);
                                list_kajuan.setLoadAllViewText("暂时只有这么多商品");
                                list_kajuan.setLoadAllFooterVisible(true);
                            } else {
                                list_kajuan.setHasLoadMore(true);
                            }
                            madapter.notifyDataSetChanged();
                        } else {
                            //没有消息
                            list_kajuan.setHasLoadMore(false);
                            list_kajuan.setLoadAllViewText("暂时只有这么多商品");
                            list_kajuan.setLoadAllFooterVisible(true);
                        }
                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<OrderListResultBean>> call, Throwable t) {
//                ptr_refresh.refreshComplete();
                DialogUtil.closeProgress();
            }
        });


    }

    class DingdanAdapter extends BaseAdapter {

        public Context mcontext;

        List<OrderBean> mData = new ArrayList<OrderBean>();

        public DingdanAdapter(Context context) {
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
                convertView = inflater.inflate(R.layout.item_dingdan, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.sdv_article = convertView.findViewById(R.id.sdv_article);
                util.tv_name = convertView.findViewById(R.id.tv_name);
                util.tv_counterprice = convertView.findViewById(R.id.tv_counterprice);
                util.tv_number=convertView.findViewById(R.id.tv_number);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            OrderBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ShangPinxiangqingActivity.class);
                    intent.putExtra("goodsid", bean.id);

                    startActivity(intent);
                }
            });

            String temp_url = bean.picUrl;
            if (temp_url == null || "".equals(temp_url)) {
                util.sdv_article.setBackgroundResource(R.drawable.article_default);

            } else {
                Uri uri = Uri.parse(temp_url);
                util.sdv_article.setImageURI(uri);
            }
            util.tv_name.setText(bean.goodsName);
            util.tv_counterprice.setText(bean.price + "");

            util.tv_number.setText("x "+bean.number);
            return convertView;
        }


        class Util {
            View ll_all;
            SimpleDraweeView sdv_article;
            TextView tv_name;
            TextView tv_counterprice;
            TextView tv_number;
        }
    }
}
