package com.example.shunfengwaidai;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;



public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{


    private MainFragment mainFragment;
    private OrderFragment orderFragment;
    private MeesageFragment meesageFragment;
    private PersonalFragment personalFragment;
    private int[] convenientBannerIndicator;
    private String TAG = MainActivity.class.getSimpleName();
    private void InitBNB(){

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)

                // .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                //.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setActiveColor(R.color.white)
                .setBarBackgroundColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_1, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_3, "订单"))
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_4, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_2, "我的"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
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
                if (meesageFragment == null) {
                    meesageFragment = MeesageFragment.newInstance("消息");
                }
                transaction.replace(R.id.tv_content, meesageFragment);
                break;
            case 3:
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
        setDefaltFragment();

    }
    public void setDefaltFragment(){
        FragmentManager defalt =this .getFragmentManager();
        FragmentTransaction fragmentTransaction = defalt.beginTransaction();
        fragmentTransaction.replace(R.id.tv_content, new MainFragment());
        fragmentTransaction.commit();
    }
}
