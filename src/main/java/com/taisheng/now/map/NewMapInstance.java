package com.taisheng.now.map;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.taisheng.now.SampleAppLike;
import com.taisheng.now.SampleApplication;

public class NewMapInstance extends BDAbstractLocationListener {


    public static NewMapInstance instance;

    private NewMapInstance() {

    }


    public static NewMapInstance getInstance() {
        if (instance == null) {
            synchronized (NewMapInstance.class) {
                if (instance == null) {
                    instance = new NewMapInstance();
                }
            }
        }
        return instance;
    }

    private MapView mapView;
    public BaiduMap mBaiduMap;

    public void init(MapView mapView) {
        this.mapView = mapView;
        initMap();
    }


    public int LocationMapType = BaiduMap.MAP_TYPE_NORMAL;

    private void initMap() {
        mapView.showZoomControls(false);//不显示放大缩小控件
        mBaiduMap = mapView.getMap();
        mBaiduMap.setMapType(LocationMapType);//只显示普通地图就好
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//卫星地图
        mBaiduMap.setBuildingsEnabled(true);//设置是否允许楼块效果

//        mBaiduMap.setOnBaseIndoorMapListener(new BaiduMap.OnBaseIndoorMapListener() {
//            @Override
//            public void onBaseIndoorMapMode(boolean b, MapBaseIndoorMapInfo mapBaseIndoorMapInfo) {
//                if (b) {
//                    // 进入室内图
//                    // 通过获取回调参数 mapBaseIndoorMapInfo 来获取室内图信息，包含楼层信息，室内ID等
//                } else {
//                    // 移除室内图
//                }
//            }
//        });

//        mBaiduMap.setIndoorEnable(true);//设置是否显示室内图, 默认室内图不显示
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        UiSettings UiSettings = mBaiduMap.getUiSettings();
        UiSettings.setRotateGesturesEnabled(false);//屏蔽旋转
        initLocation();
    }


    public LocationClient mLocationClient = null;

    /**
     * 初始化LocationClient类
     */
    public void initLocation() {

        // 定位初始化
        mLocationClient = new LocationClient(SampleAppLike.mcontext);
        mLocationClient.registerLocationListener(this);

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setAddrType("all");// 返回的定位结果包含地址信息,(注意如果想获取关于地址的信息，这里必须进行设置)
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        // 设置定位方式的优先级。
        // 当gps可用，而且获取了定位结果时，不再发起网络请求，直接返回给用户坐标。这个选项适合希望得到准确坐标位置的用户。如果gps不可用，再发起网络请求，进行定位。
        option.setPriority(LocationClientOption.GpsFirst);
        mLocationClient.setLocOption(option);


        startLoc();
    }


    /**
     * 开始定位
     */
    public void startLoc() {
        startLocListener(1000);
    }


    /**
     * 开始位置监听
     */
    public void startLocListener(int scan) {
        if (null != mLocationClient && !mLocationClient.isStarted()) {
            mLocationClient.getLocOption().setScanSpan(scan);
            mLocationClient.start();
        }
    }

    /**
     * 停止位置监听
     */
    public void stopLocListener() {
        if (null != mLocationClient && mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }


    @Override
    public void onReceiveLocation(BDLocation location) {
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取经纬度相关（常用）的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

        double latitude = location.getLatitude();    //获取纬度信息
        double longitude = location.getLongitude();    //获取经度信息
        float radius = location.getRadius();    //获取定位精度，默认值为0.0f

        String coorType = location.getCoorType();
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

        int errorCode = location.getLocType();
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明




        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        float f = mBaiduMap.getMaxZoomLevel();//19.0
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(latLng, f - 2);
        mBaiduMap.animateMapStatus(u);
        setCenter(latLng, 300);
        stopLocListener();
    }


    /**
     * 按动画移动到中心点
     *
     * @param centerPoint
     */
    private void setCenter(LatLng centerPoint, int delayMills) {
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(centerPoint);
        mBaiduMap.animateMapStatus(mapStatusUpdate, delayMills);
    }
}
