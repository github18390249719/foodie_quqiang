package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class FooddetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button food_details_menu_return;    //定义美食详情界面——菜单——返回Button控件
    private Button food_details_menu_collect;   //定义美食详情界面——菜单——收藏Button控件
    private ViewFlipper food_details_content_vf;
    private int[] food_dzetails_content_vf_resId = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4};
    private float startX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        //初始化相关控件及实现监听效果功能
        init();
        //初始化相关控件方法
        //动态导入的方式为ViewFlipper加入子View
        for (int i = 0; i < food_dzetails_content_vf_resId.length; i++) {

            //把对应的图片增加到ViewFlipper容器中
            food_details_content_vf.addView(getImageView(food_dzetails_content_vf_resId[i]));
        }
        //为ViewFlipper添加动画效果
        food_details_content_vf.setInAnimation(this, R.anim.left_in);
        food_details_content_vf.setOutAnimation(this, R.anim.left_out);

        //设定ViewFlipper视图切换的时间间隔
        food_details_content_vf.setFlipInterval(5000);
        //开始播放
        food_details_content_vf.startFlipping();


    }
    private void init() {

        food_details_menu_return = (Button) findViewById(R.id.food_details_menu_return);    //初始化美食详情界面——菜单——返回Button控件
        food_details_menu_collect = (Button) findViewById(R.id.food_details_menu_collect);  //初始化美食详情界面——菜单——收藏Button控件
        food_details_content_vf = (ViewFlipper) findViewById(R.id.food_detail_content_vf);

        food_details_menu_return.setOnClickListener(this);  //实现 美食详情界面——菜单——返回Button控件 点击监听事件
        food_details_menu_collect.setOnClickListener(this); //实现 美食详情界面——菜单——收藏Button控件 点击监听事件
    }


    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //手指落下
            case MotionEvent.ACTION_DOWN: {
                startX = event.getX();
                break;
            }
            //手指滑动
            case MotionEvent.ACTION_MOVE: {
                //向右滑动看前一页
                if (event.getX() - startX > 100) {
                    food_details_content_vf.setInAnimation(this, R.anim.left_in);
                    food_details_content_vf.setOutAnimation(this, R.anim.left_out);
                    food_details_content_vf.showPrevious();//显示前一页
                }
                //向左滑动看后一页
                if (startX - event.getX() > 100) {
                    food_details_content_vf.setInAnimation(this, R.anim.left_out);
                    food_details_content_vf.setOutAnimation(this, R.anim.left_in);
                    food_details_content_vf.showNext();//显示后一页

                }
                break;
            }
            //手指离开
            case MotionEvent.ACTION_UP:

            {
                break;
            }
        }
        return super.onTouchEvent(event);
    }
    //通过对应的图片的ID得到对应的图片ImageView方法
    private ImageView getImageView(int resId) {



        ImageView image = new ImageView(this);
        image.setImageResource(resId);
        return image;
    }

    //相应点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.food_details_menu_return:
                Toast.makeText(this, "返回按钮被点击了", Toast.LENGTH_LONG).show();
                break;
            case R.id.food_details_menu_collect:
                Toast.makeText(this, "收藏按钮被点击了", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
