package com.taisheng.now.bussiness.healthfiles;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 2019/6/28.
 */

public class HealthFileSearchActivity extends BaseFragmentActivity {
    View iv_back;


    TabLayout tl_tab;
    ViewPager vp_content;
    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_health_file_search);

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
