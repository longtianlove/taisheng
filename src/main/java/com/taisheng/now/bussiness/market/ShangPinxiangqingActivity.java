package com.taisheng.now.bussiness.market;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.post.AddgouwuchePostBean;
import com.taisheng.now.bussiness.bean.post.BaseListPostBean;
import com.taisheng.now.bussiness.bean.post.ShangpinxaingqingPostBean;
import com.taisheng.now.bussiness.bean.result.market.DizhilistResultBean;
import com.taisheng.now.bussiness.bean.result.market.JsonRootBean;
import com.taisheng.now.bussiness.market.dingdan.DingdanjiesuanActivity;
import com.taisheng.now.bussiness.market.dizhi.DizhiBianjiActivity;
import com.taisheng.now.bussiness.market.gouwuche.ShoppingCartActivity;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.util.DialogUtil;
import com.taisheng.now.util.ToastUtil;
import com.taisheng.now.view.banner.BannerViewPager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dragon on 2019/6/28.
 */

public class ShangPinxiangqingActivity extends BaseActivity {
    View iv_back;


    private FrameLayout bannerContaner;
    BannerViewPager bannerViewPager;
    private View bannerView;
    public TextView tv_counterprice;
    public TextView tv_retailprice;
    public TextView tv_name;
    public TextView tv_jianjie;


    public View iv_gouwuche;

    public View ll_guige;
    private PopupWindow popupWindow;
    private View contentView;




    public WebView wv_shangpinxiangqing;

    public View tv_addgouwuche;
    public View tv_goumai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpinxiangqing);
        initView();

        initData();
    }

    void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        bannerContaner = (FrameLayout) findViewById(R.id.bannerContaner);
        bannerContaner.setVisibility(View.VISIBLE);
        bannerViewPager = new BannerViewPager(this);
        bannerView = bannerViewPager.getContentView();
        bannerContaner.addView(bannerView);

        iv_gouwuche=findViewById(R.id.iv_gouwuche);
        iv_gouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShangPinxiangqingActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
        ll_guige=findViewById(R.id.ll_guige);
        ll_guige.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//从底部显示
                popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
            }
        });

        //加载弹出框的布局
        contentView = LayoutInflater.from(ShangPinxiangqingActivity.this).inflate(
                R.layout.pop, null);
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画，指定刚才定义的style
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);







        wv_shangpinxiangqing=findViewById(R.id.wv_shangpinxiangqing);
        wv_shangpinxiangqing.getSettings().setJavaScriptEnabled(true);
        wv_shangpinxiangqing.setWebViewClient(new WebViewClient());







        tv_addgouwuche = findViewById(R.id.tv_addgouwuche);
        tv_addgouwuche.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//todo 添加到购物车
                AddgouwuchePostBean bean = new AddgouwuchePostBean();
                bean.userId = UserInstance.getInstance().getUid();
                bean.token = UserInstance.getInstance().getToken();
                bean.goodsId = "1181000";
                bean.productId = "2";
                bean.number = "2";
                ApiUtils.getApiService().addgouwuche(bean).enqueue(new TaiShengCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response, BaseBean message) {
                        switch (message.code) {
                            case Constants.HTTP_SUCCESS:
                                ToastUtil.showAtCenter("添加成功");
                                break;

                        }
                    }

                    @Override
                    public void onFail(Call<BaseBean> call, Throwable t) {

                    }
                });

            }
        });

        tv_goumai = findViewById(R.id.tv_goumai);
        tv_goumai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
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
                                            Intent intent = new Intent(ShangPinxiangqingActivity.this, DingdanjiesuanActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Intent intent = new Intent(ShangPinxiangqingActivity.this, DizhiBianjiActivity.class);
                                            startActivity(intent);
                                        }
                                        break;
                                }
                            }

                    @Override
                    public void onFail(Call<BaseBean<DizhilistResultBean>> call, Throwable t) {

                        DialogUtil.closeProgress();
                    }
                });


            }
        });

        tv_counterprice = findViewById(R.id.tv_counterprice);
        tv_retailprice = findViewById(R.id.tv_retailprice);
        tv_name = findViewById(R.id.tv_name);
        tv_jianjie = findViewById(R.id.tv_jianjie);

    }


    public String goodsid;
    //todo 赋值
    public String productid = 1 + "";
    public String number = 1 + "";

    void initData() {
        Intent intent = getIntent();
        goodsid = intent.getStringExtra("goodsid");

        ShangpinxaingqingPostBean bean = new ShangpinxaingqingPostBean();
        bean.id = goodsid;
        bean.userId = UserInstance.getInstance().getUid();
        bean.token = UserInstance.getInstance().getToken();
        ApiUtils.getApiService().shangpinxiangqing(bean).enqueue(new TaiShengCallback<BaseBean<JsonRootBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<JsonRootBean>> response, BaseBean<JsonRootBean> message) {
                switch (message.code) {
                    case Constants.HTTP_SUCCESS:
                        //商品轮播图
                        if (message.result.goodsEntity != null && message.result.goodsEntity.gallery != null && message.result.goodsEntity.gallery.size() > 0) {
                            ArrayList<String> pictureUrls = new ArrayList<>();
                            for (String urlTemp : message.result.goodsEntity.gallery) {
                                pictureUrls.add(urlTemp);
                            }
                            bannerViewPager.setPictureUrls(pictureUrls);
                            bannerViewPager.setmScrollSpeed(500);
                            bannerViewPager.setOnItemClickListener(new BannerViewPager.ViewPagerItemListener() {
                                @Override
                                public void onViewPagerItemClick(int i) {

                                }
                            });
                            bannerViewPager.madapter.notifyDataSetChanged();

                            tv_counterprice.setText(message.result.goodsEntity.counterPrice + "");
                            tv_retailprice.setText(message.result.goodsEntity.retailPrice + "");
                            tv_retailprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                            tv_name.setText(message.result.goodsEntity.name);
                            tv_jianjie.setText(message.result.goodsEntity.brief);

                            wv_shangpinxiangqing.loadData(Html.fromHtml(message.result.goodsEntity.detail).toString(), "text/html", "UTF-8");
                        }


                        break;
                }
            }

            @Override
            public void onFail(Call<BaseBean<JsonRootBean>> call, Throwable t) {

            }
        });

    }
}
