package com.example.sfwd.shunfengwaidai.sun;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
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

import com.example.sfwd.shunfengwaidai.R;
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
 * Created by HASEE on 2018/4/12.
 */

public class FilterActivity extends BaseActivity{
    private SimpleAdapter simpleAdapter1;
    private SimpleAdapter simpleAdapter2;
    private ListView listView,listView2;
    private List<String> sortList = new ArrayList<>();
    private List<String> gaodiList = new ArrayList<>();
//    private List<String> showMes1 = new ArrayList<>();
    LinearLayout sortAll;
    LinearLayout gaodiAll;
    private CheckBox cbSort;
    private CheckBox cbGaodi;
    private Button bFilter;
    DrawerLayout drawerLayout;

//    private View view;


    private List<Map<String,Object>> data2=new ArrayList<Map<String, Object>>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        listView = findViewById(R.id.listview);
//        listView2=findViewById(R.id.list);


        initData();
        initAdapter();
        initView();


    }
    private void initData(){
        sortList.add("地点");
        sortList.add("距离");
        sortList.add("价钱");
        gaodiList.add("从高到低");
        gaodiList.add("从低到高");
    }
    private void initView(){
        sortAll=findViewById(R.id.sort_tab);
        gaodiAll=findViewById(R.id.gaodi_tab);
        cbSort=findViewById(R.id.sort);
        cbGaodi=findViewById(R.id.gaodi);
        bFilter=findViewById(R.id.shaixuan);


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
                        cbSort.setText(sortList.get(position));
                    }
                }, cbGaodi,cbSort);
            }
        });
        cbGaodi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterToggle(isChecked, gaodiAll, gaodiList, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePop();
                        cbGaodi.setText(gaodiList.get(position));
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
//        setTouchListener(this);

    }

    private void initAdapter(){
        RequestParams params = new RequestParams();
        params.put("userName", UserManager.getInstance().getLoginUserName());
        params.put("state", "notAccepted");
        MyRestClient.get("/orderService/getExpress", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {

                    JSONObject firstEvent = null;
                    firstEvent = response;
                    String tweetText = firstEvent.getString("id");
                    Toast.makeText(FilterActivity.this,tweetText, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONArray timeline) {
                // Pull out the first event on the public timeline
                try {
                   List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
                    JSONObject firstEvent = null;
                    for(int i=0;i<timeline.length();i++) {
                        firstEvent = (JSONObject) timeline.get(i);
                        Map<String, Object> map=new HashMap<String, Object>();
                        String tweetText = firstEvent.getString("id");
                        map.put("picture", R.drawable.logo);
                        map.put("name",firstEvent.getString("userName"));
                        map.put("time",firstEvent.getString("date"));
                        map.put("state",firstEvent.getString("state"));
                        map.put("detail",firstEvent.getString("expressInfo"));
                        map.put("price",firstEvent.getString("reward"));
                        map.put("style","取快递");
                        map.put("receive",firstEvent.getString("positionOfGet"));
                        map.put("send",firstEvent.getString("positionTo"));
                        map.put("distance", "1km");
                        data.add(map);
                    }
                    simpleAdapter1 = new SimpleAdapter(FilterActivity.this,
                            data,
                            R.layout.item,
                            new String[]{"picture", "name", "time", "state", "detail", "price", "style", "distance"},
                            new int[]{R.id.picture, R.id.name, R.id.time,R.id.state, R.id.detail, R.id.price, R.id.style, R.id.distance}
                    );
                    listView.setAdapter(simpleAdapter1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
