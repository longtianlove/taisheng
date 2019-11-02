package com.taisheng.now.bussiness.bean.result.market;

import java.util.List;

public class OrderBean {
    public String orderId;
    public String orderSn;
    public int totalPrice;
    public int goodsNumber;
    public List<OrderGoodsBean> list;
    public String status;
}
