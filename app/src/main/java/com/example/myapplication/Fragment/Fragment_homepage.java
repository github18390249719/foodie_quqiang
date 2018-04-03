package com.example.myapplication.Fragment;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.FoodContentActivity;
import com.example.myapplication.FoodRankingListActivity;
import com.example.myapplication.FoodSearchActivity;
import com.example.myapplication.LocationActivity;
import com.example.myapplication.R;
import com.example.myapplication.Shop;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 蒋星 on 2017/10/6.
 */

public class Fragment_homepage extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private TextView Location;
    private LinearLayout location, food_search, shoplist;
    private RelativeLayout tv_1;
    private LinkedList<Shop> mData;
    private ViewPager viewpager;
    private ArrayList<View> viewlist;
    private ArrayList<View> dotlist;
    private int currentPosition = 0, viewCount = 4;
    private long releasetime;//记录上一次切换页面的时间
    //设置显示图片的线性布局的资源ID数组
    private int[] viewsrc = {R.layout.page_one, R.layout.page_two, R.layout.page_three,R.layout.page_four};
    //设置线性布局的背景图片的资源ID数组
    private int[] imagesrc = {R.mipmap.food1, R.mipmap.food2, R.mipmap.food3, R.mipmap.food4};
    //设置指针的资源ID数组
    private int[] dotsrc = {R.id.homepage_vp_dot1_v, R.id.homepage_vp_dot2_v, R.id.homepage_vp_dot3_v, R.id.homepage_vp_dot4_v};

    /**
     * 使用Handler机制切换图片以实现轮播效果
     */
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1) {
                currentPosition += 1;
                currentPosition = currentPosition % 6;
                viewpager.setCurrentItem(currentPosition);
                releasetime=System.currentTimeMillis();
                myHandler.removeCallbacks(runnable);
                myHandler.postDelayed(runnable,4000);
            }else{
                myHandler.removeCallbacks(runnable);
                myHandler.postDelayed(runnable,4000);
            }
        }
    };

    /**
     * 创建一个子线程用于判断当前页面是否被点击
     * 如果被点击，则重新计时轮播
     */
    final Runnable runnable=new Runnable() {
        @Override
        public void run() {
            long now=System.currentTimeMillis();
            Message msg=new Message();
            if(now-releasetime>3800){
                msg.what=1;
                myHandler.sendMessage(msg);
            }else{
                msg.what=0;
                myHandler.sendMessage(msg);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_homepage, container, false);
        initView(view);//关联UI控件
        setListener(); //为UI控件设置监听器
        initData(); //初始化数据
        addData(mData);
        //启动轮播
        Message msg=new Message();
        msg.what=1;
        myHandler.sendMessage(msg);
        return view;
    }

    protected void initView(View view) {
        tv_1 = (RelativeLayout) view.findViewById(R.id.homepage_frl_rl);  //关联控件
        Location = view.findViewById(R.id.homepage_position_tv);
        location = (LinearLayout) view.findViewById(R.id.homepage_position_ll);
        food_search = (LinearLayout) view.findViewById(R.id.homepage_food_search_ll);
        shoplist = (LinearLayout) view.findViewById(R.id.homepage_store_recommend_ll);
        viewpager = (ViewPager) view.findViewById(R.id.homepage_vp);
        viewlist = new ArrayList<View>();
        dotlist = new ArrayList<View>();
        LayoutInflater li = getActivity().getLayoutInflater();
        //在需要轮播的图片组前加上最后一个图片
        viewlist.add(li.inflate(R.layout.page_four, null, false));
        viewlist.get(0).setBackgroundResource(imagesrc[3]);
        //循环获取用于显示图片的View和指示指针View以及给View设置背景图片
        for (int i = 0; i < viewCount; i++) {
            viewlist.add(li.inflate(viewsrc[i], null, false));
            viewlist.get(i+1).setBackgroundResource(imagesrc[i]);
            dotlist.add(view.findViewById(dotsrc[i]));
        }
        //在需要轮播的图片组后加上第一个图片
        viewlist.add(li.inflate(R.layout.page_one, null, false));
        viewlist.get(5).setBackgroundResource(imagesrc[0]);
    }

    protected void initData() {
        //初始化美食推送列表数据源
        mData = new LinkedList<Shop>();
        mData.add(new Shop("重庆火锅", 80.0, 96.0, R.mipmap.ic_launcher));
        mData.add(new Shop("酸菜鱼", 70.0, 94.0, R.mipmap.ic_launcher));
        mData.add(new Shop("辣子鸡", 80.0, 90.0, R.mipmap.ic_launcher));
        mData.add(new Shop("东北麻辣烫", 40.0, 89.0, R.mipmap.ic_launcher));


        //首页自动定位代码块
        {
            String str = "小堕落街";
            Location.setText(str);
        }
    }

    //为UI控件设置监听器
    protected void setListener() {
        tv_1.setOnClickListener(this);
        location.setOnClickListener(this);//定位框设置监听器
        food_search.setOnClickListener(this);//搜索框设置监听器
        viewpager.setAdapter(new mPagerAdapter());
        viewpager.addOnPageChangeListener(this);
    }

    /**
     * 动态添加商家列表至首页
     *
     * @param mData
     */
    private void addData(final LinkedList<Shop> mData) {
        //轮播图实现

        //商家推送列表实现
        for (int i = 0; i < mData.size(); i++) {
            final View view = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.food_list_item, null);

            //从布局文件中通过ID获取UI控件
            TextView foodname = (TextView) view.findViewById(R.id.food_list_item_foodname_tv);
            TextView foodprice = (TextView) view.findViewById(R.id.food_list_item_foodprice_tv);
            TextView foodgrade = (TextView) view.findViewById(R.id.food_list_item_like_percent_tv);
            ImageView foodimage = (ImageView) view.findViewById(R.id.food_list_item_iv);
            foodname.setText(mData.get(i).getShopname());

            //从数据源获取商家数据
            foodprice.setText("¥ " + Double.toString(mData.get(i).getShopprice()));
            foodgrade.setText("好评度：" + Double.toString(mData.get(i).getShopgrade()));
            foodimage.setBackgroundResource(mData.get(i).getShopicon());

            //为每一条目设置监听器
            final String name = mData.get(i).getShopname();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), FoodContentActivity.class);
                    intent.putExtra("name", name);  //将本条目美食名称通过intent传送至美食内容页面
                    getActivity().startActivity(intent);
                }
            });
            shoplist.addView(view);
        }
    }

    //将所有的View圆点背景设为较淡颜色
    private void initAllDot() {
        for (int i = 0; i < dotlist.size(); i++) {
            dotlist.get(i).setBackgroundResource(R.drawable.dot_normal);
        }
    }

    /**
     * 重写监听器onClick()方法，当点击相应功能区后跳转至相应页面
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.homepage_frl_rl:
                intent = new Intent(getActivity(), FoodRankingListActivity.class);
                getActivity().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            case R.id.homepage_position_ll:
                intent = new Intent(getActivity(), LocationActivity.class);
                getActivity().startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            case R.id.homepage_food_search_ll:
                intent = new Intent(getActivity(), FoodSearchActivity.class);
                getActivity().startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(getActivity(),getActivity().findViewById(R.id.homepage_food_search_tv),"shareTextView").toBundle());
                break;
            default:
                break;
        }
    }

    /**
     * 重写OnPageChangeListener中的方法
     *以下3个方法为OnPageChangeListener中的抽象方法
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 当一个页面被选择的时候调用该方法
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        int x=position;
        initAllDot();
        if(position==0)
            x=dotlist.size()-1;
        else if(position==viewlist.size()-1)
            x=0;
        else x-=1;
        dotlist.get(x).setBackgroundResource(R.drawable.dot_focused);
        currentPosition = position;
    }

    /**
     * 当一个页面的状态发生变化时调用该方法
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        if(state!=ViewPager.SCROLL_STATE_IDLE) return;
        if(currentPosition==0){
            viewpager.setCurrentItem(viewlist.size()-2,false);
        }else if(currentPosition==viewlist.size()-1){
            viewpager.setCurrentItem(1,false);
        }
        releasetime=System.currentTimeMillis();
        myHandler.removeCallbacks(runnable);
        myHandler.postDelayed(runnable,4000);
    }

    /**
     * 创建一个适配器用于为ViewPager添加数据
     */
    class mPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewlist.get(position));
            return viewlist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewlist.get(position));
        }

    }
}
