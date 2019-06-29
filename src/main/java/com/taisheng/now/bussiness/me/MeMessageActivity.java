package com.taisheng.now.bussiness.me;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;

/**
 * Created by dragon on 2019/6/29.
 */

public class MeMessageActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_memessage);
    }
}
