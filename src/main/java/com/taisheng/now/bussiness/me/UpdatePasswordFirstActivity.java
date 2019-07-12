package com.taisheng.now.bussiness.me;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.bussiness.user.LoginPresenter;
import com.taisheng.now.bussiness.user.LoginView;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.util.DialogUtil;

import org.w3c.dom.Text;

/**
 * Created by dragon on 2019/6/28.
 */

public class UpdatePasswordFirstActivity extends BaseActivity implements LoginView {
    View iv_back;
    EditText et_shoujihao;
    Button btn_yanzhengma;
    EditText et_yanzhengma;
    TextView btn_yanzhengma_login;

    private LoginPresenter loginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepwd_first);
        initView();
        loginPresenter = new LoginPresenter(this);
    }
    void initView(){
        iv_back=findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_shoujihao= (EditText) findViewById(R.id.et_shoujihao);
        et_shoujihao.setText(UserInstance.getInstance().getPhone());
        //手机号
        et_shoujihao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    btn_yanzhengma_login.setEnabled(true);
                } else {
                    btn_yanzhengma_login.setEnabled(false);
                }
                if (s.length() == 11) {
                    btn_yanzhengma.setEnabled(true);
                } else {
                    btn_yanzhengma.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_yanzhengma= (Button) findViewById(R.id.btn_yanzhengma);
        btn_yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPhone = et_shoujihao.getText().toString();
                loginPresenter.getVerifyCode(strPhone);

            }
        });

        et_yanzhengma= (EditText) findViewById(R.id.et_yanzhengma);
        btn_yanzhengma_login= (TextView) findViewById(R.id.btn_yanzhengma_login);

    }

    @Override
    public void showDialog() {
        DialogUtil.showProgress(this, "");
    }

    @Override
    public void dismissDialog() {
        DialogUtil.closeProgress();
    }


    @Override
    public void getVerifyNextTime(int nSecond) {
        WaitForNextFetchCode(nSecond);

    }
    int messageWaitTime;

    void WaitForNextFetchCode(int nSecond) {
        messageWaitTime = nSecond;
        btn_yanzhengma.setText(String.valueOf(messageWaitTime) + "S");
        btn_yanzhengma.setEnabled(false);

        btn_yanzhengma.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (messageWaitTime == 1) {
                    btn_yanzhengma.setEnabled(true);
                    btn_yanzhengma.setText("重新发送");
                } else {
                    messageWaitTime--;
                    btn_yanzhengma.setText(String.valueOf(messageWaitTime) + "S");
                    btn_yanzhengma.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
