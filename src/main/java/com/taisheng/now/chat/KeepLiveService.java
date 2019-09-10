package com.taisheng.now.chat;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.starrtc.starrtcsdk.api.XHClient;
import com.starrtc.starrtcsdk.api.XHCustomConfig;
import com.starrtc.starrtcsdk.apiInterface.IXHErrorCallback;
import com.starrtc.starrtcsdk.apiInterface.IXHResultCallback;
import com.starrtc.starrtcsdk.core.videosrc.XHVideoSourceManager;

import java.util.Random;


/**
 * Created by zhangjt on 2017/8/6.
 */

public class KeepLiveService extends Service implements IEventListener {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initSDK();
        return super.onStartCommand(intent, flags, startId);
    }

    private void initSDK() {
        MLOC.init(this);
        initFree();
    }

    //    private boolean isLogin = false;
    //开放版SDK初始化
    private void initFree() {
        MLOC.d("KeepLiveService", "initFree");
        addListener();
        //todo 收到消息回调
//        XHClient.getInstance().getChatManager().addListener(new XHChatManagerListener());
    }

    @Override
    public void dispatchEvent(String aEventID, boolean success, Object eventObj) {
        switch (aEventID) {
            case AEvent.AEVENT_C2C_REV_MSG:
                MLOC.hasNewC2CMsg = true;
                break;
        }
    }


    private void addListener() {
        AEvent.addListener(AEvent.AEVENT_C2C_REV_MSG, this);
    }

    private void removeListener() {
        AEvent.removeListener(AEvent.AEVENT_C2C_REV_MSG, this);
    }

}
