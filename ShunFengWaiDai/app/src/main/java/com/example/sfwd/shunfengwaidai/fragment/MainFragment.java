package com.example.sfwd.shunfengwaidai.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.activity.LoginActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements OnItemClickListener {


    private ConvenientBanner convenientBanner;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private static View view;
    private int[] convenientBannerIndicator;

    private TextView login;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        }else {
            view = inflater.inflate(R.layout.fragment_main, container, false);
            InitView();
            login = (TextView) view.findViewById(R.id.btn_main_login);
            login.setOnClickListener(new View.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {
                                             //Toast.makeText(getActivity(), "i am an ImageButton in TitleFragment ! ", Toast.LENGTH_SHORT).show();
                                             Intent intent=new Intent(getActivity(), LoginActivity.class);
                                             startActivity(intent);
                                         }
                                     });
        }

        //登录按钮的响应
       /* login = (Button) view.findViewById(R.id.btn_main_login);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(getActivity(), "i am an ImageButton in TitleFragment ! ", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });*/


        return view;
    }

    private void InitView(){
        convenientBanner =(ConvenientBanner) view.findViewById(R.id.convenientBanner);
        convenientBannerIndicator = new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused};


        for (int position = 1; position < 6; position++) {
            localImages.add(getResId("ic_test_" + position, R.drawable.class));
        }
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        }, localImages)
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(2000)
                .setPageIndicator(convenientBannerIndicator)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置点击监听事件
                .setOnItemClickListener(this)
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);
    }
    private class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，不一定是Image，任何控件都可以进行翻页
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }
        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }

    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "position:" + position, Toast.LENGTH_SHORT).show();
    }



}
