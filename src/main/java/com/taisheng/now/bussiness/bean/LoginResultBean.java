package com.taisheng.now.bussiness.bean;

import com.taisheng.now.base.BaseBean;

/**
 * Created by dragon on 2019/7/3.
 */

public class LoginResultBean extends BaseBean {

    public Result result;

    public class Result{
        public UserInfo userInfo;
    }

}
