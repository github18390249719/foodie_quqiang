package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView back,location_now;
    private String Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面切换效果
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition fade= TransitionInflater.from(this).inflateTransition(R.transition.fade);
        getWindow().setExitTransition(fade);
        getWindow().setEnterTransition(fade);
        getWindow().setReenterTransition(fade);

        setContentView(R.layout.activity_position);
        Location="后街";
        location_now=(TextView)findViewById(R.id.position_current_tv);
        back=(TextView)findViewById(R.id.position_return_tv);
        back.setOnClickListener(this);
        location_now.setText("当前位置:"+Location);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.position_return_tv:
                this.finish();
                break;
        }
    }
}
