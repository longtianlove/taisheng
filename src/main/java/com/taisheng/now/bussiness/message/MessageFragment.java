package com.taisheng.now.bussiness.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;
import com.taisheng.now.bussiness.MainActivity;
import com.taisheng.now.bussiness.article.SecretSearchActivity;
import com.taisheng.now.bussiness.article.SecretTabFragment;
import com.taisheng.now.chat.C2CActivity;
import com.taisheng.now.chat.CircularCoverView;
import com.taisheng.now.chat.ColorUtils;
import com.taisheng.now.chat.CoreDB;
import com.taisheng.now.chat.HistoryBean;
import com.taisheng.now.chat.MLOC;
import com.taisheng.now.util.DensityUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;


/**
 * Created by Administrator on 2015/6/12.
 */

@SuppressLint("WrongConstant")
public class MessageFragment extends BaseFragment {

    private String mTargetId;
    private List<HistoryBean> mHistoryList;
    private ListView vHistoryList;
    private MyListAdapter listAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);

        initView(rootView);


//        EventBus.getDefault().register(this);
        initData();

        return rootView;
    }

    void initView(View rootView){
        mHistoryList = new ArrayList<>();
        listAdapter = new MyListAdapter();
        vHistoryList = (ListView) rootView.findViewById(R.id.history_list);
        vHistoryList.setAdapter(listAdapter);
        vHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MLOC.addHistory(mHistoryList.get(position),true);
                mTargetId = (String) mHistoryList.get(position).getConversationId();
                Intent intent = new Intent(getActivity(), C2CActivity.class);
                intent.putExtra("targetId",mTargetId);
                startActivity(intent);
            }
        });
    }

    void initData() {


    }



    @Override
    public void onResume(){
        super.onResume();
        MLOC.hasNewC2CMsg = false;
        mHistoryList.clear();
        List<HistoryBean> list = MLOC.getHistoryList(CoreDB.HISTORY_TYPE_C2C);
        if(list!=null&&list.size()>0){
            mHistoryList.addAll(list);
        }
        listAdapter.notifyDataSetChanged();
    }

    public class MyListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        public MyListAdapter(){
            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            if(mHistoryList!=null)
                return mHistoryList.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if(mHistoryList ==null)
                return null;
            return mHistoryList.get(position);
        }

        @Override
        public long getItemId(int position) {
            if(mHistoryList ==null)
                return 0;
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder itemSelfHolder;
            if(convertView == null){
                itemSelfHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_c2c_history,null);
                itemSelfHolder.vUserId = (TextView) convertView.findViewById(R.id.item_id);
                itemSelfHolder.vTime = (TextView) convertView.findViewById(R.id.item_time);
                itemSelfHolder.vMessage = (TextView) convertView.findViewById(R.id.item_msg);
                itemSelfHolder.vCount = (TextView) convertView.findViewById(R.id.item_count);
                itemSelfHolder.vHeadBg =  convertView.findViewById(R.id.head_bg);
                itemSelfHolder.vHeadImage = (ImageView) convertView.findViewById(R.id.head_img);
                itemSelfHolder.vHeadCover = (CircularCoverView) convertView.findViewById(R.id.head_cover);
                convertView.setTag(itemSelfHolder);
            }else{
                itemSelfHolder = (ViewHolder)convertView.getTag();
            }

            HistoryBean historyBean = mHistoryList.get(position);
            String userId = historyBean.getConversationId();
            itemSelfHolder.vUserId.setText(userId);
            itemSelfHolder.vHeadBg.setBackgroundColor(ColorUtils.getColor(getActivity(),userId));
            itemSelfHolder.vHeadCover.setCoverColor(Color.parseColor("#FFFFFF"));
            int cint = DensityUtil.dip2px(getActivity(),28);
            itemSelfHolder.vHeadCover.setRadians(cint, cint, cint, cint,0);
            itemSelfHolder.vHeadImage.setImageResource(MLOC.getHeadImage(getActivity(),userId));

            itemSelfHolder.vTime.setText(historyBean.getLastTime());
            itemSelfHolder.vMessage.setText(historyBean.getLastMsg());
            if(historyBean.getNewMsgCount()==0){
                itemSelfHolder.vCount.setVisibility(View.INVISIBLE);
            }else{
                itemSelfHolder.vCount.setText(""+historyBean.getNewMsgCount());
                itemSelfHolder.vCount.setVisibility(View.VISIBLE);
            }
            return convertView;
        }
    }

    public class ViewHolder{
        public TextView vUserId;
        public TextView vTime;
        public TextView vMessage;
        public TextView vCount;
        public View vHeadBg;
        public CircularCoverView vHeadCover;
        public ImageView vHeadImage;
    }



    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }
}
