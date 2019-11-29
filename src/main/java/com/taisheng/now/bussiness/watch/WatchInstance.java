package com.taisheng.now.bussiness.watch;

public class WatchInstance {


    private static WatchInstance watchInstance;

    private WatchInstance() {
    }

    public static WatchInstance getInstance() {
        if (watchInstance == null) {
            watchInstance = new WatchInstance();
        }
        return watchInstance;
    }
}
