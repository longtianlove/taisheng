package com.taisheng.now.chat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.EventManage;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.post.ConnectDoctorPostBean;
import com.taisheng.now.bussiness.bean.result.ConnectDoctorResultBean;
import com.taisheng.now.bussiness.doctor.DoctorCommentActivity;
import com.taisheng.now.bussiness.doctor.DoctorDetailActivity;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.chat.websocket.WebSocketManager;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.selfshipin.util.WebrtcUtil;
import com.taisheng.now.shipin.TRTCMainActivity;
import com.taisheng.now.util.DensityUtil;
import com.taisheng.now.util.DialogUtil;
import com.taisheng.now.util.DoubleClickUtil;
import com.taisheng.now.util.ToastUtil;
import com.taisheng.now.view.AppDialog;
import com.tencent.trtc.TRTCCloudDef;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class C2CActivity extends BaseActivity implements  AdapterView.OnItemLongClickListener , ActivityCompat.OnRequestPermissionsResultCallback {

    private EditText vEditText;
    private TextView vTargetId;
    private ListView vMsgList;
    private View vSendBtn;

    private String mTargetId;
    public String doctorAvator;
    public String doctorName;
    private List<MessageBean> mDatas;
    private MyChatroomListAdapter mAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c2c);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        findViewById(R.id.title_left_btn).setVisibility(View.VISIBLE);
        findViewById(R.id.title_left_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.title_right_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastMiniDoubleClick()) {
                    return;
                }
                                DialogUtil.showzixunDialog(C2CActivity.this,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                chatType = "video";

                                check();

                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                chatType = "audio";
                                check();
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }
                );
            }
        });
        vEditText = (EditText) findViewById(R.id.id_input);
        mDatas = new ArrayList<>();

        mTargetId = getIntent().getStringExtra("targetId");

        doctorAvator=getIntent().getStringExtra("doctorAvator");
        doctorName=getIntent().getStringExtra("doctorName");
        ((TextView)findViewById(R.id.title_text)).setText(doctorName);
        mAdapter = new MyChatroomListAdapter();
        vMsgList = (ListView) findViewById(R.id.msg_list);
        vMsgList.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        vMsgList.setOnItemLongClickListener(this);
        mAdapter = new MyChatroomListAdapter();
        vMsgList.setAdapter(mAdapter);
        vMsgList.setOverScrollMode(View.OVER_SCROLL_NEVER);



        vSendBtn = findViewById(R.id.send_btn);
        vSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = vEditText.getText().toString();
                if(!TextUtils.isEmpty(txt)){
                    sendMsg(txt);
                    vEditText.setText("");
                }
            }
        });

        EventBus.getDefault().register(this);


    }

    private void sendMsg(String msg){
        String rawMessage=",fhadmin-msg,"+ UserInstance.getInstance().getUid() +",fh,"+mTargetId+",fh,"
                +UserInstance.getInstance().getNickname()+",fh,普通用户,fh,"+UserInstance.getInstance().getRealname()
                +",fh,friend,fh,"+UserInstance.getInstance().userInfo.avatar+",fh,"+msg;

        WebSocketManager.getInstance().sendMessage(rawMessage);

        RemoteChatMessage message=new RemoteChatMessage();
        message.contentData=msg;
        message.targetId=mTargetId;
        message.fromId=UserInstance.getInstance().getUid();

        HistoryBean historyBean = new HistoryBean();
        historyBean.setType(CoreDB.HISTORY_TYPE_C2C);
        historyBean.setLastTime(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
        historyBean.setLastMsg(message.contentData);
        historyBean.setConversationId(message.targetId);
        historyBean.setNewMsgCount(1);
        historyBean.doctorAvator=doctorAvator;
        historyBean.doctorName=doctorName;
        MLOC.addHistory(historyBean,true);

        MessageBean messageBean = new MessageBean();
        messageBean.setConversationId(message.targetId);
        messageBean.setTime(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
        messageBean.setMsg(message.contentData);
        messageBean.setFromId(message.fromId);
        MLOC.saveMessage(messageBean);

        ColorUtils.getColor(this,message.fromId);
        mDatas.add(messageBean);
        mAdapter.notifyDataSetChanged();
    }





    @Override
    public void onRestart(){
        super.onRestart();
    }

    @Override
    public void onResume(){
        super.onResume();
        mDatas.clear();
        List<MessageBean> list =  MLOC.getMessageList(mTargetId);
        if(list!=null&&list.size()>0){
            mDatas.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
        vMsgList.setSelection(mAdapter.getCount()-1);

    }

    @Override
    public void onStop(){
        super.onStop();
    }





    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0)
    public void recevieMessage(EventManage.AEVENT_C2C_REV_MSG eventObj){
        MLOC.d("IM_C2C","||"+eventObj);
                final RemoteChatMessage revMsg = (RemoteChatMessage) eventObj.message;
                if(revMsg.fromId.equals(mTargetId)){
                    HistoryBean historyBean = new HistoryBean();
                    historyBean.setType(CoreDB.HISTORY_TYPE_C2C);
                    historyBean.setLastTime(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
                    historyBean.setLastMsg(revMsg.contentData);
                    historyBean.setConversationId(revMsg.fromId);
                    historyBean.setNewMsgCount(1);
                    historyBean.doctorName=doctorName;
                    historyBean.doctorAvator=doctorAvator;
                    MLOC.addHistory(historyBean,true);

                    MessageBean messageBean = new MessageBean();
                    messageBean.setConversationId(revMsg.fromId);
                    messageBean.setTime(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
                    messageBean.setMsg(revMsg.contentData);
                    messageBean.setFromId(revMsg.fromId);
                    mDatas.add(messageBean);
                    mAdapter.notifyDataSetChanged();

        }
    }



    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(mDatas.get(position).getMsg());
        Toast.makeText(this,"消息已复制",Toast.LENGTH_LONG).show();
        return false;
    }

    public class MyChatroomListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        public MyChatroomListAdapter(){
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            if(mDatas ==null) return 0;
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            if(mDatas ==null)
                return null;
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            if(mDatas ==null)
                return 0;
            return position;
        }

        @Override
        public int getViewTypeCount(){
            return 2;
        }

        @Override
        public int getItemViewType(int position){
            return mDatas.get(position).getFromId().equals(MLOC.userId)?0:1;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            int currLayoutType = getItemViewType(position);
            if(currLayoutType == 0){ //自己的信息
                final ViewHolder itemSelfHolder;
                if(convertView == null){
                    itemSelfHolder = new ViewHolder();
                    convertView = mInflater.inflate(R.layout.item_chat_msg_list_right,null);
                    itemSelfHolder.vUserId = (TextView) convertView.findViewById(R.id.item_user_id);
                    itemSelfHolder.vMsg = (TextView) convertView.findViewById(R.id.item_msg);
//                    itemSelfHolder.vHeadBg = convertView.findViewById(R.id.head_bg);
                    itemSelfHolder.sdv_header=convertView.findViewById(R.id.sdv_header);
//                    itemSelfHolder.vHeadCover = (CircularCoverView) convertView.findViewById(R.id.head_cover);
//                    itemSelfHolder.vHeadImage = (ImageView) convertView.findViewById(R.id.head_img);
                    convertView.setTag(itemSelfHolder);
                }else{
                    itemSelfHolder = (ViewHolder)convertView.getTag();
                }
                itemSelfHolder.vUserId.setText(UserInstance.getInstance().getNickname());
                itemSelfHolder.vMsg.setText(mDatas.get(position).getMsg());
                itemSelfHolder.sdv_header.setImageURI(Uri.parse(Constants.Url.File_Host+UserInstance.getInstance().userInfo.avatar));
//                itemSelfHolder.vHeadBg.setBackgroundColor(ColorUtils.getColor(C2CActivity.this,mDatas.get(position).getFromId()));
//                itemSelfHolder.vHeadCover.setCoverColor(Color.parseColor("#f6f6f6"));
//                int cint = DensityUtil.dip2px(C2CActivity.this,20);
//                itemSelfHolder.vHeadCover.setRadians(cint, cint, cint, cint,0);
//                itemSelfHolder.vHeadImage.setImageResource(MLOC.getHeadImage(C2CActivity.this,mDatas.get(position).getFromId()));
            }else if(currLayoutType == 1){//别人的信息
                final ViewHolder itemOtherHolder;
                if(convertView == null){
                    itemOtherHolder = new ViewHolder();
                    convertView = mInflater.inflate(R.layout.item_chat_msg_list_left,null);
                    itemOtherHolder.vUserId = (TextView) convertView.findViewById(R.id.item_user_id);
                    itemOtherHolder.vMsg = (TextView) convertView.findViewById(R.id.item_msg);
//                    itemOtherHolder.vHeadBg = convertView.findViewById(R.id.head_bg);
                    itemOtherHolder.sdv_header=convertView.findViewById(R.id.sdv_header);
//                    itemOtherHolder.vHeadCover = (CircularCoverView) convertView.findViewById(R.id.head_cover);
//                    itemOtherHolder.vHeadImage = (ImageView) convertView.findViewById(R.id.head_img);
                    convertView.setTag(itemOtherHolder);
                }else{
                    itemOtherHolder = (ViewHolder)convertView.getTag();
                }
                itemOtherHolder.vUserId.setText(doctorName);
                itemOtherHolder.vMsg.setText(mDatas.get(position).getMsg());
                if(doctorAvator!=null&&!"".equals(doctorAvator)) {
                    Uri uri = Uri.parse(doctorAvator);
                    itemOtherHolder.sdv_header.setImageURI(uri);
                }
//                itemOtherHolder.vHeadBg.setBackgroundColor(ColorUtils.getColor(C2CActivity.this,mDatas.get(position).getFromId()));
//                itemOtherHolder.vHeadCover.setCoverColor(Color.parseColor("#f6f6f6"));
//                int cint = DensityUtil.dip2px(C2CActivity.this,20);
//                itemOtherHolder.vHeadCover.setRadians(cint, cint, cint, cint,0);
//                itemOtherHolder.vHeadImage.setImageResource(MLOC.getHeadImage(C2CActivity.this,mDatas.get(position).getFromId()));
            }
            return convertView;
        }
    }

    public class ViewHolder{
        public TextView vUserId;
        public TextView vMsg;
//        public View vHeadBg;
//        public CircularCoverView vHeadCover;
//        public ImageView vHeadImage;
        public SimpleDraweeView sdv_header;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }




    //检查权限
    void check() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        } else {
            //TODO
            permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITEEXTRENAL_STOR);
            } else {
                permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO);
                } else {
                    connectDoctor();
                }
            }
        }
    }


    public final static int REQUEST_CAMERA = 1;

    public final static int REQUEST_WRITEEXTRENAL_STOR = 2;

    public final static int REQUEST_AUDIO = 3;


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                int permissionCheck;

                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITEEXTRENAL_STOR);
                    permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITEEXTRENAL_STOR);
                    } else {
                        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO);
                        } else {
                            connectDoctor();
                        }
                    }
                }
                break;
            case REQUEST_WRITEEXTRENAL_STOR:

                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO);
                    } else {
                        connectDoctor();
                    }

                }
                break;
            case REQUEST_AUDIO:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    connectDoctor();
                }
                break;

            default:
                break;
        }
    }


    public String chatType = "video";

    void connectDoctor() {
        ConnectDoctorPostBean bean = new ConnectDoctorPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.doctorId = mTargetId;
        if ("video".equals(chatType)) {
            bean.type = "1";
        } else {
            bean.type = "0";
        }
        ApiUtils.getApiService().connectDoctor(bean).enqueue(new TaiShengCallback<BaseBean<ConnectDoctorResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<ConnectDoctorResultBean>> response, BaseBean<ConnectDoctorResultBean> message) {
                ConnectDoctorResultBean bean = message.result;
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        mUserSig = bean.userSign;

//                        onJoinRoomByTecent(bean.roomId, bean.userId);
//                        onJoinRoomBySelf(bean.roomId, bean.userId);
                        break;
                    case Constants.DOCTOR_BUSY:
                        ToastUtil.showTost("医生忙碌中,请稍后联系");
                        break;
                    case Constants.DOCTOR_NOEXIST:
                        ToastUtil.showTost("医生不存在");

                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<ConnectDoctorResultBean>> call, Throwable t) {

            }
        });

    }



    private void onJoinRoomBySelf(final int roomId, final String userId){
        WebrtcUtil.callSingle(this,
                "",
                roomId+"",
                "video".equals(chatType)?true:false);
    }


    /**
     * Function: 读取用户输入，并创建（或加入）音视频房间
     * <p>
     * 此段示例代码最主要的作用是组装 TRTC SDK 进房所需的 TRTCParams
     * <p>
     * TRTCParams.sdkAppId => 可以在腾讯云实时音视频控制台（https://console.cloud.tencent.com/rav）获取
     * TRTCParams.userId   => 此处即用户输入的用户名，它是一个字符串
     * TRTCParams.roomId   => 此处即用户输入的音视频房间号，比如 125
     * TRTCParams.userSig  => 此处示例代码展示了两种获取 usersig 的方式，一种是从【控制台】获取，一种是从【服务器】获取
     * <p>
     * （1）控制台获取：可以获得几组已经生成好的 userid 和 usersig，他们会被放在一个 json 格式的配置文件中，仅适合调试使用
     * （2）服务器获取：直接在服务器端用我们提供的源代码，根据 userid 实时计算 usersig，这种方式安全可靠，适合线上使用
     * <p>
     * 参考文档：https://cloud.tencent.com/document/product/647/17275
     */
    private String mUserId = "";
    private String mUserSig = "";

    private void onJoinRoomByTecent(final int roomId, final String userId) {
        final Intent intent = new Intent(C2CActivity.this, TRTCMainActivity.class);
//        if (doctorBean != null) {
//            intent.putExtra("nickName", doctorBean.nickName);
//            intent.putExtra("title", doctorBean.title);
//            intent.putExtra("avatar", doctorBean.avatar);
//            intent.putExtra("doctorId", doctorBean.id);
//        }
        intent.putExtra("roomId", roomId);
        intent.putExtra("userId", userId);
        intent.putExtra("AppScene", TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL);
        intent.putExtra("sdkAppId", Constants.SDKAPPID);
        intent.putExtra("userSig", mUserSig);
        intent.putExtra("chatType", chatType);
        startActivityForResult(intent, 1);


//                mUserInfoLoader.getUserSigFromServer(finalSdkAppId, roomId, userId, "12345678", new TRTCGetUserIDAndUserSig.IGetUserSigListener() {
//                    @Override
//                    public void onComplete(String userSig, String errMsg) {
//                        if (!TextUtils.isEmpty(userSig)) {
//                            intent.putExtra("sdkAppId", finalSdkAppId);
//                            intent.putExtra("userSig", userSig);
////                            saveUserInfo(String.valueOf(roomId), userId, userSig);
//                            startActivity(intent);
//                        } else {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(DoctorDetailActivity.this, "从服务器获取userSig失败", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    }
//                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case TRTCMainActivity.TRTC_Normal_EXIT_RESULT:
                showGoRecommendDialog();
                break;

        }
    }


    public void showGoRecommendDialog() {
        final Dialog dialog = new AppDialog(this, R.layout.dialog_go_recommend, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, R.style.mystyle, Gravity.CENTER);
        dialog.getWindow().setWindowAnimations(0);

        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btn_go_recommend = (Button) dialog.findViewById(R.id.btn_go_recommend);


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        btn_go_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Intent intent = new Intent(C2CActivity.this, DoctorCommentActivity.class);
                intent.putExtra("id", mTargetId);
                startActivity(intent);

            }
        });

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
