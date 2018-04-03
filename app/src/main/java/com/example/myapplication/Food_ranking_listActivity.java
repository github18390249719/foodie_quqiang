package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/3/7.
 */
//美食排行榜综合排序方法
public class Food_ranking_listActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //综合排序ListView控件
    private ListView food_ranking_list_comprehensive_ranking_lv;
    //综合排序SimpleAdapter适配器
    private SimpleAdapter food_ranking_list_comprehensive_ranking_simple_adapter;
    //综合排序List<Map<String, Object>>   数据源，一个Map<String,Object>所组成的List集合
    private List<Map<String, Object>> food_ranking_list_comprehensive_ranking_datalist;

    //综合排序 图片资源ID
    int[] food_ranking_list_comprehensive_ranking_pic_resID = {
            R.mipmap.u47, R.mipmap.u48, R.mipmap.u49,
            R.mipmap.u50, R.mipmap.u51, R.mipmap.u52};

    //综合排序 标题资源
    String[] food_ranking_list_comprehensive_ranking_title = {
            "巧克力蛋糕", "韩式拌饭", "蜜汁烤鸭",

            "荷香鸡饭", "红烧狮子头", "特色煎饺"};
    //综合排序 介绍资源
    String[] food_ranking_list_comprehensive_ranking_introduce = {
            "巧克力是女人永远的甜品，黑巧克力的微苦，白巧克力的绵甜，而巧克力蛋糕它绝对是下午茶的好搭档。",

            "韩式拌饭又称石锅拌饭，朝鲜菜，咸鲜味，营养丰富且热量不高。",

            "烤鸭历史悠久，起源于中国南北朝时期，当时《食珍录》中已记有炙鸭。",

            "荷香鸡粒饭,荤素相辅、滋补益人的食谱",

            "红烧狮子头，也称四喜丸子，是一道营养美味，口感十足，是家庭聚会的常见菜。",

            "煎饺是北方家常食品，主要食材是面粉和肉馅，制作成水饺之后用油煎制而成。"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_ranking_list_demo);
        //初始化控件和变量
        food_ranking_list_comprehensive_ranking_lv = findViewById(R.id.food_ranking_list_comprehensive_ranking_lv);

        //ListView中Item响应点击事件
        food_ranking_list_comprehensive_ranking_lv.setOnItemClickListener(this);

         /*综合排序SimpleAdapter适配器方法
            content:上下文
            data:数据源,一个Map<String,Object>所组成的List集合
            resource：列表项的布局文件ID
            from：Map中的键名
            to：绑定数据视图中的ID，与from成对应关系
            */
        food_ranking_list_comprehensive_ranking_simple_adapter = new SimpleAdapter(this, getData(),
                R.layout.item_food_ranking_list, new String[]{"图片", "标题", "点赞红心", "点赞数量", "介绍"},
                new int[]{R.id.food_ranking_list_comprehensive_ranking_pic_iv,
                        R.id.food_ranking_list_comprehensive_ranking_title_tv,
                        R.id.food_ranking_list_comprehensive_ranking_support_iv,
                        R.id.food_ranking_list_comprehensive_ranking_support_sum_tv,
                        R.id.food_ranking_list_comprehensive_ranking_introduce_tv});
        //视图加载数据源
        food_ranking_list_comprehensive_ranking_lv.setAdapter(food_ranking_list_comprehensive_ranking_simple_adapter);

    }

    //数据源方法
    private List<Map<String, Object>> getData() {

        food_ranking_list_comprehensive_ranking_datalist = new ArrayList<Map<String, Object>>();
        //重复3次Item字段的添加
        for (int i = 0; i < (food_ranking_list_comprehensive_ranking_pic_resID.length * 3); i++) {

            Map<String, Object> map = new HashMap<String, Object>();

            //依次添加对应的数据
            map.put("图片", food_ranking_list_comprehensive_ranking_pic_resID[i %
                    food_ranking_list_comprehensive_ranking_pic_resID.length]);

            map.put("标题", food_ranking_list_comprehensive_ranking_title[i %
                    food_ranking_list_comprehensive_ranking_title.length]);

            map.put("点赞红心", R.mipmap.goodicon_u291);
            map.put("点赞数量", "512");

            map.put("介绍", food_ranking_list_comprehensive_ranking_introduce[i %
                    food_ranking_list_comprehensive_ranking_introduce.length]);
            food_ranking_list_comprehensive_ranking_datalist.add(map);
        }
        return food_ranking_list_comprehensive_ranking_datalist;
    }

    //Item响应点击方法
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "点击了" +food_ranking_list_comprehensive_ranking_title[i %
                food_ranking_list_comprehensive_ranking_title.length] , Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,FooddetailsActivity.class);
        startActivity(intent);
    }
}
