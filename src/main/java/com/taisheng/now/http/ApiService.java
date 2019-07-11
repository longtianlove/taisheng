package com.taisheng.now.http;


import com.taisheng.now.Constants;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.post.AnswerPostBean;
import com.taisheng.now.bussiness.bean.post.BasePostBean;
import com.taisheng.now.bussiness.bean.post.CollectPostBean;
import com.taisheng.now.bussiness.bean.post.DoctorCommentPostBean;
import com.taisheng.now.bussiness.bean.post.DoctorNumberPostBean;
import com.taisheng.now.bussiness.bean.post.DoctorScorePostBean;
import com.taisheng.now.bussiness.bean.post.FeedbackPostBean;
import com.taisheng.now.bussiness.bean.post.GuanzhuPostBean;
import com.taisheng.now.bussiness.bean.post.HealthCheckListPostBean;
import com.taisheng.now.bussiness.bean.post.QuestionPostBean;
import com.taisheng.now.bussiness.bean.post.RecommendDoctorPostBean;
import com.taisheng.now.bussiness.bean.post.UserInfoPostBean;
import com.taisheng.now.bussiness.bean.result.AnswerResultBean;
import com.taisheng.now.bussiness.bean.result.ArticleBean;
import com.taisheng.now.bussiness.bean.result.ArticleContentBean;
import com.taisheng.now.bussiness.bean.post.ArticleContentPostBean;
import com.taisheng.now.bussiness.bean.post.ArticlePostBean;
import com.taisheng.now.bussiness.bean.result.ArticleResultBean;
import com.taisheng.now.bussiness.bean.post.CaptchaPostBean;
import com.taisheng.now.bussiness.bean.post.HotPostBean;
import com.taisheng.now.bussiness.bean.result.CheckHistoryResultBean;
import com.taisheng.now.bussiness.bean.result.CollectResultBean;
import com.taisheng.now.bussiness.bean.result.DoctorCommentBean;
import com.taisheng.now.bussiness.bean.result.DoctorCommentResultBean;
import com.taisheng.now.bussiness.bean.result.DoctorNumberResultBean;
import com.taisheng.now.bussiness.bean.result.DoctorsResultBean;
import com.taisheng.now.bussiness.bean.result.HotResultBean;
import com.taisheng.now.bussiness.bean.post.LoginPostBean;
import com.taisheng.now.bussiness.bean.result.PictureBean;
import com.taisheng.now.bussiness.bean.result.QuestionResultBean;
import com.taisheng.now.bussiness.bean.result.UserInfo;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by long
 */
public interface ApiService {

    //登录
    @POST(Constants.Url.User.applogin)
    Call<BaseBean<UserInfo>> applogin(@Body LoginPostBean loginPostBean);

    //发送验证码
    @POST(Constants.Url.User.appAcquireVerifyCode)
    Call<BaseBean> appAcquireVerifyCode(@Body CaptchaPostBean loginPostBean);

    /**
     * 更新用户信息
     */
    @POST(Constants.Url.User.modifyuser)
    Call<BaseBean> modifyuser(@Body UserInfoPostBean bean);


    //获取热门文章
    @POST(Constants.Url.Article.hotSearchArticle)

    Call<BaseBean<HotResultBean>> hotSearchArticle(@Body HotPostBean hotPostBean);

    //获取文章列表
    @POST(Constants.Url.Article.articleList)
    Call<BaseBean<ArticleResultBean>> articleList(@Body ArticlePostBean articlePostBean);

    //获取文章详情
    @POST(Constants.Url.Article.articleQeryById)
    Call<BaseBean<ArticleContentBean>> articleQeryById(@Body ArticleContentPostBean articleContentPostBean);

    //获取题目
    @POST(Constants.Url.CePing.getExtractionSubjectDb)
    Call<BaseBean<QuestionResultBean>> getExtractionSubjectDb(@Body QuestionPostBean questionPostBean);

    //提交答题结果
    @POST(Constants.Url.CePing.saveAnswer)
    Call<BaseBean<AnswerResultBean>> saveAnswer(@Body AnswerPostBean bean);

    /**
     * 测评历史
     */
    @POST(Constants.Url.CePing.answerRecordList)
    Call<BaseBean<CheckHistoryResultBean>> answerRecordList(@Body HealthCheckListPostBean bean);

    //首页热度文章
    @POST(Constants.Url.Article.hotArticleList)
    Call<BaseBean<ArrayList<ArticleBean>>> hotArticleList(@Body BasePostBean postBean);


    //获取推荐医生
    @POST(Constants.Url.Doctor.recommendList)
    Call<BaseBean<DoctorsResultBean>> recommendList(@Body RecommendDoctorPostBean postBean);

    //获取所有医生
    @POST(Constants.Url.Doctor.doctorslist)
    Call<BaseBean<DoctorsResultBean>> doctorslist(@Body RecommendDoctorPostBean bean);

    /**
     * 获取医生评价
     */
    @POST(Constants.Url.Doctor.doctorScoreList)
    Call<BaseBean<DoctorCommentResultBean>> doctorScoreList(@Body DoctorCommentPostBean bean);


    //统计被评价总数
    @POST(Constants.Url.Doctor.getBeCommentedNum)
    Call<BaseBean<DoctorNumberResultBean>> getBeCommentedNum(@Body DoctorNumberPostBean bean);

    //            文章被/医生关注（收藏）总数
    @POST(Constants.Url.Doctor.getBeDoctorAttentionNum)
    Call<BaseBean<DoctorNumberResultBean>> getBeDoctorAttentionNum(@Body GuanzhuPostBean bean);

    //医生服务总数
    @POST(Constants.Url.Doctor.getDoctorServerNum)
    Call<BaseBean<DoctorNumberResultBean>> getDoctorServerNum(@Body DoctorNumberPostBean bean);


    //收藏/取消收藏
    @POST(Constants.Url.Doctor.collectionaddOrRemove)
    Call<BaseBean<CollectResultBean>> collectionaddOrRemove(@Body CollectPostBean bean);

    /**
     * 给医生评分
     */
    @POST(Constants.Url.Doctor.doctorScore)
    Call<BaseBean> doctorScore(@Body DoctorScorePostBean beann);


    //上传头像
    @Multipart
    @POST(Constants.Url.User.uploadImage)
    Call<BaseBean<PictureBean>> uploadLogo(
            @Part MultipartBody.Part file
    );

    //反馈
    @POST(Constants.Url.feedback)
    Call<BaseBean> feedback(@Body FeedbackPostBean bean);

}