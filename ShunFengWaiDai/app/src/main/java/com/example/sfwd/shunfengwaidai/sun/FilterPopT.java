package com.example.sfwd.shunfengwaidai.sun;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.sfwd.shunfengwaidai.R;

import java.util.ArrayList;
import java.util.List;

public class FilterPopT extends PopupWindow{
        /*
         *填充器
         */
        private LayoutInflater inflater;
        private Context context;
//    private List<String> datas=new ArrayList<>();
//
        private List<String> datas = new ArrayList<>();
        private View popView;

        private ListView contentLv;
        AdapterView.OnItemClickListener itemClickListener;
        PopAdapter adapter;
//    private List<Map<String,String>> list;
    public FilterPopT(Context context,List<String> datas){
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.datas=datas;
        popView=inflater.inflate(R.layout.popup_list,null);

        this.setContentView(popView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        init();
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.update();

    }
        private void init(){
        contentLv=(ListView)popView.findViewById(R.id.lv_pop);

//        adapter=new SimpleAdapter(context,
//                list,
//                R.layout.pop_item,
//                new String[]{"sort"},
//                new int[]{R.id.listpop_title});
        adapter = new PopAdapter(context, datas);
        contentLv.setAdapter(adapter);
    }

        public void setOnItemSelectedListener(AdapterView.OnItemClickListener itemClickListener){
        if (null!=itemClickListener&&null!=contentLv)
            contentLv.setOnItemClickListener(itemClickListener);
    }

    }
