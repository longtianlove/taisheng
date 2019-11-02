package com.taisheng.now.bussiness.market.dingdan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.post.BaseListPostBean;
import com.taisheng.now.bussiness.bean.post.BasePostBean;
import com.taisheng.now.bussiness.bean.post.CreateOrderPostBean;
import com.taisheng.now.bussiness.bean.post.WexinZhifuPostBean;
import com.taisheng.now.bussiness.bean.result.CreateOrderResultBean;
import com.taisheng.now.bussiness.bean.result.PostageResultBean;
import com.taisheng.now.bussiness.bean.result.market.DizhilistBean;
import com.taisheng.now.bussiness.bean.result.market.DizhilistResultBean;
import com.taisheng.now.bussiness.bean.result.xiadanshangpinBean;
import com.taisheng.now.bussiness.market.DingdanInstance;
import com.taisheng.now.bussiness.market.ShangPinxiangqingActivity;
import com.taisheng.now.bussiness.market.dizhi.DizhiActivity;
import com.taisheng.now.bussiness.market.youhuijuan.MyYouhuijuanActivity;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.test.TestActivity;
import com.taisheng.now.test.WechatResultBean;
import com.taisheng.now.util.DialogUtil;
import com.taisheng.now.view.WithScrolleViewListView;
import com.taisheng.now.view.chenjinshi.StatusBarUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by an on 2017/6/14.
 * 购物车界面
 */
public class DingdanjiesuanActivity extends Activity implements View.OnClickListener {

    View btnBack;


    View ll_dizhi;
    TextView tv_dizhiname;
    TextView tv_phone;
    TextView tv_address;


    View ll_youhuijuan;
    TextView tv_youhuijuan;


    public WithScrolleViewListView lv_jiesuan;
    ArticleAdapter madapter;


    TextView tv_jianyouhuijuan;
    TextView tv_youfei;
    TextView tv_zongjia;


    EditText et_beizhu;
    View btn_qujiesuan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diandanjiesuan);
        initView();

    }

    private void initView() {

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);

        ll_dizhi = findViewById(R.id.ll_dizhi);
        ll_dizhi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DingdanjiesuanActivity.this, DizhiActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        tv_dizhiname = findViewById(R.id.tv_dizhiname);
        tv_phone = findViewById(R.id.tv_phone);
        tv_address = findViewById(R.id.tv_address);

        ll_youhuijuan = findViewById(R.id.ll_youhuijuan);
        ll_youhuijuan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DingdanjiesuanActivity.this, MyYouhuijuanActivity.class);
                startActivityForResult(intent, 2);
            }
        });
        tv_youhuijuan = findViewById(R.id.tv_youhuijuan);

        lv_jiesuan = findViewById(R.id.lv_jiesuan);
        madapter = new ArticleAdapter(DingdanjiesuanActivity.this);
        madapter.mData = DingdanInstance.getInstance().dingdanList;
        lv_jiesuan.setAdapter(madapter);


        tv_jianyouhuijuan = findViewById(R.id.tv_jianyouhuijuan);
        tv_jianyouhuijuan.setText("-¥0");
        tv_youfei = findViewById(R.id.tv_youfei);
        tv_youfei.setText("￥0");

        tv_zongjia = findViewById(R.id.tv_zongjia);
        tv_zongjia.setText(DingdanInstance.getInstance().zongjia);

        et_beizhu = findViewById(R.id.et_beizhu);
        btn_qujiesuan = findViewById(R.id.btn_qujiesuan);
        btn_qujiesuan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ("请选择".equals(tv_youhuijuan.getText())) {
                    DingdanInstance.getInstance().couponId = "";
                }

                CreateOrderPostBean bean = new CreateOrderPostBean();
                bean.userId = UserInstance.getInstance().getUid();
                bean.token = UserInstance.getInstance().getToken();
                bean.addressId = DingdanInstance.getInstance().addressId;
                bean.couponId = DingdanInstance.getInstance().couponId;
                bean.flag = DingdanInstance.getInstance().flag;
                bean.goodsList = DingdanInstance.getInstance().dingdanList;
                bean.postFeeId = DingdanInstance.getInstance().postFeeId;
                bean.message = et_beizhu.getText().toString();
                ApiUtils.getApiService().createOrder(bean).enqueue(new TaiShengCallback<BaseBean<CreateOrderResultBean>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<CreateOrderResultBean>> response, BaseBean<CreateOrderResultBean> message) {
                        switch (message.code) {
                            case Constants.HTTP_SUCCESS:

                                //请求支付接口
//                                String orderId=message.message;
                                WexinZhifuPostBean bean1 = new WexinZhifuPostBean();
                                bean1.orderId = message.message;
                                bean1.userId = UserInstance.getInstance().getUid();
                                bean1.token = UserInstance.getInstance().getToken();
                                ApiUtils.getApiService().weChatPay(bean1).enqueue(new TaiShengCallback<WechatResultBean>() {
                                    @Override
                                    public void onSuccess(Response<WechatResultBean> response, WechatResultBean message) {
                                        switch (message.code) {
                                            case Constants.HTTP_SUCCESS:
                                                IWXAPI api = WXAPIFactory.createWXAPI(DingdanjiesuanActivity.this, Constants.WXAPPID, false);//填写自己的APPIDapi.registerApp("wxAPPID");//填写自己的APPID，注册本身
                                                PayReq req = new PayReq();//PayReq就是订单信息对象
                                                req.appId = Constants.WXAPPID;//你的微信appid
                                                req.partnerId = message.partnerid;//商户号
                                                req.prepayId = message.prepayid;//预支付交易会话ID
                                                req.nonceStr = message.noncestr;//随机字符串
                                                req.timeStamp = message.timestamp + "";//时间戳
                                                req.packageValue = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
                                                req.sign = message.sign;//签名
                                                api.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求
                                                break;
                                        }


                                    }

                                    @Override
                                    public void onFail(Call<WechatResultBean> call, Throwable t) {

                                    }
                                });

                                break;
                        }
                    }

                    @Override
                    public void onFail(Call<BaseBean<CreateOrderResultBean>> call, Throwable t) {

                    }
                });
            }
        });
        initData();
    }


    //初始化数据
    protected void initData() {
//获取地址信息
        BaseListPostBean bean = new BaseListPostBean();
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        bean.pageNo = 1;
        bean.pageSize = 10;

        ApiUtils.getApiService().addressList(bean).enqueue(new TaiShengCallback<BaseBean<DizhilistResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<DizhilistResultBean>> response, BaseBean<DizhilistResultBean> message) {

                DialogUtil.closeProgress();
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        if (message.result.records != null && message.result.records.size() > 0) {
                            DizhilistBean bean = new DizhilistBean();
                            int i = 0;
                            for (DizhilistBean tempbean : message.result.records) {
                                i++;
                                if (tempbean.defaultAddress == 1) {
                                    bean = tempbean;
                                    break;
                                }

                                if (i == message.result.records.size()) {
                                    bean = tempbean;
                                }

                            }
                            DingdanInstance.getInstance().addressId = bean.id;
                            DingdanInstance.getInstance().name = bean.name;
                            DingdanInstance.getInstance().phone = bean.phone;
                            DingdanInstance.getInstance().address = bean.province + bean.city + bean.county + bean.addressDetail;
                            tv_dizhiname.setText(bean.name);
                            tv_phone.setText(bean.phone);
                            tv_address.setText(bean.province + bean.city + bean.county + bean.addressDetail);

                        } else {
//todo 什么逻辑
                        }
                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<DizhilistResultBean>> call, Throwable t) {

                DialogUtil.closeProgress();
            }
        });


        BasePostBean basePostBean = new BasePostBean();
        basePostBean.userId = UserInstance.getInstance().getUid();
        basePostBean.token = UserInstance.getInstance().getToken();


        //获取邮费
        ApiUtils.getApiService().getPostage(basePostBean).enqueue(new TaiShengCallback<BaseBean<PostageResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<PostageResultBean>> response, BaseBean<PostageResultBean> message) {
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        youfei = message.result.money + "";
                        DingdanInstance.getInstance().postFeeId = message.result.id;
                        tv_youfei.setText("￥" + youfei);
                        tv_zongjia.setText("¥" + (Double.parseDouble(DingdanInstance.getInstance().zongjia) - Double.parseDouble(discount) + Double.parseDouble(youfei)));

                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<PostageResultBean>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }


    String discount = "0";
    String youfei = "0";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                String name = data.getStringExtra("name");
                String phone = data.getStringExtra("phone");
                String address = data.getStringExtra("address");
                tv_dizhiname.setText(name);
                tv_phone.setText(phone);
                tv_address.setText(address);
                break;
            case 2:
                discount = DingdanInstance.getInstance().tv_discount;
                tv_youhuijuan.setText("¥" + discount);
                tv_jianyouhuijuan.setText("-¥" + discount);
                tv_zongjia.setText("¥" + (Double.parseDouble(DingdanInstance.getInstance().zongjia) - Double.parseDouble(discount) + Double.parseDouble(youfei)));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }


        tv_dizhiname.setText(DingdanInstance.getInstance().name);
        tv_phone.setText(DingdanInstance.getInstance().phone);
        tv_address.setText(DingdanInstance.getInstance().address);
    }


    class ArticleAdapter extends BaseAdapter {

        public Context mcontext;

        List<xiadanshangpinBean> mData = new ArrayList<xiadanshangpinBean>();

        public ArticleAdapter(Context context) {
            this.mcontext = context;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 声明内部类
            ArticleAdapter.Util util = null;
            // 中间变量
            final int flag = position;
            if (convertView == null) {
                util = new ArticleAdapter.Util();
                LayoutInflater inflater = LayoutInflater.from(mcontext);
                convertView = inflater.inflate(R.layout.item_dingdannshangpinn, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.sdv_article = convertView.findViewById(R.id.sdv_article);
                util.tv_name = convertView.findViewById(R.id.tv_name);
                util.tv_counterprice = convertView.findViewById(R.id.tv_counterprice);
//                util.tv_retailprice = convertView.findViewById(R.id.tv_retailprice);
                util.tv_number = convertView.findViewById(R.id.tv_number);

                convertView.setTag(util);
            } else {
                util = (ArticleAdapter.Util) convertView.getTag();
            }
            xiadanshangpinBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo 进入商品详情

                    Intent intent = new Intent(DingdanjiesuanActivity.this, ShangPinxiangqingActivity.class);
                    intent.putExtra("goodsid", bean.goodsId);

                    startActivity(intent);
                }
            });

            String temp_url = bean.picUrl;
            if (temp_url == null || "".equals(temp_url)) {
                util.sdv_article.setBackgroundResource(R.drawable.article_default);

            } else {
                Uri uri = Uri.parse(temp_url);
                util.sdv_article.setImageURI(uri);
            }
            util.tv_name.setText(bean.name);
            util.tv_counterprice.setText(bean.counterPrice + "");
//            util.tv_retailprice.setText(bean.retailPrice + "");
//            util.tv_retailprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            util.tv_number.setText("x " + bean.number);
            return convertView;
        }


        class Util {
            View ll_all;
            SimpleDraweeView sdv_article;
            TextView tv_name;
            TextView tv_counterprice;
            TextView tv_number;
            TextView tv_retailprice;

        }
    }
}
