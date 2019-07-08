package com.taisheng.now.http;


import com.taisheng.now.Constants;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.ArticleContentBean;
import com.taisheng.now.bussiness.bean.ArticleContentPostBean;
import com.taisheng.now.bussiness.bean.ArticlePostBean;
import com.taisheng.now.bussiness.bean.ArticleResultBean;
import com.taisheng.now.bussiness.bean.CaptchaPostBean;
import com.taisheng.now.bussiness.bean.CaptchaResultBean;
import com.taisheng.now.bussiness.bean.HotPostBean;
import com.taisheng.now.bussiness.bean.HotResultBean;
import com.taisheng.now.bussiness.bean.LoginPostBean;
import com.taisheng.now.bussiness.bean.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by long
 */
public interface ApiService {
    //    /**
//     * 获取用户基本信息
//     *
//     * @param uid
//     * @param token
//     * @return
//     */
//    @GET(Constants.Url.User.get_user_info)
//    Call<UserBean> getUserInfo(
//            @Query("uid") String uid,
//            @Query("token") String token
//    );

    //登录
    @POST(Constants.Url.User.applogin)
    Call<BaseBean<UserInfo>> applogin(@Body LoginPostBean loginPostBean);

    //发送验证码
    @POST(Constants.Url.User.appAcquireVerifyCode)
    Call<BaseBean> appAcquireVerifyCode(@Body CaptchaPostBean loginPostBean);

    //获取热门文章
    @POST(Constants.Url.Article.hotSearchArticle)
    Call<BaseBean<HotResultBean>> hotSearchArticle(@Body HotPostBean hotPostBean);

    //获取文章列表
    @POST(Constants.Url.Article.articleList)
    Call<BaseBean<ArticleResultBean>> articleList(@Body ArticlePostBean articlePostBean);

    //获取文章详情
    @POST(Constants.Url.Article.articleQeryById)
    Call<BaseBean<ArticleContentBean>> articleQeryById(@Body ArticleContentPostBean articleContentPostBean);


}