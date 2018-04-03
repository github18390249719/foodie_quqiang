package com.example.myapplication;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.myapplication.Fragment.Fragment_login;
import com.example.myapplication.Fragment.Fragment_register;
import android.support.v4.view.*;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.*;

public class Login_RegisterActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private Fragment_login fg_login;
    private Fragment_register fg_register;
    private ViewPager viewpager;
    private TextView login,register,back;
    private ImageView cursor;

    private int bmpwidth;
    private int offset=0;
    private int current=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        init();
        login.performClick();
    }

    private void init(){
        viewpager=(ViewPager)findViewById(R.id.login_register_content_vp);
        login=(TextView)findViewById(R.id.login_register_login_tv);
        register=(TextView)findViewById(R.id.login_register_register_tv);
        back=(TextView)findViewById(R.id.login_register_top_return_tv);
        cursor=(ImageView)findViewById(R.id.login_register_content_cursor_iv);

        bmpwidth= BitmapFactory.decodeResource(getResources(),R.mipmap.line_1).getWidth();
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenwidth=dm.widthPixels;
        offset=(screenwidth/2-bmpwidth)/2;
        Matrix matrix=new Matrix();
        matrix.postTranslate(offset,0);
        cursor.setImageMatrix(matrix);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        back.setOnClickListener(this);
        viewpager.setAdapter(new myFragmentAdapter(getSupportFragmentManager()));
        viewpager.addOnPageChangeListener(this);
    }

    private void hide(){
        login.setSelected(false);
        register.setSelected(false);
    }

    /**
     *OnPageChangeListener中需要重写的方法
     */
    @Override
    public void onClick(View view) {
        hide();
        switch (view.getId()){
            case R.id.login_register_login_tv:
                viewpager.setCurrentItem(0);
                login.setSelected(true);
                break;
            case R.id.login_register_register_tv:
                viewpager.setCurrentItem(1);
                register.setSelected(true);
                break;
            case R.id.login_register_top_return_tv:
                this.finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation=null;
        switch (position){
            case 0:
                if(current==0);
                if(current==1){
                    animation=new TranslateAnimation(2*offset+bmpwidth,0,0,0);
                }
                break;
            case 1:
                if(current==0){
                    animation=new TranslateAnimation(0,2*offset+bmpwidth,0,0);
                }
                if(current==1);
                break;
        }
        current=position;
        animation.setFillAfter(true);
        animation.setDuration(300);
        cursor.startAnimation(animation);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state==2){
            hide();
            switch (viewpager.getCurrentItem()){
                case 0:
                    login.setSelected(true);
                    break;
                case 1:
                    register.setSelected(true);
                    break;
            }
        }
    }

    //自定义一个FragmentPagerAdapter适配器
    class myFragmentAdapter extends FragmentPagerAdapter{

        public myFragmentAdapter(FragmentManager fm) {
            super(fm);
            fg_login=new Fragment_login();
            fg_register=new Fragment_register();
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            if(position==0){
                fragment=fg_login;
            }
            if(position==1){
                fragment=fg_register;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object instantiateItem(ViewGroup vg, int position) {
            return super.instantiateItem(vg, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
