package com.taisheng.now.http;


import com.taisheng.now.Constants;
import com.taisheng.now.bussiness.bean.UserBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by long
 */
public interface ApiService {
    /**
     * 获取用户基本信息
     *
     * @param uid
     * @param token
     * @return
     */
    @GET(Constants.Url.User.get_user_info)
    Call<UserBean> getUserInfo(
            @Query("uid") String uid,
            @Query("token") String token
    );

}