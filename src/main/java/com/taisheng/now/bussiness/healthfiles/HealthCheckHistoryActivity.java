package com.taisheng.now.bussiness.healthfiles;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.base.BaseFragmentActivity;
import com.taisheng.now.view.chenjinshi.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 2019/6/28.
 */

public class HealthCheckHistoryActivity extends BaseFragmentActivity {
    View iv_back;


    TabLayout tl_tab;
    ViewPager vp_content;
    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_health_check_history);
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
        initView();
    }
    void initView(){
        iv_back=findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tl_tab= (TabLayout) findViewById(R.id.tl_tab);
        vp_content= (ViewPager) findViewById(R.id.vp_content);
        initContent();
        initTab();




    }
    private void initTab(){
        tl_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tl_tab.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.SelectedTabIndicatorColor));
        tl_tab.setTabTextColors(ContextCompat.getColor(this, R.color.UnSelectedTextColor), ContextCompat.getColor(this, R.color.SelectedTextColor));
        tl_tab.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
//        tl_tab.setTabTextColors(ContextCompat.getColor(this, R.color.gray), ContextCompat.getColor(this, R.color.white));
//        tl_tab.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));
//        ViewCompat.setElevation(tl_tab, 10);
        tl_tab.setupWithViewPager(vp_content);
    }
    ZhongyitizhiFragment zhongyitizhiFragment1;
    ZhongyitizhiFragment zhongyitizhiFragment2;
    ZhongyitizhiFragment zhongyitizhiFragment3;
    ZhongyitizhiFragment zhongyitizhiFragment4;
    ZhongyitizhiFragment zhongyitizhiFragment5;
    ZhongyitizhiFragment zhongyitizhiFragment6;

    private void initContent(){
        tabIndicators = new ArrayList<>();
        tabIndicators.add("中医体质");
        tabIndicators.add("基础代谢");
        tabIndicators.add("妇科健康");
        tabIndicators.add("心肺功能");
        tabIndicators.add("腰颈肩背");
        tabIndicators.add("脾胃肝肾");
//        for (int i = 0; i < 3; i++) {
//            tabIndicators.add("Tab " + i);
//        }
        tabFragments = new ArrayList<>();
        zhongyitizhiFragment1=new ZhongyitizhiFragment();
        zhongyitizhiFragment2=new ZhongyitizhiFragment();
        zhongyitizhiFragment3=new ZhongyitizhiFragment();
        zhongyitizhiFragment4=new ZhongyitizhiFragment();
        zhongyitizhiFragment5=new ZhongyitizhiFragment();
        zhongyitizhiFragment6=new ZhongyitizhiFragment();
        tabFragments.add(zhongyitizhiFragment1);
        tabFragments.add(zhongyitizhiFragment2);
        tabFragments.add(zhongyitizhiFragment3);
        tabFragments.add(zhongyitizhiFragment4);
        tabFragments.add(zhongyitizhiFragment5);
        tabFragments.add(zhongyitizhiFragment6);
//        for (String s : tabIndicators) {
////            tabFragments.add(TabContentFragment.newInstance(s));
//        }
        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        vp_content.setAdapter(contentAdapter);
    }


    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }
}
