package com.taisheng.now.chat;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;


import com.taisheng.now.chat.websocket.WebSocketManager;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
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
        WebSocketManager webSocketManager=new WebSocketManager();
        webSocketManager.setWebSocketListener(new WebSocketManager.WebSocketListener() {
            @Override
            public void onConnected(Map<String, List<String>> headers) {

            }

            @Override
            public void onTextMessage(String text) {
                RemoteChatMessage message=new RemoteChatMessage();
                //todo 实例化消息
                HistoryBean historyBean = new HistoryBean();
                historyBean.setType(CoreDB.HISTORY_TYPE_C2C);
                historyBean.setLastTime(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
                historyBean.setLastMsg(message.contentData);
                historyBean.setConversationId(message.fromId);
                historyBean.setNewMsgCount(1);
                MLOC.addHistory(historyBean,false);

                MessageBean messageBean = new MessageBean();
                messageBean.setConversationId(message.fromId);
                messageBean.setTime(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
                messageBean.setMsg(message.contentData);
                messageBean.setFromId(message.fromId);
                MLOC.saveMessage(messageBean);

                AEvent.notifyListener(AEvent.AEVENT_C2C_REV_MSG,true,message);
            }
        });
        webSocketManager.connect();


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
