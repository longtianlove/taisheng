package com.taisheng.now.bussiness.market.dingdan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.taisheng.now.R;
import com.taisheng.now.bussiness.market.dizhi.DizhiActivity;
import com.taisheng.now.view.chenjinshi.StatusBarUtil;

/**
 * Created by an on 2017/6/14.
 * 购物车界面
 */
public class DingdanjiesuanActivity extends Activity implements View.OnClickListener {

    View btnBack;


    View ll_dizhi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diandanjiesuan);
        initView();
    }

    private void initView() {

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);

        ll_dizhi = findViewById(R.id.ll_dizhi);
        ll_dizhi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DingdanjiesuanActivity.this, DizhiActivity.class);
                startActivity(intent);
            }
        });
        initData();
    }


    //初始化数据
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
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
