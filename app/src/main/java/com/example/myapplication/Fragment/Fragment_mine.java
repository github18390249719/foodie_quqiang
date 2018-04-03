package com.example.myapplication.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Login_RegisterActivity;
import com.example.myapplication.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 蒋星 on 2017/9/7.
 */

public class Fragment_mine extends Fragment implements View.OnClickListener {

    private TextView comment, tv_2, tv_3, tv_4, tv_5, user_name;
    private LinearLayout userMessage;
    private Button btn;
    private ImageView setting;
    private SharedPreferences sharepf;
    private static boolean isLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_mine, container, false);
        linkView(view); //关联UI控件
        setListener(); //为控件设置事件监听器
        userMessage.addView(LayoutInflater.from(getActivity()).inflate(R.layout.mine_unlogin_abstract, null));
        sharepf = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        //判断是否自动登录
        if (sharepf.getBoolean("autologin", false)) {
            //设置自动登录缓冲页面
            final ProgressDialog progressdialog = new ProgressDialog(getActivity());
            progressdialog.setMessage("正在登陆");
            progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressdialog.show();
            Handler mHander = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    progressdialog.dismiss();
                    Login();
                }
            };
            mHander.sendEmptyMessageDelayed(0, 2000);//在2000毫秒后发送消息
        }
        return view;
    }

    protected void linkView(View view) {
        comment = view.findViewById(R.id.mine_my_collection_tv);
        tv_2 = view.findViewById(R.id.mine_my_comment);
        tv_3 = view.findViewById(R.id.mine_my_food_album_tv);
        tv_4 = view.findViewById(R.id.mine_my_date_tv);
        tv_5 = view.findViewById(R.id.mine_my_record);
        userMessage = view.findViewById(R.id.mine_abstract_ll);
        btn = view.findViewById(R.id.mine_exit_login_bt);
    }

    protected void setListener() {
        comment.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
        tv_5.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    /**
     * 登录功能实现代码
     */
    private void Login() {
        userMessage.removeAllViews();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.mine_login_abstract, null);
        setting=view.findViewById(R.id.mine_login_setting_iv);
        setting.setOnClickListener(this);
        user_name = view.findViewById(R.id.mine_login_nickname_tv);
        user_name.setText(sharepf.getString("user", "unkonwn"));
        userMessage.addView(view);
        isLogin = true;
    }

    /**
     * 当在登录页面成功登录返回后的处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Login();
        }
    }

    @Override
    public void onClick(View view) {
        if (isLogin) {
            switch (view.getId()) {
                case R.id.mine_my_collection_tv:     //“我的收藏”功能实现
                    Toast.makeText(getActivity().getApplicationContext(), "我的收藏", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mine_my_comment:     //“我的评论”功能实现
                    Toast.makeText(getActivity().getApplicationContext(), "我的评论", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mine_my_food_album_tv:     //“美食相册”功能实现
                    Toast.makeText(getActivity().getApplicationContext(), "美食相册", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mine_my_date_tv:     //“我的团约”按钮功能实现
                    Toast.makeText(getActivity().getApplicationContext(), "我的团约", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mine_my_record:     //“我的轨迹”按钮功能实现
                    Toast.makeText(getActivity().getApplicationContext(), "我的轨迹", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mine_exit_login_bt:           //“退出”按钮功能实现
                    new AlertDialog.Builder(getActivity()).setTitle("提示")
                            .setMessage("确认退出？")
                            //“确认退出”按钮功能实现
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SharedPreferences.Editor editer = sharepf.edit();
                                    editer.putString("user", null);
                                    editer.putString("password", null);
                                    editer.putBoolean("autologin", false);
                                    editer.commit();
                                    userMessage.removeAllViews();
                                    View view = LayoutInflater.from(getActivity()).inflate(R.layout.mine_unlogin_abstract, null);
                                    userMessage.addView(view);
                                    isLogin = false;
                                }
                            })
                            //“取消退出”按钮功能实现
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).show();
                    break;
                case R.id.mine_login_setting_iv:
                    Toast.makeText(getActivity().getApplicationContext(), "设置个人信息", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else {
            Intent intent = new Intent(getActivity(), Login_RegisterActivity.class);
            switch (view.getId()) {
                case R.id.mine_my_collection_tv:
                case R.id.mine_my_comment:
                case R.id.mine_my_food_album_tv:
                case R.id.mine_my_date_tv:
                case R.id.mine_my_record:
                case R.id.mine_abstract_ll:
                    startActivityForResult(intent, 3);
                    break;
            }
        }
    }
}
