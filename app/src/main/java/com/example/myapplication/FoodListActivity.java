package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by 蒋星 on 2017/10/12.
 */

public class FoodListActivity extends Activity {
    private ListView foodlist;
    private LinkedList<Shop> mData;  //Food对象列表
    private myAdapter mAdapter;  //ListView适配器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面切换效果
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        Transition slide=bulid();
        Transition explode=TransitionInflater.from(this).inflateTransition(R.transition.explode);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);

        setContentView(R.layout.activity_foodlist);
        foodlist=(ListView)findViewById(R.id.food_ranking_list_lv);  //关联控件
        init();
        mAdapter=new myAdapter();
        foodlist.setAdapter(mAdapter);  //为ListView绑定适配器
    }

    //创建一个Activity转场过度动画
    private Transition bulid(){
        Slide slide=new Slide();
        slide.setDuration(200);
        slide.setInterpolator(new LinearInterpolator());
        slide.setSlideEdge(Gravity.RIGHT);
        return slide;
    }

    //初始化设置
    protected void init(){
        //初始化推荐美食数据
        mData=new LinkedList<Shop>();
        mData.add(new Shop("重庆火锅",80.0,96.0,R.mipmap.ic_launcher));
        mData.add(new Shop("酸菜鱼",70.0,94.0,R.mipmap.ic_launcher));
        mData.add(new Shop("辣子鸡",80.0,90.0,R.mipmap.ic_launcher));
    }

    /**
     * 将适配器整合到Activity内部，便于直接获取数据源，加强模块内聚
     */
    class myAdapter extends BaseAdapter{

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
        public View getView(int position, View covertView, ViewGroup viewGroup) {
            covertView= LayoutInflater.from(FoodListActivity.this).inflate(R.layout.food_list_item,null);
            TextView foodname=(TextView)covertView.findViewById(R.id.food_list_item_foodname_tv);
            TextView foodprice=(TextView)covertView.findViewById(R.id.food_list_item_foodprice_tv);
            TextView foodgrade=(TextView)covertView.findViewById(R.id.food_list_item_like_percent_tv);
            ImageView foodimage=(ImageView)covertView.findViewById(R.id.food_list_item_iv);
            foodname.setText(mData.get(position).getShopname());
            foodprice.setText("¥ "+Double.toString(mData.get(position).getShopprice()));
            foodgrade.setText("好评度："+Double.toString(mData.get(position).getShopgrade()));
            foodimage.setBackgroundResource(mData.get(position).getShopicon());
            return covertView;
        }
    }
}
