package com.taisheng.now.chat;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.EventManage;
import com.taisheng.now.R;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.chat.websocket.WebSocketManager;
import com.taisheng.now.util.DensityUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class C2CActivity extends Activity implements  AdapterView.OnItemLongClickListener {

    private EditText vEditText;
    private TextView vTargetId;
    private ListView vMsgList;
    private View vSendBtn;

    private String mTargetId;
    public String doctorAvator;
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
        vEditText = (EditText) findViewById(R.id.id_input);
        mDatas = new ArrayList<>();

        mTargetId = getIntent().getStringExtra("targetId");
        ((TextView)findViewById(R.id.title_text)).setText(mTargetId);
        doctorAvator=getIntent().getStringExtra("doctorAvator");
        mAdapter = new MyChatroomListAdapter();
        vMsgList = (ListView) findViewById(R.id.msg_list);
        vMsgList.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        vMsgList.setOnItemLongClickListener(this);
        mAdapter = new MyChatroomListAdapter();
        vMsgList.setAdapter(mAdapter);


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
                +",fh,friend,fh,assets/images/user/avatar-2.jpg,fh,"+msg;

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
                itemSelfHolder.vUserId.setText(mDatas.get(position).getFromId());
                itemSelfHolder.vMsg.setText(mDatas.get(position).getMsg());
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
                itemOtherHolder.vUserId.setText(mDatas.get(position).getFromId());
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
}
