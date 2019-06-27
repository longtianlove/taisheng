package com.taisheng.now.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Created by dragon on 2019/6/27.
 */
public class BaseFragment extends Fragment {

    protected Activity mActivity;


    public void onAttach(Activity activity) {

        mActivity=  activity;
        super.onAttach(mActivity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
