package com.taisheng.now.bussiness.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taisheng.now.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 2019/7/8.
 */

public class ArticleResultBean extends BaseBean{
    public List<ArticleBean> records=new ArrayList<>();
    public int total;
    public int size;
    public int current;
    public boolean searchCount;
    public int pages;

    public void parseResult(){
        if (result == null) {
            return;
        }

        JSONArray articlesJson= result.getJSONArray("records");
        records= JSON.parseArray(articlesJson.toJSONString(),ArticleBean.class);
    }
}
