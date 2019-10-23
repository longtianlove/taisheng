package com.taisheng.now.bussiness.market;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.base.BaseBean;
import com.taisheng.now.bussiness.bean.post.ShangpinxaingqingPostBean;
import com.taisheng.now.bussiness.me.FuwuxieyiActivity;
import com.taisheng.now.bussiness.me.YisixieyiActivity;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.http.ApiUtils;
import com.taisheng.now.http.TaiShengCallback;
import com.taisheng.now.util.Apputil;
import com.taisheng.now.view.banner.BannerViewPager;

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
        bannerViewPager.setLocalPictureIds();
        bannerViewPager.setmScrollSpeed(500);
        bannerViewPager.madapter.notifyDataSetChanged();
        bannerView = bannerViewPager.getContentView();
        bannerViewPager.setOnItemClickListener(new BannerViewPager.ViewPagerItemListener() {
            @Override
            public void onViewPagerItemClick(int i) {

            }
        });
        bannerContaner.addView(bannerView);

        tv_addgouwuche=findViewById(R.id.tv_addgouwuche);
        tv_addgouwuche.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//todo 添加到购物车
            }
        });

        tv_goumai = findViewById(R.id.tv_goumai);
        tv_goumai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShangPinxiangqingActivity.this, DingdanjiesuanActivity.class);
                startActivity(intent);
            }
        });
    }


    public String goodsid;

    void initData() {
        Intent intent = getIntent();
        goodsid = intent.getStringExtra("goodsid");

        ShangpinxaingqingPostBean bean=new ShangpinxaingqingPostBean();
        bean.id=goodsid;
        bean.userId= UserInstance.getInstance().getUid();
        bean.token=UserInstance.getInstance().getToken();
        ApiUtils.getApiService().shangpinxiangqing(bean).enqueue(new TaiShengCallback<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response, BaseBean message) {

            }

            @Override
            public void onFail(Call<BaseBean> call, Throwable t) {

            }
        });

    }
}
