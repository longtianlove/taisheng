package com.taisheng.now.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;




/**
 * Created by Administrator on 2015/6/17.
 */
public class BaseFragmentActivity extends FragmentActivity {
    private static String TAG = "BaseFragmentActivity";
    private ViewGroup m_titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }




}
