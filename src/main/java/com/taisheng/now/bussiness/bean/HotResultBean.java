package com.taisheng.now.bussiness.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taisheng.now.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 2019/7/5.
 */

public class HotResultBean extends BaseBean {

    public List<HotSearchBean> list=new ArrayList<>();
    public void parseResult(){
        if (result == null) {
            return;
        }

       JSONArray articlesJson= result.getJSONArray("list");
        list=JSON.parseArray(articlesJson.toJSONString(),HotSearchBean.class);
    }
}
