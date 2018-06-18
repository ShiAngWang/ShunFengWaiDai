package com.example.sfwd.shunfengwaidai;
import android.app.Application;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.sun.Info;

import java.util.ArrayList;

public class InfoList extends Application{
    private ArrayList<Info> infoArrayList;



    public ArrayList<Info> getInfoArrayList() {
        return infoArrayList;
    }

    public void setInfoArrayList(ArrayList<Info> infoArrayList) {
        this.infoArrayList = infoArrayList;
    }
    @Override
    public void onCreate(){
        ArrayList<Info> infoArrayList1=new ArrayList<>();
        Info info1,info2,info3,info4;
        info1=new Info(R.drawable.logo,"sunzhaolong","2012-12-1 9:00","已完成","11件商品","$2","取外卖","1km","18812445678","北京市上苑村","2012-12-3 7:00","六三","12345678978");
        info2=new Info(R.drawable.logo,"lisiu","2012-12-5 12:00","已接单","11件商品","$3","取快递","2km","18812445678","北京市上苑村","2012-12-3 5:00","六三","12345678978");
        info3=new Info(R.drawable.logo,"sjksxkv","2012-12-4 11:00","已接单","11件商品","$1","代买","5km","18812445678","北京市上苑村","2012-12-3 6:00","六三","12345678978");
        info4=new Info(R.drawable.logo,"ckdvkn","2012-12-3 3:00","未接单","11件商品","$4","紧急","4km","18812445678","北京市上苑村","2012-12-3 11:00","六三","12345678978");
        infoArrayList1.add(info1);
        infoArrayList1.add(info2);
        infoArrayList1.add(info3);
        infoArrayList1.add(info4);
//        infoArrayList.add(info1);
//        infoArrayList.add(info2);
//        infoArrayList.add(info3);
//        infoArrayList.add(info4);
        infoArrayList=infoArrayList1;
        super.onCreate();
    }
}
