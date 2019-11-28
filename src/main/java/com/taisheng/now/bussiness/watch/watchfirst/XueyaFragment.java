package com.taisheng.now.bussiness.watch.watchfirst;

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

public class XueyaFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_xueya, container, false);
        initView(rootView);



        initData();

        return rootView;
    }


    private ArrayList<Entry> list = new ArrayList<>();  //数据集合
    private ArrayList<Entry> list1 = new ArrayList<>();  //数据集合


    private LineChart mChart;

    void initView(View rootView) {

        this.mChart = (LineChart) rootView.findViewById(R.id.chart);
        list.clear();
        list1.clear();
        for (int i = 0; i < 10; i++) {
            list.add(new Entry(i, (float) (Math.random() * 80)));
        }
        for (int i = 0; i < 10; i++) {
            list1.add(new Entry(i, (float) (Math.random() * 40)));
        }


        //直接调用即可
        LineChartUtils lineChartUtils=new LineChartUtils(list,list1,mChart);

    }




    void initData(){


    }


}
