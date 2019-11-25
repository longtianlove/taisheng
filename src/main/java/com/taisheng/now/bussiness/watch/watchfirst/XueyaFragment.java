package com.taisheng.now.bussiness.watch.watchfirst;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    LineChartView lineChartView;
    void initView(View rootView) {
        lineChartView = rootView.findViewById(R.id.chart_view_week);
        lineChartView.setyLableText("折线图");
//设置点击背景（可以为图片，也可以为一个颜色背景，大小根据textAndClickBgMargin设置）
        lineChartView.setDrawBgType(DrawBgType.DrawBitmap);
//设置图片资源
        lineChartView.setShowPicResource(R.mipmap.click_icon);
//连接线为虚线（也可以为实现）
        lineChartView.setDrawConnectLineType(DrawConnectLineType.DrawDottedLine);
        lineChartView.setClickable(true);
//是否需要画连接线
        lineChartView.setNeedDrawConnectYDataLine(true);
//连接线的颜色
        lineChartView.setConnectLineColor(getResources().getColor(R.color.default_color));
//是否需要背景
        lineChartView.setNeedBg(true);
//画曲线图（也可以为折线图）
        lineChartView.setDrawLineType(DrawLineType.Draw_Curve);
    }


    private ArrayList<ChartBean> lineChartBeanList;
    void initData(){

        if(lineChartBeanList ==null){
            lineChartBeanList = new ArrayList<>();
        }
        lineChartView.setDefaultTextSize(24);
        Random random = new Random();
        for(int i=0;i<7;i++){
            ChartBean lineChartBean = new ChartBean();
            lineChartBean.setValue(String.valueOf(random.nextInt(10000)));
            lineChartBean.setDate(String.valueOf(i));
            lineChartBeanList.add(lineChartBean);
        }
        lineChartView.setData(lineChartBeanList);
    }


}
