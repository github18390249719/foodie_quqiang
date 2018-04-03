package com.example.myapplication.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.myapplication.R;

/**
 * Created by Administrator on 2017/8/14.
 */

@SuppressWarnings("DefaultFileTemplate")
public class Fragment_perimeter extends Fragment {
    private AMap aMap = null;
    private MapView mapView = null;
    /*
        private PoiResult poiResult; // poi返回的结果
        private int currentPage = 0;// 当前页面，从0开始计数
        private PoiSearch.Query query;// Poi查询条件类
        private LatLonPoint lp = new LatLonPoint(39.993743, 116.472995);// 116.472995,39.993743
        private Marker locationMarker; // 选择的点
        private Marker detailMarker;
        private Marker mlastMarker;
        private PoiSearch poiSearch;
        private myPoiOverlay poiOverlay;// poi图层
        private List<PoiItem> poiItems;// poi数据

        private RelativeLayout mPoiDetail;
        private TextView mPoiName, mPoiAddress;
        private String keyWord = "";
        private EditText mSearchText;
        */
    private MyLocationStyle myLocationStyle = null;
    private AMapLocationClient aMapLocationClient = null;
    private AMapLocationClientOption aMapLocationClientOption = null;
    private AMapLocationListener aMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");

                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                //解析定位结果，
                String result = sb.toString();
                Log.i("AmapInfo", result);
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perimeter_fragment_main_layout, container, false);

        mapView = view.findViewById(R.id.fg_perimeter_map);
        mapView.onCreate(savedInstanceState);

        initAmap();

        return view;
    }

    private void initAmap() {
        aMap = mapView.getMap();
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);

        aMapLocationClientOption = new AMapLocationClientOption();
        //设置定位模式：高精度定位模式，会同时使用网络定位和GPS定位，优先返回最高精度的定位结果，以及对应的地址描述信息。
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置首次定位是否等待GPS定位结果，默认false
        aMapLocationClientOption.setGpsFirst(false);
        //设置联网超时时间，默认值：30000毫秒
        aMapLocationClientOption.setHttpTimeOut(20000);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        //aMapLocationClientOption.setOnceLocationLatest(true);
        //设置是否单次定位,默认false
        aMapLocationClientOption.setOnceLocation(false);
        if (aMapLocationClient == null)
            aMapLocationClient = new AMapLocationClient(this.getActivity());
        aMapLocationClient.setLocationOption(aMapLocationClientOption);
        aMapLocationClient.setLocationListener(aMapLocationListener);
        aMapLocationClient.startLocation();

        //设置定位蓝点样式类
        if(myLocationStyle == null)
            myLocationStyle = new MyLocationStyle();
        //定位、但不会移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        aMap.setMyLocationStyle(myLocationStyle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
