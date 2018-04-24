package com.example.sfwd.shunfengwaidai.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sfwd.shunfengwaidai.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private ViewPager myviewpager;

    //选中框的按钮
    private Button btn_order;
    private Button btn_history_order;
    //作为指示标签的按钮
    private ImageView cursor;
    //标志指示标签的横坐标
    float cursorX = 0;
    //所有按钮的宽度的数组
    private int[] widthArgs;
    //所有标题按钮的数组
    private Button[] btnArgs;


    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(String param1) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order,container,false);
        // Inflate the layout for this fragment
        initView(view);
        return view;
    }
    //初始化布局
    public void initView(View view){
        myviewpager = (ViewPager)view.findViewById(R.id.myviewpager);

        btn_order = (Button)view.findViewById(R.id.btn_first);
        btn_history_order = (Button)view.findViewById(R.id.btn_second);
        //初始化按钮数组
        btnArgs = new Button[]{btn_order,btn_history_order};
        //指示标签设置为红色
        cursor = (ImageView)view.findViewById(R.id.cursor_btn);
        cursor.setBackgroundColor(Color.RED);

        myviewpager.setOnPageChangeListener(this);
        btn_order.setOnClickListener(this);
        btn_history_order.setOnClickListener(this);
        //fragment的集合，对应每个子页面
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new OrderlistFragment());
        fragments.add(new HistoryOrderFragment());

        //新建
        MyFagmentPagerAdapter adapter = new MyFagmentPagerAdapter(getChildFragmentManager(),fragments);
        myviewpager.setAdapter(adapter);

        //先重置所有按钮颜色
        resetButtonColor();
        //再将第一个按钮字体设置为红色，表示默认选中第一个
        btn_order.setTextColor(Color.RED);

    }

    //重置所有按钮的颜色
    public void resetButtonColor(){
        btn_order.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_history_order.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_order.setTextColor(Color.BLACK);
        btn_history_order.setTextColor(Color.BLACK);
    }
    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub

        switch (view.getId()) {
            case R.id.btn_first:
                myviewpager.setCurrentItem(0);
                //cursorAnim(0);
                break;
            case R.id.btn_second:
                myviewpager.setCurrentItem(1);
                //cursorAnim(1);
                break;
        }
    }


    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        //每次滑动首先重置所有按钮的颜色
        resetButtonColor();
        //将滑动到的当前按钮颜色设置为红色
        btnArgs[arg0].setTextColor(Color.RED);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }
    //指示器的跳转，传入当前所处的页面的下标
    public void cursorAnim(int curItem){
        //每次调用，就将指示器的横坐标设置为0，即开始的位置
        cursorX = 0;
        //再根据当前的curItem来设置指示器的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)cursor.getLayoutParams();
        //减去边距*2，以对齐标题栏文字
        lp.width = widthArgs[curItem]-btnArgs[0].getPaddingLeft()*2;
        cursor.setLayoutParams(lp);
        //循环获取当前页之前的所有页面的宽度
        for(int i=0; i<curItem; i++){
            cursorX = cursorX + btnArgs[i].getWidth();
        }
        //再加上当前页面的左边距，即为指示器当前应处的位置
        cursor.setX(cursorX+btnArgs[curItem].getPaddingLeft());
    }

}
