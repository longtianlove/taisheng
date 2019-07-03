package com.taisheng.now;

/**
 * Created by long on 17/4/6.
 */

public class Constants {

    public final static String DEFAULT_TAG = "taisheng";
    public final static String  BUGLY_APP_ID="24b876d82d";


    //访问成功
    public final static int HTTP_SUCCESS=200;




    public static class Url {
        //        public static String Host = "http://47.93.249.1:9100/";
//        public static String File_Host="http://47.93.249.1:9700/";
//        public static String Host = "http://120.24.152.121:9100/";
        public static String Host = "http://192.168.1.18:8080/";
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
             * 	手机APP登录
             */
            public static final String applogin="jeecg-boot/app/login/applogin";
        }
    }





}
