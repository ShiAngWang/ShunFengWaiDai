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
import android.widget.Switch;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.fragment.MainFragment;
import com.example.sfwd.shunfengwaidai.fragment.MeesageFragment;
import com.example.sfwd.shunfengwaidai.fragment.OrderFragment;
import com.example.sfwd.shunfengwaidai.fragment.PersonalFragment;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    private MainFragment mainFragment;
    private OrderFragment orderFragment;
    private PersonalFragment personalFragment;
    private int[] convenientBannerIndicator;
    private String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationBar bottomNavigationBar;
    private CircleImageView headimage;
    private void InitBNB() {

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)

                // .setBackgroundSt0yle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                //.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setActiveColor(R.color.white)
                .setBarBackgroundColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_1, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_3, "订单"))
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_2, "我的"))
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
                if (mainFragment == null) {
                    mainFragment = MainFragment.newInstance("主页");
                }
                transaction.replace(R.id.tv_content, mainFragment);
                break;
            case 1:
                if (orderFragment == null) {
                    orderFragment = OrderFragment.newInstance("订单");
                }
                transaction.replace(R.id.tv_content, orderFragment);
                break;
            case 2:
                if (personalFragment == null) {
                    personalFragment = PersonalFragment.newInstance("我的");
                }
                transaction.replace(R.id.tv_content, personalFragment);
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
        setFragment();

    }


    public void setFragment() {
        FragmentManager defalt = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = defalt.beginTransaction();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int backcode = bundle.getInt("key");
            if (backcode == 10) {
                fragmentTransaction.replace(R.id.tv_content, PersonalFragment.newInstance("我的"));
                fragmentTransaction.commit();
            }
        } else {
            Intent intent = getIntent();
            //取出Intent中附加的数据
            int first = 0;
            if (intent.getStringExtra("id") != null) {
                first = Integer.parseInt(intent.getStringExtra("id"));
            }

            Toast.makeText(MainActivity.this, first + "", Toast.LENGTH_SHORT).show();
            switch (first) {
                case 0:
                    fragmentTransaction.replace(R.id.tv_content, new MainFragment());
                    break;
                case 1:
                    fragmentTransaction.replace(R.id.tv_content, new OrderFragment());
                    break;
                case 2:
                    fragmentTransaction.replace(R.id.tv_content, new MeesageFragment());
                    break;
                case 3:
                    fragmentTransaction.replace(R.id.tv_content, new PersonalFragment());
                    break;
                default:
                    break;
            }
            fragmentTransaction.commit();
        }

    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.head_image:
                Intent intent1 = new Intent(this, BasicInformationActivity.class);
                startActivity(intent1);
                break;
            case R.id.modify_secret:
                Intent intent3 = new Intent(this, AccountManagementActivity.class);
                startActivity(intent3);
                break;
            case R.id.switching_account:
                Intent intent2 = new Intent(this, LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.exit:


                break;


        }
    }
}
