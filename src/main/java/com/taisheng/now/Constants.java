package com.taisheng.now;

/**
 * Created by long on 17/4/6.
 */

public class Constants {

    public final static String DEFAULT_TAG = "taisheng";
    public final static String BUGLY_APP_ID = "24b876d82d";


    //访问成功
    public final static int HTTP_SUCCESS = 200;
    public final static int HTTP_ERROR = 500;
    public final static int LOGIN_VERIFYCODE_FAIL = 104;//验证码不正确，请重新输入
    public final static int LOGIN_USERNAME_NOT_EXISTENCE = 102;//账号不存在,请切换手机号登陆！
    public final static int LOGIN_PASSWORD_ERROR = 101;//密码错误

    //男女0是男，1是女
    public final static int MALE = 0;
    public final static int FEMALE = 1;

    public final static String ZHONGYITIZHI = "中医体质";
    public final static String JICHUDAIXIE = "基础代谢";
    public final static String FUKEJIANKANG = "女性健康";
    public final static String XINFEIGONGNENG = "心肺功能";
    public final static String YAOJINGJIANBEI = "腰颈肩背";
    public final static String PIWEIGANSHEN = "脾胃肝肾";


    public static class Url {
        //        public static String Host = "http://47.93.249.1:9100/";
//        public static String File_Host="http://47.93.249.1:9700/";
//        public static String Host = "http://120.24.152.121:9100/";
//        public static String Host = "http://192.168.1.18:8080/";
        public static String Host = "http://192.168.1.8:8888/";
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

        //测评
        public static class CePing {
            /**
             * 获取测评题目
             */
            public static final String getExtractionSubjectDb = "jeecg-boot/app/assessment/getExtractionSubjectDb";
        }

        /**
         * 投诉中心
         */

        public static final String feedback = "jeecg-boot/app/feedback/add";

    }


}
