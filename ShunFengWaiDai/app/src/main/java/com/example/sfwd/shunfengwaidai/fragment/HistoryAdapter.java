package com.example.sfwd.shunfengwaidai.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sfwd.shunfengwaidai.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 54638 on 2018/4/15.
 */

public class HistoryAdapter extends BaseAdapter {
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public HistoryAdapter(Context context, List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater= LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Tools{
        public ImageView image;
        public TextView title;
        public TextView money;
        public TextView receive;
        public TextView send;
        public TextView need;
        public ImageView image1;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryAdapter.Tools tools=null;
        if(convertView==null){
            tools=new HistoryAdapter.Tools();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.history_order_item, null);
            tools.image=(ImageView)convertView.findViewById(R.id.image);
            tools.title=(TextView)convertView.findViewById(R.id.title);
            tools.money=(TextView)convertView.findViewById(R.id.money);
            tools.receive=(TextView)convertView.findViewById(R.id.receive);
            tools.send=(TextView)convertView.findViewById(R.id.send);
            tools.need=(TextView)convertView.findViewById(R.id.need);
            tools.image1=(ImageView)convertView.findViewById(R.id.image1);
            convertView.setTag(tools);
        }else{
            tools=(HistoryAdapter.Tools)convertView.getTag();
        }
        //绑定数据
        tools.image.setBackgroundResource((Integer)data.get(position).get("image"));
        tools.title.setText((String)data.get(position).get("title"));
        tools.money.setText((String)data.get(position).get("money"));
        tools.receive.setText((String)data.get(position).get("receive"));
        tools.send.setText((String)data.get(position).get("send"));
        tools.need.setText((String)data.get(position).get("need"));
        tools.image1.setBackgroundResource((Integer)data.get(position).get("image1"));
        return convertView;
    }
}
