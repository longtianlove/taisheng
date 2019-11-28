package com.taisheng.now.bussiness.watch.watchfirst;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseFragment;
import com.veken.chartview.bean.ChartBean;
import com.veken.chartview.drawtype.DrawBgType;
import com.veken.chartview.drawtype.DrawConnectLineType;
import com.veken.chartview.drawtype.DrawLineType;
import com.veken.chartview.view.LineChartView;

import java.util.ArrayList;
import java.util.Random;

public class JibuFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_jibu, container, false);
        initView(rootView);


        initData();

        return rootView;
    }


    View ll_guijiditu;
    private ArrayList<Entry> list = new ArrayList<>();  //数据集合


    private LineChart mChart;

    void initView(View rootView) {

        ll_guijiditu = rootView.findViewById(R.id.ll_guijiditu);
        ll_guijiditu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WatchFirstGuijiActivity.class);
                startActivity(intent);
            }
        });
        this.mChart = (LineChart) rootView.findViewById(R.id.chart);
        list.clear();
        for (int i = 0; i < 10; i++) {
            list.add(new Entry(i, (float) (Math.random() * 80)));
        }


        //直接调用即可
        LineChartUtils lineChartUtils = new LineChartUtils(list, mChart);
    }


    void initData() {

    }


}
