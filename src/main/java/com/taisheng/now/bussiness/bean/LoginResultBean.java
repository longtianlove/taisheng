package com.taisheng.now.bussiness.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taisheng.now.base.BaseBean;

/**
 * Created by dragon on 2019/7/3.
 */

public class LoginResultBean extends BaseBean {
    public UserInfo userInfo = new UserInfo();

    public void parseResult() {
        if (result == null) {
            return;
        }
        JSONObject userInfoObject = (JSONObject) result.get("userInfo");
        userInfo.id = userInfoObject.getString("id");
        userInfo.token = userInfoObject.getString("token");
        userInfo.userName = userInfoObject.getString("userName");
        userInfo.nickName = userInfoObject.getString("nickName");
        userInfo.password = userInfoObject.getString("password");
        userInfo.avatar = userInfoObject.getString("avatar");
        userInfo.sex = userInfoObject.getString("sex");
        userInfo.age = userInfoObject.getString("age");


        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
        userInfo.id = userInfoObject.getString("id");
    }


}
