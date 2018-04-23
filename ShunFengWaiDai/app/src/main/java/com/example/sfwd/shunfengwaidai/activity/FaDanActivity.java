package com.example.sfwd.shunfengwaidai.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.fragment.DaigouFragment;
import com.example.sfwd.shunfengwaidai.fragment.KuaidiFragment;
import com.example.sfwd.shunfengwaidai.fragment.WaimaiFragment;

public class FaDanActivity extends Activity {
    private Button button01,button02,button03;
    private Fragment fragment01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fadan);
        setview();
    }
    private void setview() {
        button01 = (Button)findViewById(R.id.btn_kuaidi);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到FragmentManager，在V4包中通过getSupportFragmentManager，
                //在系统中原生的Fragment是通过getFragmentManager获得的。
                FragmentManager FM = getFragmentManager();
                //2.开启一个事务，通过调用beginTransaction方法开启。
                FragmentTransaction MfragmentTransaction =FM.beginTransaction();
                //把自己创建好的fragment创建一个对象
                KuaidiFragment f1 = new KuaidiFragment();
                //向容器内加入Fragment，一般使用add或者replace方法实现，需要传入容器的id和Fragment的实例。
                MfragmentTransaction.replace(R.id.fadan_content,f1);
                //提交事务，调用commit方法提交。
                MfragmentTransaction.commit();
            }
        });
        button02 = (Button)findViewById(R.id.btn_waimai);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到FragmentManager，在V4包中通过getSupportFragmentManager，
                //在系统中原生的Fragment是通过getFragmentManager获得的。
                FragmentManager FMs = getFragmentManager();
                //2.开启一个事务，通过调用beginTransaction方法开启。
                FragmentTransaction MfragmentTransactions = FMs.beginTransaction();
                //把自己创建好的fragment创建一个对象
                WaimaiFragment f2 = new WaimaiFragment();
                //向容器内加入Fragment，一般使用add或者replace方法实现，需要传入容器的id和Fragment的实例。
                MfragmentTransactions.replace(R.id.fadan_content,f2);
                //提交事务，调用commit方法提交。
                MfragmentTransactions.commit();
            }
        });
        button03 = (Button)findViewById(R.id.btn_daigou);
        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到FragmentManager，在V4包中通过getSupportFragmentManager，
                //在系统中原生的Fragment是通过getFragmentManager获得的。
                FragmentManager FMs = getFragmentManager();
                //2.开启一个事务，通过调用beginTransaction方法开启。
                FragmentTransaction MfragmentTransactions = FMs.beginTransaction();
                //把自己创建好的fragment创建一个对象
                DaigouFragment f3 = new DaigouFragment();
                //向容器内加入Fragment，一般使用add或者replace方法实现，需要传入容器的id和Fragment的实例。
                MfragmentTransactions.replace(R.id.fadan_content,f3);
                //提交事务，调用commit方法提交。
                MfragmentTransactions.commit();
            }
        });
    }
}