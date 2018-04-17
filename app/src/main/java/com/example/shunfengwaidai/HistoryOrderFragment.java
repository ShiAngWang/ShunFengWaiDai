package com.example.shunfengwaidai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 54638 on 2018/4/15.
 */

public class HistoryOrderFragment extends Fragment {
    private ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_history_order,container,false);
        listView = (ListView)view.findViewById(R.id.listview2);
        List<Map<String, Object>> list=getData();
        listView.setAdapter(new HistoryAdapter(getActivity(), list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    public List<Map<String, Object>> getData(){
        String[] title = new String[]{"取快递", "取外卖", "代购"};
        String[] money = new String[]{"悬赏:3元", "悬赏:2元", "悬赏:15元"};
        String[] receive = new String[]{"取货地:南门近邻宝", "取货地:南门顺丰", "取货地:东门圆通"};
        String[] send = new String[]{"送货地:16号楼", "送货地:18号楼", "送货地:2号楼"};
        String[] need = new String[]{"所需物品:耳机", "所需物品:黄焖鸡米饭", "所需物品:东门贡茶的抹茶蛋糕"};
        int[] img=new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("image", img[i]);
            map.put("title",title[i]);
            map.put("money",money[i]);
            map.put("receive",receive[i]);
            map.put("send",send[i]);
            map.put("need",need[i]);
            map.put("image1",R.mipmap.turn);
            list.add(map);
        }
        return list;
    }
}
