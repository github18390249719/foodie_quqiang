package com.example.myapplication.Fragment;

import android.support.v4.app.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 蒋星 on 2017/9/14.
 */

public class Fragment_login extends Fragment implements View.OnClickListener{
    private Button btn;
    private TextView forget;
    private EditText user,password;
    private CheckBox autologin;
    private SharedPreferences.Editor editor;
    private Boolean isAutologin=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_login, container, false);
        bindView(view);
        String str="<a href='http://www.baidu.com'>忘记密码?</a>";
        forget.setMovementMethod(LinkMovementMethod.getInstance());
        forget.setText(Html.fromHtml(str));
        btn.setOnClickListener(this);
        autologin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) isAutologin=true;
                else isAutologin=false;
            }
        });
        return view;
    }

    protected void bindView(View view){
        btn=view.findViewById(R.id.login_login_bt);
        forget=view.findViewById(R.id.login_forget_tv);
        user=view.findViewById(R.id.login_username_et);
        password=view.findViewById(R.id.login_password_et);
        autologin=view.findViewById(R.id.login_autologin_cb);
        editor=getActivity().getSharedPreferences("data",MODE_PRIVATE).edit();

    }


    protected void login(String str1,String str2){         //登录功能实现代码
        if(str1.equals("jiangxing")){
            if(str2.equals("123456")){
                editor.putString("user",str1);
                if(isAutologin){
                    editor.putString("password",str2);
                    editor.putBoolean("autologin",true);
                }
                editor.commit();
                Intent intent=new Intent();
                getActivity().setResult(RESULT_OK,intent);
                getActivity().finish();
            }else
                Toast.makeText(getActivity().getApplicationContext(),"密码错误",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity().getApplicationContext(), "用户不存在", Toast.LENGTH_SHORT).show();
            password.setText(null);
            user.requestFocus();
            user.selectAll();
        }
    }

    @Override
    public void onClick(View view) {
        String str1=user.getText().toString();
        String str2=password.getText().toString();
        if(str1.isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
        }else if(str2.isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
        }else
            login(str1,str2);
    }

}
