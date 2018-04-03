package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView foodSearchReturnIV;//美食搜索返回ImageView
    private TextView foodSearchInputTV;//美食搜索输入TextView
    private TextView foodSearchStartTV;//美食搜索开始TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面切换效果
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade);
        getWindow().setExitTransition(fade);
        getWindow().setEnterTransition(fade);
        getWindow().setReenterTransition(fade);

        setContentView(R.layout.activity_food_search);

        init();//初始化相关控件
    }

    private void init() {
        foodSearchReturnIV = (ImageView)findViewById(R.id.foodSearchReturnIV);
        foodSearchInputTV = (TextView)findViewById(R.id.foodSearchInputTV);
        foodSearchStartTV =(TextView) findViewById(R.id.foodSearchStartTV);

        foodSearchReturnIV.setOnClickListener(this);
        foodSearchInputTV.setOnClickListener(this);
        foodSearchStartTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())) {
            case R.id.foodSearchReturnIV:
                Toast.makeText(this, "点击了美食搜索返回ImageView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.foodSearchInputTV:
                Toast.makeText(this, "点击了美食搜索输入TextView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.foodSearchStartTV:
                Toast.makeText(this, "点击了美食搜索开始TextView", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
