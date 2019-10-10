package com.taisheng.now.bussiness.market;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;

public class MarketFragment extends BaseFragment {

    View iv_gouwuche;
    EditText et_doctor_search;
    View iv_search_guanbi;
    View tv_search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_market, container, false);

        initView(rootView);


//        EventBus.getDefault().register(this);
        initData();

        return rootView;
    }

    void initView(View rootView) {
        iv_gouwuche = rootView.findViewById(R.id.iv_gouwuche);
        iv_gouwuche.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//todo 进入购物车

            }
        });

        iv_search_guanbi = rootView.findViewById(R.id.iv_search_guanbi);
        iv_search_guanbi.setVisibility(View.GONE);
        iv_search_guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_doctor_search.setText("");
            }
        });
        et_doctor_search = (EditText) rootView.findViewById(R.id.et_doctor_search);
        et_doctor_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    iv_search_guanbi.setVisibility(View.VISIBLE);
                } else {
                    iv_search_guanbi.setVisibility(View.GONE);
                }
                String searchkey = s.toString();
//                nickName = searchkey;
//                PAGE_NO = 1;
//                madapter.mData.clear();
//                getDoctors();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tv_search = rootView.findViewById(R.id.tv_search);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchkey = et_doctor_search.getText().toString();
//                nickName = searchkey;
//                PAGE_NO = 1;
//                madapter.mData.clear();
//                getDoctors();

            }
        });
    }

    void initData() {

    }


}
