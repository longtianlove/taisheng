package com.taisheng.now.base;

import com.taisheng.now.bussiness.bean.UserInfo;

import java.io.Serializable;

/**
 * Created by long on 17/4/8.
 */

public class BaseBean implements Serializable{
    public boolean success;
    public String message;
    public int code;
//    public Result<T> result;
    public long timestamp;

}
