package com.taisheng.now.bussiness.doctor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;
import com.taisheng.now.bussiness.bean.result.DoctorBean;
import com.taisheng.now.view.TaishengListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/6/12.
 */

@SuppressLint("WrongConstant")
public class DoctorFragment extends BaseFragment {


    TaishengListView lv_doctors;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_doctor, container, false);
        initView(rootView);


//        EventBus.getDefault().register(this);
        initData();

        return rootView;
    }

    void initView(View rootView) {
        lv_doctors= (TaishengListView) rootView.findViewById(R.id.lv_doctors);
    }

    void initData() {


    }

    class DoctorAdapter extends BaseAdapter{

        public Context mcontext;

        List<DoctorBean> mData = new ArrayList<DoctorBean>();

        public DoctorAdapter(Context context) {
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
            convertView = inflater.inflate(R.layout.item_doctors, null);

            convertView.setTag(util);
        } else {
            util = (Util) convertView.getTag();
        }
            DoctorBean bean = mData.get(position);


        return convertView;
    }


        class Util {
            //todo 列表
//            TextView tv_time;
//            TextView tv_content;
        }
    }


    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }
}
