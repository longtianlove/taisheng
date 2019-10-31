package com.taisheng.now.bussiness.market.dingdan;

import android.content.Context;
import android.content.Intent;
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
import com.taisheng.now.bussiness.bean.post.OrderListPostBean;
import com.taisheng.now.bussiness.bean.result.market.OrderBean;
import com.taisheng.now.bussiness.bean.result.market.OrderListResultBean;
import com.taisheng.now.bussiness.market.ShangPinxiangqingActivity;
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
    TaishengListView lv_dingdan;

    DingdanAdapter madapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_dingdan, container, false);


        initView(rootView);
        initData();
        return rootView;
    }

    void initView(View rootView) {
        lv_dingdan = (TaishengListView) rootView.findViewById(R.id.lv_dingdan);


        madapter = new DingdanAdapter(getContext());
        lv_dingdan.setAdapter(madapter);
        lv_dingdan.setOnUpLoadListener(new TaishengListView.OnUpLoadListener() {
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
                            lv_dingdan.setLoading(false);
                            if (PAGE_NO == 1) {
                                madapter.mData.clear();
                            }
                            //有消息
//                            PAGE_NO++;
                            madapter.mData.addAll(message.result.records);
                            if (message.result.records.size() < 10) {
                                lv_dingdan.setHasLoadMore(false);
                                lv_dingdan.setLoadAllViewText("暂时只有这么多商品");
                                lv_dingdan.setLoadAllFooterVisible(true);
                            } else {
                                lv_dingdan.setHasLoadMore(true);
                            }
                            madapter.notifyDataSetChanged();
                        } else {
                            //没有消息
                            lv_dingdan.setHasLoadMore(false);
                            lv_dingdan.setLoadAllViewText("暂时只有这么多商品");
                            lv_dingdan.setLoadAllFooterVisible(true);
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
            switch (assessmentType) {
                case "1":
// 声明内部类
                    Util util = null;
                    // 中间变量
                    final int flag = position;
                    if (convertView == null) {
                        util = new Util();
                        LayoutInflater inflater = LayoutInflater.from(mcontext);


                        convertView = inflater.inflate(R.layout.item_dingdandaifukuan, null);
                        util.ll_all = convertView.findViewById(R.id.ll_all);
                        util.sdv_article = convertView.findViewById(R.id.sdv_article);
                        util.tv_name = convertView.findViewById(R.id.tv_name);
                        util.tv_counterprice = convertView.findViewById(R.id.tv_counterprice);
                        util.tv_number = convertView.findViewById(R.id.tv_number);
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

                    util.tv_number.setText("x " + bean.number);



                        break;

                    case "2":
                        // 声明内部类
                        Util util1 = null;
                        // 中间变量
                        final int flag1 = position;
                        if (convertView == null) {
                            util1 = new Util();
                            LayoutInflater inflater = LayoutInflater.from(mcontext);


                            convertView = inflater.inflate(R.layout.item_dingdandaifahuo, null);
                            util1.ll_all = convertView.findViewById(R.id.ll_all);
                            util1.sdv_article = convertView.findViewById(R.id.sdv_article);
                            util1.tv_name = convertView.findViewById(R.id.tv_name);
                            util1.tv_counterprice = convertView.findViewById(R.id.tv_counterprice);
                            util1.tv_number = convertView.findViewById(R.id.tv_number);
                            convertView.setTag(util1);
                        } else {
                            util1 = (Util) convertView.getTag();
                        }
                        OrderBean bean1 = mData.get(position);
                        util1.ll_all.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mActivity, ShangPinxiangqingActivity.class);
                                intent.putExtra("goodsid", bean1.id);

                                startActivity(intent);
                            }
                        });

                        String temp_url1 = bean1.picUrl;
                        if (temp_url1 == null || "".equals(temp_url1)) {
                            util1.sdv_article.setBackgroundResource(R.drawable.article_default);

                        } else {
                            Uri uri1 = Uri.parse(temp_url1);
                            util1.sdv_article.setImageURI(uri1);
                        }
                        util1.tv_name.setText(bean1.goodsName);
                        util1.tv_counterprice.setText(bean1.price + "");

                        util1.tv_number.setText("x " + bean1.number);

                        break;
                    case "3":
                        // 声明内部类
                        Util util2 = null;
                        // 中间变量
                        final int flag2 = position;
                        if (convertView == null) {
                            util2 = new Util();
                            LayoutInflater inflater = LayoutInflater.from(mcontext);


                            convertView = inflater.inflate(R.layout.item_dingdandaisouhuo, null);
                            util2.ll_all = convertView.findViewById(R.id.ll_all);
                            util2.sdv_article = convertView.findViewById(R.id.sdv_article);
                            util2.tv_name = convertView.findViewById(R.id.tv_name);
                            util2.tv_counterprice = convertView.findViewById(R.id.tv_counterprice);
                            util2.tv_number = convertView.findViewById(R.id.tv_number);
                            convertView.setTag(util2);
                        } else {
                            util2 = (Util) convertView.getTag();
                        }
                        OrderBean bean2 = mData.get(position);
                        util2.ll_all.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mActivity, ShangPinxiangqingActivity.class);
                                intent.putExtra("goodsid", bean2.id);

                                startActivity(intent);
                            }
                        });

                        String temp_url2 = bean2.picUrl;
                        if (temp_url2 == null || "".equals(temp_url2)) {
                            util2.sdv_article.setBackgroundResource(R.drawable.article_default);

                        } else {
                            Uri uri2 = Uri.parse(temp_url2);
                            util2.sdv_article.setImageURI(uri2);
                        }
                        util2.tv_name.setText(bean2.goodsName);
                        util2.tv_counterprice.setText(bean2.price + "");

                        util2.tv_number.setText("x " + bean2.number);
                        break;
                    case "4":
                        // 声明内部类
                        Util util3 = null;
                        // 中间变量
                        final int flag3 = position;
                        if (convertView == null) {
                            util3 = new Util();
                            LayoutInflater inflater = LayoutInflater.from(mcontext);


                            convertView = inflater.inflate(R.layout.item_dingdanyiwancheng, null);
                            util3.ll_all = convertView.findViewById(R.id.ll_all);
                            util3.sdv_article = convertView.findViewById(R.id.sdv_article);
                            util3.tv_name = convertView.findViewById(R.id.tv_name);
                            util3.tv_counterprice = convertView.findViewById(R.id.tv_counterprice);
                            util3.tv_number = convertView.findViewById(R.id.tv_number);
                            convertView.setTag(util3);
                        } else {
                            util3 = (Util) convertView.getTag();
                        }
                        OrderBean bean3 = mData.get(position);
                        util3.ll_all.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mActivity, ShangPinxiangqingActivity.class);
                                intent.putExtra("goodsid", bean3.id);

                                startActivity(intent);
                            }
                        });

                        String temp_url3 = bean3.picUrl;
                        if (temp_url3 == null || "".equals(temp_url3)) {
                            util3.sdv_article.setBackgroundResource(R.drawable.article_default);

                        } else {
                            Uri uri3 = Uri.parse(temp_url3);
                            util3.sdv_article.setImageURI(uri3);
                        }
                        util3.tv_name.setText(bean3.goodsName);
                        util3.tv_counterprice.setText(bean3.price + "");

                        util3.tv_number.setText("x " + bean3.number);
                        break;

                }

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
