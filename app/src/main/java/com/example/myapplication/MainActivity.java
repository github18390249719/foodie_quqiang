package com.example.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Fragment.Fragment_homepage;
import com.example.myapplication.Fragment.Fragment_mine;
import com.example.myapplication.Fragment.Fragment_perimeter;


/**
 * changed in2017.9.6
 */
public class MainActivity extends CheckPermissionsActivity implements View.OnClickListener {

    //UI Object
    protected TextView txt_homepage;
    protected TextView txt_perimeter;
    protected TextView txt_user;

    //Fragment Object
    private Fragment_homepage fg1;
    private Fragment_perimeter fg2;
    private Fragment_mine fg3;

    private FragmentManager fManager;

    //确定是否退出程序的标识变量
    private static boolean isExit = false;
//    public static boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除顶部标题栏
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition fade= TransitionInflater.from(this).inflateTransition(R.transition.fade);
        getWindow().setReenterTransition(fade);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        bindViews();
        initPictrue();
        txt_homepage.performClick();   //模拟一次点击，既进去后选择第一项
    }

    /**
     * 设置底部切换按钮图片大小
     */
    private void initPictrue(){
        Drawable[] drawable=txt_homepage.getCompoundDrawables();
        drawable[1].setBounds(0,0,40,40);
        txt_homepage.setCompoundDrawables(drawable[0],drawable[1],drawable[2],drawable[3]);
        drawable=txt_perimeter.getCompoundDrawables();
        drawable[1].setBounds(0,0,40,40);
        txt_perimeter.setCompoundDrawables(drawable[0],drawable[1],drawable[2],drawable[3]);
        drawable=txt_user.getCompoundDrawables();
        drawable[1].setBounds(0,0,40,40);
        txt_user.setCompoundDrawables(drawable[0],drawable[1],drawable[2],drawable[3]);
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_homepage = (TextView) findViewById(R.id.main_menu_tv_homepage);
        txt_perimeter = (TextView) findViewById(R.id.main_menu_tv_perimeter);
        txt_user = (TextView) findViewById(R.id.main_menu_tv_mine);

        //启动时初始化周边模
        fg2 = new Fragment_perimeter();
        fManager.beginTransaction().add(R.id.main_content_fl, fg2).commit();

        //为底部控件设置监听器
        txt_homepage.setOnClickListener(this);
        txt_perimeter.setOnClickListener(this);
        txt_user.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    protected void setSelected() {
        txt_homepage.setSelected(false);
        txt_perimeter.setSelected(false);
        txt_user.setSelected(false);
    }

    //隐藏所有Fragment
    protected void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) fragmentTransaction.hide(fg1);
        if (fg2 != null) fragmentTransaction.hide(fg2);
        if (fg3 != null) fragmentTransaction.hide(fg3);
    }

    //模拟一次点击
    public void setFragment() {
        txt_user.performClick();
    }

    //通过handler修改标识变量
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    //通过两次点击返回键后标识变量的状态确定是否退出程序
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                // 利用handler延迟发送更改状态信息，等待时间为2秒
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                finish();
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        setSelected();
        switch (v.getId()) {
            case R.id.main_menu_tv_homepage:
                txt_homepage.setSelected(true);
                if (fg1 == null) {
                    fg1 = new Fragment_homepage();
                    fTransaction.add(R.id.main_content_fl, fg1);
                } else {
                    fTransaction.show(fg1);
                }
                break;
            case R.id.main_menu_tv_perimeter:
                txt_perimeter.setSelected(true);
                fTransaction.show(fg2);
                break;
            case R.id.main_menu_tv_mine:
                txt_user.setSelected(true);
                if (fg3 == null) {
                    fg3 = new Fragment_mine();
                    fTransaction.add(R.id.main_content_fl, fg3);
                } else
                    fTransaction.show(fg3);
                break;
        }
        fTransaction.commit();
    }
}
