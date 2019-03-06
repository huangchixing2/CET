package com.electric.cet.mobile.android.pq.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.R;
import com.electric.cet.mobile.android.pq.db.SQLhelper_Device;
import com.electric.cet.mobile.android.pq.ui.adapter.DataCountAdapter;

import java.util.ArrayList;
import java.util.List;

public class MapViewActivity extends Activity implements BDLocationListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    private MapView mapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient;
    private LatLng latLng;
    private boolean isFirstLoc = true; // 是否首次定位
    public static final int BAIDU_READ_PHONE_STATE = 1001;

    private RadioGroup map_rg;
    private RadioButton map_rb;
    private RadioButton list_rb;
    private TextView title;
    private ListView listView;
    private int sourceFlag = -1;


    private List<DataBean> devicesList = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map_view);

        initView();
        initData();
    }

    private void initView() {
        sourceFlag = getIntent().getIntExtra("source_flag",1);
        map_rg = (RadioGroup) findViewById(R.id.cet_cockpit_mapview_rg);
        map_rb = (RadioButton) findViewById(R.id.cet_cockpit_map_rb);
        list_rb = (RadioButton) findViewById(R.id.cet_cockpit_list_rb);
        listView = (ListView) findViewById(R.id.cet_mapview_ll);
        title = (TextView) findViewById(R.id.cockpit_tab);
        DataCountAdapter dcAdapter = new DataCountAdapter(this, getData());
        listView.setAdapter(dcAdapter);
        listView.setOnItemClickListener(this);
        map_rg.setOnCheckedChangeListener(this);
        mapView = (MapView) findViewById(R.id.cet_mapview);
        //判断是否为android6.0系统版本，如果是，需要动态添加权限
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        } else {
            initMap();//init为定位方法
        }

    }

    private void initData() {

    }

    private void initMap() {
        //获取地图控件引用
        mBaiduMap = mapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(10 * 1000); // 设置扫描间隔，单位是毫秒
        option.setIsNeedAddress(true);// 设置是否需要地址信息，默认为无地址
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        //图片点击事件，回到定位点
//        mLocationClient.requestLocation();
        //点击不同按钮显示对应的标题文字
        String  value = getIntent().getStringExtra("title");
        title.setText(value);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        MapView.setMapCustomEnable(false);
        mapView = null;
    }

    public void showContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "没有权限,请手动开启定位权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(MapViewActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
        } else {
            initMap();
        }
    }

    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    initMap();
                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(getApplicationContext(), "获取位置权限失败，请手动开启", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
//        Toast.makeText(MapViewActivity.this,bdLocation.getLatitude() +"````"+bdLocation.getLongitude()+"__"+bdLocation.getLocType(),Toast.LENGTH_LONG).show();
        latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                .direction(100)
                .latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        if (isFirstLoc) {
            isFirstLoc = false;
            addMarker(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(latLng).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }

    private void addMarker(double latitude, double longitude) {
        BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromBitmap(((BitmapDrawable) getResources().getDrawable(R.mipmap.cet_nor_marker)).getBitmap());
        BitmapDescriptor markerIcon2 = BitmapDescriptorFactory.fromBitmap(((BitmapDrawable) getResources().getDrawable(R.mipmap.cet_selected_marker)).getBitmap());
        LatLng ll;
        MarkerOptions markerOptions;
        ll = new LatLng(latitude + 0.00000000002, longitude + 0.00000000004);
        markerOptions = new MarkerOptions();
        markerOptions.position(ll);
        markerOptions.icon(markerIcon);
        markerOptions.draggable(false);
        markerOptions.anchor(0.5f, 1.0f);
        markerOptions.alpha(0.8f);
        markerOptions.zIndex(1);
        mBaiduMap.addOverlay(markerOptions);
        ll = new LatLng(latitude + 0.0011100202, longitude + 0.00001000204);
        markerOptions = new MarkerOptions();
        markerOptions.position(ll);
        markerOptions.icon(markerIcon);
        markerOptions.draggable(false);
        markerOptions.anchor(0.5f, 1.0f);
        markerOptions.alpha(0.8f);
        markerOptions.zIndex(1);
        mBaiduMap.addOverlay(markerOptions);
        ll = new LatLng(latitude + 0.0011100202, longitude + 0.00101000204);
        markerOptions = new MarkerOptions();
        markerOptions.position(ll);
        markerOptions.icon(markerIcon2);
        markerOptions.draggable(false);
        markerOptions.anchor(0.5f, 1.0f);
        markerOptions.alpha(0.8f);
        markerOptions.zIndex(1);
        mBaiduMap.addOverlay(markerOptions);
        ll = new LatLng(latitude + 0.0031100202, longitude + 0.00201000204);
        markerOptions = new MarkerOptions();
        markerOptions.position(ll);
        markerOptions.icon(markerIcon);
        markerOptions.draggable(false);
        markerOptions.anchor(0.5f, 1.0f);
        markerOptions.alpha(0.8f);
        markerOptions.zIndex(1);
        mBaiduMap.addOverlay(markerOptions);

    }

    //查询数据库
    private List<DataBean> getData() {
        devicesList.clear();
        devicesList = SQLhelper_Device.Instance(this).queryDeviceList(sourceFlag);
        Log.d("guol","devicesList.size:"+devicesList.size());//为何只有一条数据？
        return devicesList;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.cet_cockpit_map_rb:
                map_rb.setChecked(true);
                mapView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                break;
            case R.id.cet_cockpit_list_rb:
                list_rb.setChecked(true);
                mapView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        Log.i("devicesId",devicesList.get(position).getDeviceId()+"mapview");
        SharedPreferences sp = getSharedPreferences("data",0);
        sp.edit().putLong("deviceId",devicesList.get(position).getDeviceId()).commit();
        setResult(1002,intent);
        finish();
    }
}
