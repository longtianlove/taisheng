package com.taisheng.now.bussiness;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragmentActivity;
import com.taisheng.now.bussiness.doctor.DoctorFragment;
import com.taisheng.now.bussiness.first.FirstFragment;
import com.taisheng.now.bussiness.me.MeFragment;
import com.taisheng.now.bussiness.secret.SecretFragment;
import com.taisheng.now.util.SPUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;

@SuppressLint("WrongConstant")
public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

    private static int mTabID[] = {
            R.id.tab_first,
            R.id.tab_doctor,
            R.id.tab_secret,
            R.id.tab_me};

    private ImageView iv_tab_first, iv_tab_doctor, iv_tab_secret,iv_tab_me;
private TextView tv_tab_first,tv_tab_doctor,tv_tab_secret,tv_tab_me;

    private View mTabs[] = {null, null,null, null};

    private FirstFragment firstFragment;
    private DoctorFragment doctorFragment;
    private SecretFragment secretFragment;
    private MeFragment meFragment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        if (savedInstanceState != null) {
            firstFragment = (FirstFragment) getSupportFragmentManager().findFragmentByTag(FirstFragment.class.getName());
            doctorFragment = (DoctorFragment) getSupportFragmentManager().findFragmentByTag(DoctorFragment.class.getName());
            secretFragment= (SecretFragment) getSupportFragmentManager().findFragmentByTag(SecretFragment.class.getName());
            meFragment = (MeFragment) getSupportFragmentManager().findFragmentByTag(MeFragment.class.getName());
            if (firstFragment == null) {
                firstFragment = new FirstFragment();
            }
            if (doctorFragment == null) {
                doctorFragment = new DoctorFragment();
            }
            if(secretFragment==null){
                secretFragment=new SecretFragment();
            }
            if (meFragment == null) {
                meFragment = new MeFragment();
            }
            getSupportFragmentManager().beginTransaction()
                    .show(firstFragment)
                    .hide(doctorFragment)
                    .hide(secretFragment)
                    .hide(meFragment).commit();
        } else {
            firstFragment = new FirstFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment, FirstFragment.class.getName())
                    .show(firstFragment).commit();
        }


        for (int i = 0; i < 4; i++) {
            mTabs[i] = findViewById(mTabID[i]);
            mTabs[i].setOnClickListener(this);
        }

        iv_tab_first.setSelected(true);

//        EventBus.getDefault().register(this);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        iv_tab_first= (ImageView) findViewById(R.id.iv_tab_first);
        iv_tab_doctor= (ImageView) findViewById(R.id.iv_tab_doctor);
        iv_tab_secret= (ImageView) findViewById(R.id.iv_tab_secret);
        iv_tab_me= (ImageView) findViewById(R.id.iv_tab_me);
        tv_tab_first= (TextView) findViewById(R.id.tv_tab_first);
        tv_tab_doctor= (TextView) findViewById(R.id.tv_tab_doctor);
        tv_tab_secret= (TextView) findViewById(R.id.tv_tab_secret);
        tv_tab_me= (TextView) findViewById(R.id.tv_tab_me);
    }

    private void hideAllTabIcon(FragmentTransaction transaction) {
        if (null != firstFragment) {
            transaction.hide(firstFragment);
        }
        if (null != doctorFragment) {
            transaction.hide(doctorFragment);
        }
        if(null!=secretFragment){
            transaction.hide(secretFragment);
        }
        if (null != meFragment) {
            transaction.hide(meFragment);
        }
        iv_tab_first.setSelected(false);
        tv_tab_first.setTextColor(getResources().getColor(R.color.tv_tab_color_normal));
        iv_tab_doctor.setSelected(false);
        tv_tab_doctor.setTextColor(getResources().getColor(R.color.tv_tab_color_normal));

        iv_tab_secret.setSelected(false);
        tv_tab_secret.setTextColor(getResources().getColor(R.color.tv_tab_color_normal));

        iv_tab_me.setSelected(false);
        tv_tab_me.setTextColor(getResources().getColor(R.color.tv_tab_color_normal));


    }

    public static int select_index;

    public void showFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllTabIcon(transaction);
        switch (index) {
            case 0:

                if (firstFragment == null) {
                    firstFragment = new FirstFragment();
                    transaction.add(R.id.fragment_container, firstFragment, FirstFragment.class.getName());
                }
                transaction.show(firstFragment).commit();
                iv_tab_first.setSelected(true);
                tv_tab_first.setTextColor(getResources().getColor(R.color.tv_tab_color_select));

//                getLocationWithOneMinute = false;
                select_index = 0;
                break;
            case 1:
                if (doctorFragment == null) {
                    doctorFragment = new DoctorFragment();
                    transaction.add(R.id.fragment_container, doctorFragment, DoctorFragment.class.getName());
                }
                select_index = 1;


                transaction
                        .show(doctorFragment).commit();
                iv_tab_doctor.setSelected(true);
                tv_tab_doctor.setTextColor(getResources().getColor(R.color.tv_tab_color_select));

                break;
            case 2:
                if(secretFragment==null){
                    secretFragment=new SecretFragment();
                    transaction.add(R.id.fragment_container,secretFragment,SecretFragment.class.getName());
                }
                select_index=2;
                transaction.show(secretFragment).commit();
                iv_tab_secret.setSelected(true);
                tv_tab_secret.setTextColor(getResources().getColor(R.color.tv_tab_color_select));

                break;
            case 3:

                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.fragment_container, meFragment, MeFragment.class.getName());
                }
//                getLocationWithOneMinute = false;
                select_index = 3;
                transaction
                        .show(meFragment).commit();
                iv_tab_me.setSelected(true);
                tv_tab_me.setTextColor(getResources().getColor(R.color.tv_tab_color_select));

                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_first:
                showFragment(0);
                break;
            case R.id.tab_doctor:
                showFragment(1);
                break;
            case R.id.tab_secret:
                showFragment(2);
                break;
            case R.id.tab_me:
                showFragment(3);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        DialogUtil.showTwoButtonDialog(this, getString(R.string.dialog_exit_app_title), getString(R.string.dialog_exit_app_tab1), getString(R.string.dialog_exit_app_tab2), new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                },
//                new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        finish();
//                    }
//                }
//        );
    }








    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
    
    

