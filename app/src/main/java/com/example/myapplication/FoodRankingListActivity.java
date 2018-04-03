package com.example.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Fragment.Fragment_comprehensive_ranking;
import com.example.myapplication.Fragment.Fragment_distance_ranking;
import com.example.myapplication.Fragment.Fragment_good_ranking;

public class FoodRankingListActivity extends AppCompatActivity implements View.OnClickListener {

    //Fragment Object
    private Fragment_comprehensive_ranking fg_comprehensive_ranking;//美食榜——综合排序——Fragment
    private Fragment_good_ranking fg_good_ranking;                  //美食榜——好评排序——Fragment
    private Fragment_distance_ranking fg_distance_ranking;          //美食榜——距离排序——Fragment

    private FragmentManager fManager;
    private LinearLayout frl_ranking_fl;

    //UI Object
    private TextView frl_comprehensive_ranking_tv;   //美食榜——综合排序——TextView
    private TextView frl_good_ranking_tv;            //美食榜——好评排序——TextView
    private TextView frl_distance_ranking_tv;        //美食榜——距离排序——TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_food_ranking_list);
        fManager = getFragmentManager();
        bindViews(); //初始化控件
        frl_comprehensive_ranking_tv.performClick();//模拟一次点击，既进去后选择第一项

    }

    private void bindViews() {

        frl_comprehensive_ranking_tv = (TextView) findViewById(R.id.frl_comprehensive_ranking_tv);
        frl_good_ranking_tv = (TextView) findViewById(R.id.frl_good_ranking_tv);
        frl_distance_ranking_tv = (TextView) findViewById(R.id.frl_distance_ranking_tv);
        frl_ranking_fl = (LinearLayout) findViewById(R.id.frl_ranking_fl);

        frl_comprehensive_ranking_tv.setOnClickListener(this);
        frl_good_ranking_tv.setOnClickListener(this);
        frl_distance_ranking_tv.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        frl_comprehensive_ranking_tv.setSelected(false);
        frl_good_ranking_tv.setSelected(false);
        frl_distance_ranking_tv.setSelected(false);

    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg_comprehensive_ranking != null) fragmentTransaction.hide(fg_comprehensive_ranking);
        if (fg_good_ranking != null) fragmentTransaction.hide(fg_good_ranking);
        if (fg_distance_ranking != null) fragmentTransaction.hide(fg_distance_ranking);

    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()) {
            case R.id.frl_comprehensive_ranking_tv:
                setSelected();
                frl_comprehensive_ranking_tv.setSelected(true);
                if(fg_comprehensive_ranking == null){
                    fg_comprehensive_ranking = new Fragment_comprehensive_ranking();
                    fTransaction.add(R.id.frl_ranking_fl,fg_comprehensive_ranking);
                }else{
                    fTransaction.show(fg_comprehensive_ranking);
                }
                break;
            case R.id.frl_good_ranking_tv:
                setSelected();
                frl_good_ranking_tv.setSelected(true);
                if(fg_good_ranking == null){
                    fg_good_ranking = new Fragment_good_ranking();
                    fTransaction.add(R.id.frl_ranking_fl,fg_good_ranking);
                }else{
                    fTransaction.show(fg_good_ranking);
                }
                break;
            case R.id.frl_distance_ranking_tv:
                setSelected();
                frl_distance_ranking_tv.setSelected(true);
                if(fg_distance_ranking == null){
                    fg_distance_ranking = new Fragment_distance_ranking();
                    fTransaction.add(R.id.frl_ranking_fl,fg_distance_ranking);
                }else{
                    fTransaction.show(fg_distance_ranking);
                }
                break;
        }
        fTransaction.commit();
    }
}
