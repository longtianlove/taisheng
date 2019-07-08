package com.taisheng.now.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.taisheng.now.R;
import com.taisheng.now.bussiness.bean.result.ArticleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 2019/6/29.
 */

public class ArticleAdapter  extends BaseAdapter {

    public Context mcontext;

    List<ArticleBean> mData = new ArrayList<ArticleBean>();

    public ArticleAdapter(Context context) {
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
            convertView = inflater.inflate(R.layout.item_article, null);
//            util.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
//            util.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(util);
        } else {
            util = (Util) convertView.getTag();
        }
        ArticleBean bean = mData.get(position);

//        util.tv_time.setText(bean.time);
//        util.tv_content.setText(bean.content);
        return convertView;
    }


    class Util {
//        TextView tv_time;
//        TextView tv_content;
    }
}