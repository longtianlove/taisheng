package com.taisheng.now.bussiness.market;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.bussiness.article.ArticleContentActivity;
import com.taisheng.now.bussiness.bean.result.ArticleBean;
import com.taisheng.now.bussiness.first.FirstFragment;
import com.taisheng.now.bussiness.me.FuwuxieyiActivity;
import com.taisheng.now.bussiness.me.YisixieyiActivity;
import com.taisheng.now.util.Apputil;
import com.taisheng.now.view.WithScrolleViewListView;
import com.taisheng.now.view.chenjinshi.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 2019/6/28.
 */

public class DizhiActivity extends Activity {
    View iv_back;


    WithScrolleViewListView  lv_dizhis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizhi);
        initView();
    }

    void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        lv_dizhis=findViewById(R.id.lv_dizhis);



    }

    class DizhiAdapter extends BaseAdapter {

        public Context mcontext;

        List<ArticleBean> mData = new ArrayList<ArticleBean>();

        public DizhiAdapter(Context context) {
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
            DizhiAdapter.Util util = null;
            // 中间变量
            final int flag = position;
            if (convertView == null) {
                util = new DizhiAdapter.Util();
                LayoutInflater inflater = LayoutInflater.from(mcontext);
                convertView = inflater.inflate(R.layout.item_dizhi, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);

                convertView.setTag(util);
            } else {
                util = (DizhiAdapter.Util) convertView.getTag();
            }
            ArticleBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DizhiActivity.this, ArticleContentActivity.class);
                    startActivity(intent);
                }
            });



            return convertView;
        }


        class Util {
            View ll_all;


        }
    }


    @Override
    protected void onStart() {
        super.onStart();
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
    }
}
