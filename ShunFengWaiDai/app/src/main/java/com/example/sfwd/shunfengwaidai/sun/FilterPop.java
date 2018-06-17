package com.example.sfwd.shunfengwaidai.sun;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.sfwd.shunfengwaidai.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HASEE on 2018/4/12.
 */

public class FilterPop extends PopupWindow{
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
    public FilterPop(Context context,List<String> datas){
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.datas=datas;
        popView=inflater.inflate(R.layout.popup_list,null);

        this.setContentView(popView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        initAdapter();
        init();
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.wheat));
//        this.setBackgroundDrawable(new BitmapDrawable());
        this.setBackgroundDrawable(dw);
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
    private void initAdapter(){
//        list=new ArrayList<Map<String,String>>();
//        Map<String,String> item=new HashMap<>();
////        String[] sortNames=new String[]{"距离","时间","价钱"};
//        for (int i=0;i<datas.length;i++) {
//            item.put("sort", datas[i]);
//            list.add(item);
//        }
    }
    public void setOnItemSelectedListener(AdapterView.OnItemClickListener itemClickListener){
        if (null!=itemClickListener&&null!=contentLv)
            contentLv.setOnItemClickListener(itemClickListener);
    }

}
