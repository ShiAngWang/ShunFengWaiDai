package com.example.sfwd.shunfengwaidai.sun;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.InfoList;
import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.manager.UserManager;
import com.example.sfwd.shunfengwaidai.untils.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HASEE on 2018/4/12.
 */

public class FilterActivity extends BaseActivity implements FlterFragment.OnSearchListener,InfoActivity.ChangeValueCallBack{
    private SimpleAdapter simpleAdapter1;
    private  ListView listView;
    private List<String> sortList = new ArrayList<>();
    private List<String> gaodiList = new ArrayList<>();
    LinearLayout sortAll;
    LinearLayout gaodiAll;
    private CheckBox cbSort;
    private CheckBox cbGaodi;
    private Button bFilter;
    DrawerLayout drawerLayout;
     InfoList data;
    String str;
     OrderAdapter orderAdapter;
     ArrayList<Info>  list;
    ArrayList<Info>  list2;
    InfoActivity infoActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initData();
        initAdapter1();
        initView();
    }
    private void initData(){
        sortList.add("时间");
        sortList.add("距离");
        sortList.add("价钱");
        gaodiList.add("从高到低");
        gaodiList.add("从低到高");
        sortAll=findViewById(R.id.sort_tab);
        gaodiAll=findViewById(R.id.gaodi_tab);
        cbSort=findViewById(R.id.sort);
        cbGaodi=findViewById(R.id.gaodi);
        bFilter=findViewById(R.id.shaixuan);
        data=(InfoList)getApplication();
        list=data.getInfoArrayList();
        list2=new ArrayList<Info>();
        for(Info info:list){
            if(info.getState().equals("未接单")){
                list2.add(info);
            }
        }
        list=list2;
//        infoActivity=new InfoActivity();
//        infoActivity.setChangeValueCallBack(this);
    }
    private void initView(){
//        listView = findViewById(R.id.listview);



        sortAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (cbSort.isChecked())
                    cbSort.setChecked(false);
                else
                    cbSort.setChecked(true);
            }
        });
        gaodiAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbGaodi.isChecked())
                    cbGaodi.setChecked(false);
                else
                    cbGaodi.setChecked(true);
            }
        });
        cbSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterToggle(isChecked, sortAll, sortList, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePop();
                        String str1=sortList.get(position);
                        cbSort.setText(str1);
                        str=str1;
                    }
                }, cbSort,cbGaodi);
            }
        });
        cbGaodi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterToggle(isChecked, gaodiAll, gaodiList, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePop();
                        String str2=gaodiList.get(position);
                        cbGaodi.setText(str2);
                        try {
                            sort1(str, str2);

                        }catch (Exception e){

                        }
                    }
                }, cbGaodi,cbSort);
            }
        });

        bFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                drawerLayout=findViewById(R.id.draw);
                drawerLayout.openDrawer(GravityCompat.END);
                drawerLayout.setScrimColor(Color.TRANSPARENT);
            }
        });

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("com.dsw.data");
//        registerReceiver(broadcastReceiver, intentFilter);

    }

    private void initAdapter1(){
//        View view=this.getLayoutInflater().inflate(R.layout.activity_filter,null);
        listView = findViewById(R.id.listview);
        orderAdapter=new OrderAdapter(FilterActivity.this,list);
        listView.setAdapter(orderAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Info info=list.get(position);
                Intent intent=new Intent(FilterActivity.this,InfoActivity.class);
                intent.putExtra("activity","FilterActivity");
//                intent.putExtra("position",position);
//                intent.putExtra("fname",info.getFname());
//                intent.putExtra("ftime",info.getFtime());
//                intent.putExtra("state",info.getState());
//                intent.putExtra("detail",info.getDetail());
//                intent.putExtra("price",info.getPrice());
//                intent.putExtra("style",info.getStyle());
//                intent.putExtra("distance",info.getDistance());
//                intent.putExtra("fphone",info.getFphone());
//                intent.putExtra("addr",info.getAdd());
//                intent.putExtra("dtime",info.getDtime());
//                intent.putExtra("dname",info.getDname());
//                intent.putExtra("dphone",info.getDphone());
                intent.putExtra("info_data", (Serializable)info);
                startActivity(intent);
            }
        } );
    }
//    private void initAdapter(){
//        RequestParams params = new RequestParams();
//        params.put("userName", UserManager.getInstance().getLoginUserName());
//        params.put("state", "notAccepted");
//        MyRestClient.get("/orderService/getExpress", params, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers,
//                                  JSONObject response) {
//                // If the response is JSONObject instead of expected JSONArray
//                try {
//
//                    JSONObject firstEvent = null;
//                    firstEvent = response;
//                    String tweetText = firstEvent.getString("id");
//                    Toast.makeText(FilterActivity.this,tweetText, Toast.LENGTH_SHORT).show();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers,
//                                  JSONArray timeline) {
//                // Pull out the first event on the public timeline
//                try {
//                   List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
//                    JSONObject firstEvent = null;
//                    for(int i=0;i<timeline.length();i++) {
//                        firstEvent = (JSONObject) timeline.get(i);
//                        Map<String, Object> map=new HashMap<String, Object>();
//                        String tweetText = firstEvent.getString("id");
//                        map.put("picture", R.drawable.logo);
//                        map.put("name",firstEvent.getString("userName"));
//                        map.put("time",firstEvent.getString("date"));
//                        map.put("state",firstEvent.getString("state"));
//                        map.put("detail",firstEvent.getString("expressInfo"));
//                        map.put("price",firstEvent.getString("reward"));
//                        map.put("style","取快递");
//                        map.put("receive",firstEvent.getString("positionOfGet"));
//                        map.put("send",firstEvent.getString("positionTo"));
//                        map.put("distance", "1km");
//                        data.add(map);
//                    }
//                    simpleAdapter1 = new SimpleAdapter(FilterActivity.this,
//                            data,
//                            R.layout.item,
//                            new String[]{"picture", "name", "time", "state", "detail", "price", "style", "distance"},
//                            new int[]{R.id.picture, R.id.name, R.id.time,R.id.state, R.id.detail, R.id.price, R.id.style, R.id.distance}
//                    );
//                    listView.setAdapter(simpleAdapter1);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//    }
    public void sort1(String str1,String str2){

//        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        switch (str1){
            case "时间":
                switch (str2){
                    case "从高到低":
//                        Toast.makeText(FilterActivity.this, list.size()+"", Toast.LENGTH_SHORT).show();
                        for (int i=0;i<(list.size()-1);i++){
                            for(int j=0;j<(list.size()-1);j++){
//                                Toast.makeText(FilterActivity.this, "fdhskgafkh"+i, Toast.LENGTH_SHORT).show();
                              int k=j+1;
//                                Date date1=formatter.parse(list.get(j).getFtime());
//                                Date date2=formatter.parse(list.get(k).getFtime());
                                String date1=list.get(j).getFtime();
                                String date2=list.get(k).getFtime();
                                if(date1.compareTo(date2)<0) {
                                    Info info = list.get(j);
                                    list.set(j, list.get(k));
                                    list.set(k, info);
//                                    Toast.makeText(FilterActivity.this, "fdhskgafkh"+i, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;
                    case "从低到高":
                        for (int i=0;i<(list.size()-1);i++){
                            for(int j=0;j<(list.size()-1);j++){
                                int k=j+1;

//                                date1=formatter.parse(list.get(j).getFtime());
//                                date2=formatter.parse(list.get(k).getFtime());
                                String date1=list.get(j).getFtime();
                                String date2=list.get(k).getFtime();

                                if(date1.compareTo(date2)>0){
                                        Info info=list.get(j);
                                        list.set(j,list.get(k));
                                        list.set(k,info);
                                    }


                            }
                        }
                        break;
                }
                break;
            case "距离":
                switch (str2){
                    case "从高到低":
                        for (int i=0;i<(list.size()-1);i++){
                            for(int j=0;j<(list.size()-1);j++){
                                int k=j+1;
                                String date1=list.get(j).getDistance();
                                String date2=list.get(k).getDistance();

                                if(date1.compareTo(date2)<0){
                                    Info info=list.get(j);
                                    list.set(j,list.get(k));
                                    list.set(k,info);

                                }
                            }
                        }
                        break;
                    case "从低到高":
                        for (int i=0;i<(list.size()-1);i++){
                            for(int j=0;j<(list.size()-1);j++){
                                int k=j+1;
                                String date1=list.get(j).getDistance();
                                String date2=list.get(k).getDistance();
                                if(date1.compareTo(date2)>0){
                                    Info info=list.get(j);
                                    list.set(j,list.get(k));
                                    list.set(k,info);
                                }
                            }
                        }
                        break;
                }
                break;
            case "价钱":
                switch (str2){
                    case "从高到低":
                        for (int i=0;i<(list.size()-1);i++){
                            for(int j=0;j<(list.size()-1);j++){
                                int k=j+1;
                                String date1=list.get(j).getPrice();
                                String date2=list.get(k).getPrice();
                                if(date1.compareTo(date2)<0){
                                    Info info=list.get(j);
                                    list.set(j,list.get(k));
                                    list.set(k,info);
                                }
                            }
                        }

                        break;
                    case "从低到高":
                        for (int i=0;i<(list.size()-1);i++){
                            for(int j=0;j<(list.size()-1);j++){
                                int k=j+1;
                                String date1=list.get(j).getPrice();
                                String date2=list.get(k).getPrice();
                                if(date1.compareTo(date2)>0){
                                    Info info=list.get(j);
                                    list.set(j,list.get(k));
                                    list.set(k,info);
                                }
                            }
                        }
                        break;
                }
                break;
        }
//        data.setInfoArrayList(list);
//        Toast.makeText(FilterActivity.this, "noti", Toast.LENGTH_SHORT).show();
        orderAdapter.notifyDataSetChanged();
    }


    @Override
    public void onFilterCompleted(HashMap<String, String> infomation) {
//        ArrayList<Info> list=data.getInfoArrayList();
        String style=infomation.get("style");
        String price=infomation.get("price");
        String distance=infomation.get("distance");
        String time=infomation.get("time");
        for (int i=0;i<list.size();i++){
            Info info=list.get(i);
            if((info.getStyle()==style||style==null)&&(info.getPrice()==price||price==null)&&(info.getDistance()==distance||distance==null)){

            }else {
                list.remove(i);
                i--;

            }
        }
//        data.setInfoArrayList(list);
        orderAdapter.notifyDataSetChanged();
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    @Override
    public void changevalue(int position) {
        list.remove(position);
//        data.setInfoArrayList(list);

//        orderAdapter.setmDatas(list);
        orderAdapter.notifyDataSetChanged();
//        listView.setAdapter(orderAdapter);
    }
//    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            int position = intent.getIntExtra("position",1);
//            FilterActivity.this.remove(position);
////            Toast.makeText(context, data, Toast.LENGTH_LONG).show();
//        }
//    };
}
