package com.taisheng.now.bussiness.market;

import com.taisheng.now.bussiness.bean.result.JifenzhuanquBean;
import com.taisheng.now.bussiness.bean.result.xiadanshangpinBean;
import com.taisheng.now.bussiness.user.UserInstance;

import java.util.ArrayList;
import java.util.List;

public class DingdanInstance {

    private static DingdanInstance dingdanInstance;


    private DingdanInstance() {
    }

    public static DingdanInstance getInstance() {
        if (dingdanInstance == null) {
            dingdanInstance = new DingdanInstance();
        }
        return dingdanInstance;
    }


    public List<xiadanshangpinBean> dingdanList=new ArrayList<>();

    public String zongjia;

    public String addressId;

    public  String flag;


//    优惠券返回值问题
    public String tv_discount;



}
