package com.taisheng.now.push;

/**
 * Created by long on 2017/5/3.
 */

public class PushEventManage {
    /*
    异地登录
     */
    public static class otherLogin {
        public String remote_login_time;
        public String X_OS_Name;
    }

    /**
     * 普通电量
     */
    public static class commonBattery {

    }

//    /**
//     * 设备离线
//     */
//    public static class deviceOffline {
//
//    }

//    /**
//     * 设备重新在线
//     */
//    public static class deviceOnline {
//
//    }

    /**
     * 设备低电量
     */
    public static class batteryLowLevel {

    }

    /**
     * 设备超低电量
     */
    public static class batterySuperLowLevel {

    }

    /**
     * 位置变化
     */
    public static class locationChange {

    }

    /**
     * 宠物离开家了
     */
    public static class petNotHome {

    }

    /**
     * 宠物回到家了
     */
    public static class petAtHome {
    }



    public static class outdoorInProtected{
        //在保护中
    }
    public static class outdoorOutProtected{
        //不在保护中
    }

}
