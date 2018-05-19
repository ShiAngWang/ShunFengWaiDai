package com.example.sfwd.shunfengwaidai.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.activity.DetailActivity;
import com.example.sfwd.shunfengwaidai.adapter.OrderAdapter;
import com.example.sfwd.shunfengwaidai.manager.UserManager;
import com.example.sfwd.shunfengwaidai.untils.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 54638 on 2018/4/15.
 */

public class OrderlistFragment extends Fragment {
    private ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_list,container,false);
        listView = (ListView)view.findViewById(R.id.listview);
        RequestParams params = new RequestParams();
        params.put("userName", UserManager.getInstance().getLoginUserName());
        params.put("state", "doing");
        MyRestClient.get("/orderService/getExpress", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {

                    JSONObject firstEvent = null;
                    firstEvent = response;
                    String tweetText = firstEvent.getString("id");
                    Toast.makeText(getActivity(),tweetText, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONArray timeline) {
                // Pull out the first event on the public timeline
                try {
                    List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
                    JSONObject firstEvent = null;
                    for(int i=0;i<timeline.length();i++) {
                        firstEvent = (JSONObject) timeline.get(i);
                        Map<String, Object> map=new HashMap<String, Object>();
                        String tweetText = firstEvent.getString("id");
                        map.put("image", R.drawable.logo);
                        map.put("title","取快递");
                        map.put("money",firstEvent.getString("reward"));
                        map.put("receive",firstEvent.getString("positionOfGet"));
                        map.put("send",firstEvent.getString("positionTo"));
                        map.put("need",firstEvent.getString("expressInfo"));
                        map.put("image1",R.mipmap.turn);
                        list.add(map);
                    }
                    // Do something with the response

                    listView.setAdapter(new OrderAdapter(getActivity(), list));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                            Intent intent = new Intent();
                            intent.setClass(getActivity(), DetailActivity.class);
                            startActivity(intent);
                        }
                    });
                    Toast.makeText(getActivity(),"id", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



        return view;
    }
    /*public List<Map<String, Object>> getData(){


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
    }*/

}
