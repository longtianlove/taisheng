package com.taisheng.now.bussiness.healthfiles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;

/**
 * Created by dragon on 2019/7/1.
 */

public class ZhongyitizhiFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_zhongyitizhi, container, false);
        initView(rootView);
        return rootView;
    }
    void initView(View rootView){

    }
}
