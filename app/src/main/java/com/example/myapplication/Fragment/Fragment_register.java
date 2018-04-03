package com.example.myapplication.Fragment;

import android.support.v4.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

/**
 * Created by 蒋星 on 2017/9/18.
 */

public class Fragment_register extends Fragment implements View.OnClickListener{

    private EditText user,password,repassword,tele;
    private Button btn_register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fg_register, container, false);
        bindViews(view);
        btn_register.setOnClickListener(this);
        return view;
    }

    private void bindViews(View view){
        user=view.findViewById(R.id.register_username_et);
        password=view.findViewById(R.id.register_password_et);
        repassword=view.findViewById(R.id.register_password_confirm_et);
        tele=view.findViewById(R.id.register_phone_et);
        btn_register=view.findViewById(R.id.register_register_bt);
    }

    @Override
    public void onClick(View view) {
        String str1=user.getText().toString();
        String str2=password.getText().toString();
        String str3=repassword.getText().toString();
        String str4=tele.getText().toString();
        if(str1.length()<4||str1.length()>16){
            Toast.makeText(getActivity(),"用户名格式错误",Toast.LENGTH_SHORT).show();
        }else if(str2.length()<6||str2.length()>16){
            Toast.makeText(getActivity(), "密码格式错误", Toast.LENGTH_SHORT).show();
        }else if(!str2.equals(str3)){
            Toast.makeText(getActivity(), "密码不一致", Toast.LENGTH_SHORT).show();
        }
    }
}
