package com.taisheng.now;

/**
 * Created by long on 17/4/6.
 */

public class Constants {

    public final static String DEFAULT_TAG = "taisheng";
    public final static String BUGLY_APP_ID = "24b876d82d";
    //todo 需要修改
    //腾讯视频appid
    public final static int SDKAPPID=1400227841;


    //访问成功
    public final static int HTTP_SUCCESS = 200;
    public final static int HTTP_ERROR = 500;
    public final static int LOGIN_VERIFYCODE_FAIL = 104;//验证码不正确，请重新输入
    public final static int LOGIN_USERNAME_NOT_EXISTENCE = 102;//账号不存在,请切换手机号登陆！
    public final static int LOGIN_PASSWORD_ERROR = 101;//密码错误

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
//        public static String Host = "http://120.24.152.121:9100/";
        public static String Host = "http://192.168.1.18:8080/";
//        public static String Host = "http://192.168.1.8:8888/";
//        public static String Host = "http://39.108.247.58:9100/";
        //        public static String File_Host = "http://120.24.152.121:9700/";

        //        public static String Host = "http://120.76.208.90:9100/";
        public static String File_Host = "http://gateway.xiaomaoqiu.com:9700/";

//        public static String File_Host = "http://39.108.247.58:9700/";
//        public static String File_Host = "http://120.76.208.90:9700/";

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
            public static final String addOrUpdateHealth="jeecg-boot/app/user/addOrUpdateHealth";
            /**
             * 更新密码
             */
            public static final String modifypassword="jeecg-boot/app/user/modifypassword";
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
             * 首页热度文章
             */
            public static final String hotArticleList = "jeecg-boot/app/article/index/hotArticleList";

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
            public static final String collectionlist="jeecg-boot/app/collection/list";

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
