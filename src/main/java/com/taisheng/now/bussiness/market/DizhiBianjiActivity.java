package com.taisheng.now.bussiness.market;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.bussiness.article.ArticleContentActivity;
import com.taisheng.now.bussiness.bean.result.ArticleBean;
import com.taisheng.now.view.WithScrolleViewListView;
import com.taisheng.now.view.chenjinshi.StatusBarUtil;
import com.ywp.addresspickerlib.AddressPickerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 2019/6/28.
 */


public class DizhiBianjiActivity extends BaseActivity {
    View iv_back;
    EditText et_xingming;
    EditText et_phone;
    View ll_dizhi;
    TextView et_dizhi;
    EditText et_xiangxidizhi;
    View btn_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizhibianji);
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

        et_xingming = findViewById(R.id.et_xingming);
        et_xingming.addTextChangedListener(watcher);
        et_phone = findViewById(R.id.et_phone);
        et_phone.addTextChangedListener(watcher);
        ll_dizhi=findViewById(R.id.ll_dizhi);
        ll_dizhi.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showAddressPickerPop();
            }
        });


        et_dizhi = findViewById(R.id.et_dizhi);
        et_dizhi.addTextChangedListener(watcher);



        et_xiangxidizhi = findViewById(R.id.et_xiangxidizhi);
        et_xiangxidizhi.addTextChangedListener(watcher);

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//todo 保存地址
            }
        });
    }

    boolean check() {
        if (TextUtils.isEmpty(et_xingming.getText())) {
            return false;
        }
        if (TextUtils.isEmpty(et_phone.getText())) {
            return false;
        }
        if (TextUtils.isEmpty(et_dizhi.getText())) {
            return false;
        }
        if (TextUtils.isEmpty(et_xiangxidizhi.getText())) {
            return false;
        }
        return true;
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (check()) {
                btn_save.setEnabled(true);
            } else {
                btn_save.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


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


    PopupWindow popupWindow;
    /**
     * 显示地址选择的pop
     */
    private void showAddressPickerPop() {
        if(popupWindow!=null&&popupWindow.isShowing()){
            popupWindow.dismiss();
            return;
        }
        popupWindow = new PopupWindow(this);
        View rootView = LayoutInflater.from(this).inflate(R.layout.pop_address_picker, null, false);
        AddressPickerView addressView = rootView.findViewById(R.id.apvAddress);
        addressView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
            @Override
            public void onSureClick(String address, String provinceCode, String cityCode, String districtCode) {
                et_dizhi.setText(address);
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(rootView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(ll_dizhi);

    }
    private void dismissPopwindow(){
        if(popupWindow!=null&&popupWindow.isShowing()){
            popupWindow.dismiss();
        }

    }
}
