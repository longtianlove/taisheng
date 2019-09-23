package com.taisheng.now;

import android.widget.SimpleCursorTreeAdapter;

/**
 * Created by long on 17/4/6.
 */

public class Constants {

    public final static String DEFAULT_TAG = "taisheng";
    public final static String BUGLY_APP_ID = "24b876d82d";
    //todo 需要修改
    //腾讯视频appid
    public final static int SDKAPPID = 1400229730;


    //访问成功
    public final static int HTTP_SUCCESS = 200;
    public final static int HTTP_ERROR = 500;
    public final static int LOGIN_VERIFYCODE_FAIL = 1004;//验证码不正确，请重新输入
    public final static int LOGIN_VERIFYCODE_OVERDUE_FAIL = 1007;//"验证码已过期，请重新获取！"
    public final static int LOGIN_USERNAME_NOT_EXISTENCE = 1002;//账号不存在,请切换手机号登陆！
    public final static int LOGIN_PASSWORD_ERROR = 101;//密码错误
    public final static int TOKEN_DIFFERENCE = 401023;//token异常
    public final static int DOCTOR_NOEXIST = 70000;//医生不存在
    public final static int DOCTOR_BUSY = 70001;//医生忙碌中,请稍后联系


    //男女0是男，1是女
    public final static int MALE = 0;
    public final static int FEMALE = 1;

    public final static String SUSHENHUFU = "塑身护肤";
    public final static String JIANSHENYUNDONG = "健身运动";
    public final static String SHILIAOYANGSHENG = "食疗养生";
    public final static String YONGYAOZHIDAO = "用药指导";
    public final static String MUYINGYUNYU = "母婴孕育";


    public static class Url {
        //        public static String Host = "http://47.93.249.1:9100/";
//        public static String File_Host="http://47.93.249.1:9700/";
//        public static String Host = "http://120.24.152.121:9100/";、
        //        public static String Host = "http://192.168.1.8:8888/";
//        public static String Host = "http://192.168.1.17:8080/";


//        public static String Host = "http://192.168.1.12:8080/";

//public static String Host = "http://192.168.1.18:8080/";
//        public static String Host = "http://152.136.26.41:8080/";


        //        public static String File_Host = "http://120.24.152.121:9700/";

//        public static String WEB_SOCKET_URL="ws://192.168.1.18:8879";

//        public static String Host = "http://152.136.26.41:8080/";
//        public static String File_Host = "http://152.136.26.41:8888/";
//        public static String WEB_SOCKET_URL = "ws://152.136.26.41:8879";


        public static String Host = "http://192.168.1.12:8080/";
        public static String File_Host = "http://192.168.1.12:8888/";
        public static String WEB_SOCKET_URL = "ws://192.168.1.12:8879";






//        public static String Host = "http://192.168.1.17:8080/";
//        public static String File_Host = "http://192.168.1.17:8080/jeecg-boot/";
//        public static String WEB_SOCKET_URL = "ws://192.168.1.17:8879";


        //用户相关
        public static class User {

            /**
             * 获取验证码
             */
            public static final String appAcquireVerifyCode = "jeecg-boot/app/sms/appAcquireVerifyCode";

            /**
             * 手机APP登录
             */
            public static final String applogin = "jeecg-boot/app/login/applogin";

            /**
             * 上传头像
             */
            public static final String uploadImage = "jeecg-boot/sys/common/uploadImage";

            /**
             * 更新用户信息
             */
            public static final String modifyuser = "jeecg-boot/app/user/modifyuser";
            /**
             * 更新档案信息
             */
            public static final String addOrUpdateHealth = "jeecg-boot/app/user/addOrUpdateHealth";
            /**
             * 更新密码
             */
            public static final String modifypassword = "jeecg-boot/app/user/modifypassword";

            /**
             * 我的评价列表
             */
            public static final String myDoctorScores = "jeecg-boot/app/score/doctorScore/myDoctorScores";


            //邀请历史
            public static final String myrecommendshare = "jeecg-boot/app/extension/getExtensionManagement";


            //签到
            public static final String isSign = "jeecg-boot/app/sign/isSign";

            //当前签到的状态
            public static final String nowSign="jeecg-boot/app/sign/user/sign";


        }

        //文章
        public static class Article {
            /**
             * 获取热门文章
             */
            public static final String hotSearchArticle = "jeecg-boot/app/article/hotSearchArticle";

            /**
             * 文章列表
             */
            public static final String articleList = "jeecg-boot/app/article/list";

            /**
             * 文章详情
             */
            public static final String articleQeryById = "jeecg-boot/app/article/queryById";

            /**
             * 文章内容
             */
            //todo 上线改host
//            public static final String articleContent=Host+"jeecg-boot/app/article/v2/h5/articleDetail?articleId=";
            public static final String articleContent = Host + "jeecg-boot/app/article/v2/h5/articleDetail?articleId=";

            /**
             * 首页热度文章
             */
            public static final String hotArticleList = "jeecg-boot/app/article/index/hotArticleList";


            //文章相关的医生
            public static final String getDoctorTypeList = "jeecg-boot/app/doctor/getDoctorTypeList";

            //增加文章的阅读量
            public static final String updateArticleReadCount = "jeecg-boot/app/article/updateArticleReadCount";

            //文章收藏
            public static final String saveCollectionArticleLog = "jeecg-boot/app/article/v2/saveCollectionArticleLog";
            //文章分享
            public static final String saveShareArticleLog = "jeecg-boot/app/article/v2/saveShareArticleLog";

        }

        //医生
        public static class Doctor {


            /**
             * 获取推荐医生
             */
            public static final String recommendList = "jeecg-boot/app/doctor/recommendList";
            /**
             * 获取所有医生
             */
            public static final String doctorslist = "jeecg-boot/app/doctor/list";
            /**
             * 获取医生详情
             */
            public static final String doctorQueryById = "jeecg-boot/app/doctor/queryById";
            /**
             * 获取医生评价
             */
            public static final String doctorScoreList = "jeecg-boot/app/score/doctorScore/scoreList";


            /**
             * 给医生评分
             */
            public static final String doctorScore = "jeecg-boot/app/score/doctorScore/add";

            //统计被评价总数
            public static final String getBeCommentedNum = "jeecg-boot/app/doctor/getBeCommentedNum";

            //文章被/医生关注（收藏）总数
            public static final String getBeDoctorAttentionNum = "jeecg-boot/app/doctor/getBeDoctorAttentionNum";
            //医生服务总数
            public static final String getDoctorServerNum = "jeecg-boot/app/doctor/getDoctorServerNum";

            //收藏/取消收藏
            public static final String collectionaddOrRemove = "jeecg-boot/app/collection/addOrRemove";

            //收藏管理
            public static final String collectionlist = "jeecg-boot/app/collection/getDoctorOrArticlePageList";

            //咨询记录
            public static final String consultList = "jeecg-boot/app/consult/consultList";


            //视频建立连接
            public static final String connectDoctor = "jeecg-boot/app/consult/connectDoctor";
            //有人进来
            public static final String detectRoomIn = "jeecg-boot/app/consult/detectRoomIn";
            //视频完接口回调
            public static final String updateDoctorStatus = "jeecg-boot/app/consult/updateDoctorStatus";
        }

        //视频
        public static class ShiPin {

            /**
             * 推荐视频
             */
            public static final String recommendShiPin = "jeecg-boot/app/video/home/recommend";

            /**
             * 获取视频列表
             */
            public static final String moreShiPin = "jeecg-boot/app/video/list";


            /**
             * 点赞
             */
            public static final String videoOperate = "jeecg-boot/app/video/operate";


        }


        //测评
        public static class CePing {
            /**
             * 获取测评题目
             */
            public static final String getExtractionSubjectDb = "jeecg-boot/app/assessment/getExtractionSubjectDb";
            /**
             * 提交答题结果
             */
            public static final String saveAnswer = "jeecg-boot/app/assessment/saveAnswer";

            /**
             * 测评历史
             */
            public static final String answerRecordList = "jeecg-boot/app/assessment/answerRecordList";

        }

        /**
         * 投诉中心
         */

        public static final String feedback = "jeecg-boot/app/feedback/add";

    }


}
