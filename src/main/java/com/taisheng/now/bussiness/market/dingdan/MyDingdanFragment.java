package com.taisheng.now.bussiness.market.dingdan;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.base.BaseFragment;
import com.taisheng.now.bussiness.bean.post.KanjuanPostBean;
import com.taisheng.now.bussiness.bean.post.OrderListPostBean;
import com.taisheng.now.bussiness.bean.result.MallYouhuiquanBean;
import com.taisheng.now.bussiness.bean.result.MallYouhuiquanResultBanner;
import com.taisheng.now.bussiness.bean.result.market.OrderListResultBean;
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

//    YouhuiquanAdapter madapter;

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
//        initData();
        return rootView;
    }

    void initView(View rootView) {
        list_kajuan = (TaishengListView) rootView.findViewById(R.id.list_kajuan);


//        madapter = new YouhuiquanAdapter(getContext());
//        list_kajuan.setAdapter(madapter);
        list_kajuan.setOnUpLoadListener(new TaishengListView.OnUpLoadListener() {
            @Override
            public void onUpLoad() {
//                getDoctors();
            }
        });

    }

//    void initData() {
//        getDoctors();
//    }


    int PAGE_NO = 1;
    int PAGE_SIZE = 10;

//    void getDoctors() {
//        OrderListPostBean bean = new OrderListPostBean();
//        bean.userId = UserInstance.getInstance().getUid();
//        bean.token = UserInstance.getInstance().getToken();
//        switch (assessmentType){
//            case "1":
//                bean.status=1;
//                break;
//            case "2":
//                bean.status=2;
//                break;
//            case "3":
//                bean.status=3;
//                break;
//            case "4":
//                bean.status=4;
//                break;
//
//        }
//        DialogUtil.showProgress(getActivity(), "");
//
//        ApiUtils.getApiService().orderList(bean).enqueue(new TaiShengCallback<BaseBean<OrderListResultBean>>() {
//            @Override
//            public void onSuccess(Response<BaseBean<OrderListResultBean>> response, BaseBean<OrderListResultBean> message) {
////                ptr_refresh.refreshComplete();
//                DialogUtil.closeProgress();
//                switch (message.code) {
//                    case Constants.HTTP_SUCCESS:
//                        if (message.result.records != null && message.result.records.size() > 0) {
//                            list_kajuan.setLoading(false);
//                            if (PAGE_NO == 1) {
//                                madapter.mData.clear();
//                            }
//                            //有消息
////                            PAGE_NO++;
//                            madapter.mData.addAll(message.result.records);
//                            if (message.result.records.size() < 10) {
//                                list_kajuan.setHasLoadMore(false);
//                                list_kajuan.setLoadAllViewText("暂时只有这么多优惠券");
//                                list_kajuan.setLoadAllFooterVisible(true);
//                            } else {
//                                list_kajuan.setHasLoadMore(true);
//                            }
//                            madapter.notifyDataSetChanged();
//                        } else {
//                            //没有消息
//                            list_kajuan.setHasLoadMore(false);
//                            list_kajuan.setLoadAllViewText("暂时只有这么多优惠券");
//                            list_kajuan.setLoadAllFooterVisible(true);
//                        }
//                        break;
//                }
//            }
//
//            @Override
//            public void onFail(Call<BaseBean<OrderListResultBean>> call, Throwable t) {
////                ptr_refresh.refreshComplete();
//                DialogUtil.closeProgress();
//            }
//        });
//
//
//    }


}
