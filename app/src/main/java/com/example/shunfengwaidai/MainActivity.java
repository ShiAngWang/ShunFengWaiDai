package com.example.shunfengwaidai;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_1, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_3, "订单"))
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_4, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.ic_buttombar_2, "我的"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mainFragment = MainFragment.newInstance("位置");
        transaction.replace(R.id.container, mainFragment);
        transaction.commit();
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
                transaction.replace(R.id.container, mainFragment);
                break;
            case 1:
                if (orderFragment == null) {
                    orderFragment = OrderFragment.newInstance("订单");
                }
                transaction.replace(R.id.container, orderFragment);
                break;
            case 2:
                if (meesageFragment == null) {
                    meesageFragment = MeesageFragment.newInstance("消息");
                }
                transaction.replace(R.id.container, meesageFragment);
                break;
            case 3:
                if (personalFragment == null) {
                    personalFragment = PersonalFragment.newInstance("我的");
                }
                transaction.replace(R.id.container, personalFragment);
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




}
