package com.taisheng.now.bussiness.bean.result;

import com.taisheng.now.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 2019/7/12.
 */

public class CollectListResultBean {


    //todo 再填文章列表 医生列表名字也要变
    public List<ArticleBean> articlerecords=new ArrayList<>();
    public List<DoctorBean> records = new ArrayList<>();
    public int total;
    public int size;
    public int current;
    public boolean searchCount;
    public int pages;
}
