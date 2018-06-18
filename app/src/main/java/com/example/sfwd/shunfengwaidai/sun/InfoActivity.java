package com.example.sfwd.shunfengwaidai.sun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sfwd.shunfengwaidai.InfoList;
import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.activity.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class InfoActivity extends AppCompatActivity {
    //@BindView(R.id.state)
    TextView state;
    //@BindView(R.id.detail)
    TextView detail;
    //@BindView(R.id.more_info)
    ListView moreInfo;
    String fname,ftime,stat,detaill,price,style,distance,fphone,addr,dtime,dname,dphone;
    private Button submit;
    List<InfoItem> data;
    String activity;
    Info info;
    int position;
    InfoList aList;
//    int position;
    public static ChangeValueCallBack changeValueCallBack;
    public interface ChangeValueCallBack {

        void changevalue(int position);

    }
//    public InfoActivity(Info info,int position){
//        this.info=info;
//        this.position=position;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //ButterKnife.bind(this);

        state=findViewById(R.id.state);
        detail=findViewById(R.id.detail);
        moreInfo=findViewById(R.id.more_info);
        submit=findViewById(R.id.submit);
        data=new ArrayList<InfoItem>();
//        info=new Info();
        aList=(InfoList)getApplication();

        initData();
        initButton();
    }
    private void initData(){
        activity=getIntent().getStringExtra("activity");
        info=(Info)getIntent().getSerializableExtra("info_data");
//        position=getIntent().getIntExtra("position",1);
//        stat=getIntent().getStringExtra("state");
//        detaill=getIntent().getStringExtra("detail");
//        fname=getIntent().getStringExtra("fname");
//        ftime=getIntent().getStringExtra("ftime");
//        price=getIntent().getStringExtra("price");
//        style=getIntent().getStringExtra("style");
//        distance=getIntent().getStringExtra("distance");
//        fphone=getIntent().getStringExtra("fphone");
//        addr=getIntent().getStringExtra("addr");
//        dtime=getIntent().getStringExtra("dtime");
//        dname=getIntent().getStringExtra("dname");
//        dphone=getIntent().getStringExtra("dphone");
        stat=info.getState();
        detaill=info.getDetail();
        fname=info.getFname();
        ftime=info.getFtime();
        price=info.getPrice();
        style=info.getStyle();
        distance=info.getDistance();
        fphone=info.getFphone();
        addr=info.getAdd();
        dtime=info.getDtime();
        dname=info.getDname();
        dphone=info.getDphone();
        state.setText("订单"+stat);
        detail.setText(detaill);
        InfoItem i1=new InfoItem("发单人",fname);
        InfoItem i2=new InfoItem("发单时间",ftime);
        InfoItem i3=new InfoItem("价钱",price);
        InfoItem i4=new InfoItem("类型",style);
        InfoItem i5=new InfoItem("距离",distance);
        InfoItem i6=new InfoItem("发单人电话",fphone);
        InfoItem i7=new InfoItem("发单人地址",addr);
        InfoItem i8=new InfoItem("截止时间",dtime);
        InfoItem i9=new InfoItem("收件人",dname);
        InfoItem i10=new InfoItem("接单人电话",dphone);
        data.add(i1);
        data.add(i2);
        data.add(i3);
        data.add(i4);
        data.add(i5);
        data.add(i6);
        data.add(i7);
        data.add(i8);
        data.add(i9);
        data.add(i10);
        InfoAdapter infoAdapter=new InfoAdapter(this,data);
        moreInfo.setAdapter(infoAdapter);
    }
    private void initButton(){
        if(activity.equals("FilterActivity")) {
//            Toast.makeText(this,"ghvjh", Toast.LENGTH_SHORT).show();
            submit.setText("接单");
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(InfoActivity.this, MainActivity.class);
//                    Bundle bundle = new Bundle();
//                    InfoItem i1 = new InfoItem("状态", stat);
//                    InfoItem i2 = new InfoItem("信息", detaill);
//                    data.add(i1);
//                    data.add(i2);
//                    info.setState("已接单");
//                    translate();
                    intent.putExtra("id","1");
//                    intent.putExtra("info", (Serializable)info);
//                    Intent i = new Intent();
//                    i.setAction("com.dsw.data");
//                    i.putExtra("position", position);
//                    sendBroadcast(i);
//                    changeValueCallBack.changevalue(position);
//                    FilterActivity.remove(position);
                    check(info,"已接单");
                    startActivity(intent);
                }
            });
        }
        else if(activity.equals("OrderlistFragment")){
            submit.setText("完成订单");
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(InfoActivity.this, MainActivity.class);
//                    InfoItem i1 = new InfoItem("状态", stat);
//                    InfoItem i2 = new InfoItem("信息", detaill);
//                    data.add(i1);
//                    data.add(i2);
//                    translate();
                    intent.putExtra("id","1");
//                    intent.putExtra("id2","0");
                    intent.putExtra("info_data", (Serializable)info);
//
                    check(info,"已完成");
                    startActivity(intent);
                }
            });
        }
        else if(activity.equals("HistoryOrderFragment")){
            submit.setText("");
            submit.setBackgroundColor(this.getColor(R.color.grey));
//            submit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(InfoActivity.this, MainActivity.class);
//                    InfoItem i1 = new InfoItem("状态", stat);
//                    InfoItem i2 = new InfoItem("信息", detaill);
//                    data.add(i1);
//                    data.add(i2);
////                    translate();
//                    intent.putExtra("id","1");
////                    intent.putExtra("id2","0");
//                    intent.putExtra("info_data", info);
//                    OrderlistFragment.remove(info);
//                    startActivity(intent);
//                }
//            });
        }

    }
//    private void translate(){
//        info.setFname(data.get(0).getInfo());
//        info.setFname(data.get(1).getInfo());
//        info.setFtime(data.get(2).getInfo());
//        info.setPrice(data.get(3).getInfo());
//        info.setStyle(data.get(4).getInfo());
//        info.setDistance(data.get(5).getInfo());
//        info.setFphone(data.get(6).getInfo());
//        info.setAdd(data.get(7).getInfo());
//        info.setDtime(data.get(8).getInfo());
//        info.setDname(data.get(9).getInfo());
//        info.setDphone(data.get(10).getInfo());
//        info.setState(data.get(11).getInfo());
//        info.setDetail(data.get(12).getInfo());
//    }

    //提供方法实例化接口
    public void setChangeValueCallBack(ChangeValueCallBack changeValueCallBack){
        this.changeValueCallBack=changeValueCallBack;
    }
    private void check(Info info,String state){
//        for(Info i:aList.getInfoArrayList()){
        ArrayList<Info> arrayList=aList.getInfoArrayList();
        for (int i=0;i<arrayList.size();i++){
            Info info1=arrayList.get(i);
            if(info1.getState().equals(info.getState())&&info1.getDetail().equals(info.getDetail())&&
                    info1.getFname().equals(info.getFname())&&  info1.getFtime().equals(info.getFtime())&& info1.getPrice().equals(info.getPrice())&&info1.getStyle().equals(info.getStyle())&&
                    info1.getDistance().equals(info.getDistance())&&info1.getFphone().equals(info.getFphone())&&info1.getAdd().equals(info.getAdd())&&info1.getDtime().equals(info.getDtime())&&
                    info1.getDname().equals(info.getDname())&&info1.getDphone().equals(info.getDphone())) {
                info1.setState(state);
            }
        }
        aList.setInfoArrayList(arrayList);
    }
}
