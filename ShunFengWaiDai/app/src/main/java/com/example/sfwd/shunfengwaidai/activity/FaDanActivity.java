package com.example.sfwd.shunfengwaidai.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.fragment.DaigouFragment;
import com.example.sfwd.shunfengwaidai.fragment.FindFragment;
import com.example.sfwd.shunfengwaidai.fragment.KuaidiFragment;
import com.example.sfwd.shunfengwaidai.fragment.MainFragment;
import com.example.sfwd.shunfengwaidai.fragment.MeesageFragment;
import com.example.sfwd.shunfengwaidai.fragment.OrderFragment;
import com.example.sfwd.shunfengwaidai.fragment.PersonalFragment;

import com.example.sfwd.shunfengwaidai.fragment.TestFragment;
import com.example.sfwd.shunfengwaidai.fragment.WaimaiFragment;

import junit.framework.Test;


public class FaDanActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{


    private KuaidiFragment kuaidiFragment;
    private WaimaiFragment waimaiFragment;
    private DaigouFragment daigouFragment;

   ;
    private int[] convenientBannerIndicator;
    private String TAG = FaDanActivity.class.getSimpleName();
    private void InitBNB(){

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)

                // .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                //.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setActiveColor(R.color.white)
                .setBarBackgroundColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_1, "快递"))
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_3, "外卖"))
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_4, "代购"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:

                    kuaidiFragment = new KuaidiFragment();

                transaction.replace(R.id.tv_content, kuaidiFragment);
                break;
            case 1:

                    waimaiFragment = new WaimaiFragment();

                transaction.replace(R.id.tv_content, waimaiFragment);
                break;
            case 2:

                daigouFragment = new DaigouFragment();

                transaction.replace(R.id.tv_content, daigouFragment);
                break;

            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_drawer);

        InitBNB();
        setDefaltFragment();



    }
    public void setDefaltFragment(){
        FragmentManager defalt =this .getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = defalt.beginTransaction();
        fragmentTransaction.replace(R.id.tv_content, new KuaidiFragment());
        fragmentTransaction.commit();
    }

}
