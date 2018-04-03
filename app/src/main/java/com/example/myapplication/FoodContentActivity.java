package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FoodContentActivity extends AppCompatActivity {

    private TextView food_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_content);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        food_title=(TextView)findViewById(R.id.food_title);
        food_title.setText(name);
    }
}
