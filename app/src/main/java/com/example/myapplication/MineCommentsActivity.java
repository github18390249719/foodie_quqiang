package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//我的
public class MineCommentsActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mineCommentsLV;//评论ListView

    private SimpleAdapter mineCommentsSimpleAdapter;//SimpleAdapter适配器

    //List<Map<String, Object>>   数据源，一个Map<String,Object>所组成的List集合
    private List<Map<String, Object>> mineCommentsDatalist;

    // 评论ListView  时间资源
    String[] mineCommentsTimeRes = {
            "2017年12月1日", "2017年12月2日", "2017年12月12日",
            "2017年12月13日", "2017年12月14日", "2017年12月22日"};

    // 评论ListView 美食图片资源
    int[] mineCommentsFoodiePicRes = {
            R.mipmap.u47, R.mipmap.u48, R.mipmap.u49,
            R.mipmap.u50, R.mipmap.u51, R.mipmap.u52};
    // 评论ListView  美食名称资源
    String[] mineCommentsFoodieNameRes = {
            "巧克力蛋糕", "韩式拌饭", "蜜汁烤鸭",
            "荷香鸡饭", "红烧狮子头", "特色煎饺"};
    // 评论ListView  浏览数资源
    String[] mineCommentsBrowseNumRes = {
            "浏览1", "浏览2", "浏览12",
            "浏览1", "浏览122", "浏览131"};
    // 评论ListView  商家回复资源
    String[] mineCommentsSellerReplyRes = {
            "商家回复：感谢您对本店的大力支持，下次给您8折优惠哦~",
            "商家回复：感谢您对本店的大力支持，下次给您7折优惠哦~",
            "商家回复：感谢您对本店的大力支持，下次给您6折优惠哦~",
            "商家回复：感谢您对本店的大力支持，下次给您8折优惠哦~",
            "商家回复：感谢您对本店的大力支持，下次给您9折优惠哦~",
            "商家回复：感谢您对本店的大力支持，下次给您8折优惠哦~"};

    private ImageView mineCommentsReturnIV;//返回ImageView
    private ImageView mineCommentsHeadPortraitIV;//头像ImageView
    private TextView mineCommentsNicknameTV;//昵称TextView
    private TextView mineCommentsCollectNumTV;//收藏数TextView
    private TextView mineCommentsConcernNumTV;//关注数TextView
    private TextView mineCommentsCommentsNumTV;//评论数TextView


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_comments);
        init();//初始化相关控件

         /*SimpleAdapter适配器方法
            content:上下文
            data:数据源,一个Map<String,Object>所组成的List集合
            resource：列表项的布局文件ID
            from：Map中的键名
            to：绑定数据视图中的ID，与from成对应关系
         */
        mineCommentsSimpleAdapter = new SimpleAdapter(
                this,
                getData(),
                R.layout.item_mine_comments,
                new String[]{"时间", "美食图片", "美食名称", "浏览数", "商家回复"},
                new int[]{
                        R.id.itemMineCommentsTimeTV,
                        R.id.itemMineCommentsFoodieIV,
                        R.id.itemMineCommentsFoodieTV,
                        R.id.itemMineCommentsBrowerNumTV,
                        R.id.itemMineCommentsSellerReplyTV});
        //视图加载数据源
        mineCommentsLV.setAdapter(mineCommentsSimpleAdapter);

    }

    private List<Map<String, Object>> getData() {

        mineCommentsDatalist = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < mineCommentsFoodiePicRes.length; i++) {
            //依次添加对应的数据
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("时间", mineCommentsTimeRes[i]);
            map.put("美食图片", mineCommentsFoodiePicRes[i]);
            map.put("美食名称", mineCommentsFoodieNameRes[i]);
            map.put("浏览数", mineCommentsBrowseNumRes[i]);
            map.put("商家回复", mineCommentsSellerReplyRes[i]);
            mineCommentsDatalist.add(map);
        }
        return mineCommentsDatalist;

    }

    private void init() {

        //实例化控件
        mineCommentsLV = findViewById(R.id.mineCommentsLV);
        mineCommentsReturnIV = findViewById(R.id.mineCommentsReturnIV);
        mineCommentsHeadPortraitIV = findViewById(R.id.mineCommentsHeadPortraitIV);
        mineCommentsNicknameTV = findViewById(R.id.mineCommentsNicknameTV);
        mineCommentsCollectNumTV = findViewById(R.id.mineCommentsCollectNumTV);
        mineCommentsConcernNumTV = findViewById(R.id.mineCommentsConcernNumTV);
        mineCommentsCommentsNumTV = findViewById(R.id.mineCommentsCommentsNumTV);

        //控件响应点击事件
        //mineCommentsLV.setOnClickListener(this);
        mineCommentsReturnIV.setOnClickListener(this);
        mineCommentsHeadPortraitIV.setOnClickListener(this);
        mineCommentsNicknameTV.setOnClickListener(this);
        mineCommentsCollectNumTV.setOnClickListener(this);
        mineCommentsConcernNumTV.setOnClickListener(this);
        mineCommentsCommentsNumTV.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

    }
}
