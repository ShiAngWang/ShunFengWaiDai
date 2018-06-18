package com.example.sfwd.shunfengwaidai.sun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sfwd.shunfengwaidai.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends BaseAdapter {

    /**
     * 筛选条件数据
     */
    private List<Info> mDatas = new ArrayList<>();
    /**
     * 布局加载器
     */
    private LayoutInflater mInflater;

    public OrderAdapter(Context context, List<Info> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.item, null);
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.picture);
            viewHolder.name =  convertView.findViewById(R.id.name);
            viewHolder.time =  convertView.findViewById(R.id.time);
            viewHolder.state =  convertView.findViewById(R.id.state);
            viewHolder.detail =  convertView.findViewById(R.id.detail);
            viewHolder.price =  convertView.findViewById(R.id.price);
            viewHolder.style =  convertView.findViewById(R.id.style);
            viewHolder.distance =  convertView.findViewById(R.id.distance);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Info info=mDatas.get(i);
        viewHolder.pic.setImageResource(info.getPicture());
        viewHolder.name.setText(info.getFname());
        viewHolder.time.setText(info.getFtime());
        viewHolder.state.setText(info.getState());
        viewHolder.detail.setText(info.getDetail());
        viewHolder.price.setText(info.getPrice());
        viewHolder.style.setText(info.getStyle());
        viewHolder.distance.setText(info.getDistance());
        return convertView;
    }
//    private void addListener(int position,ViewHolder hoder){
//        hoder.pic.setTag(position);
//        hoder.name.setTag(position);
//        hoder.time.setTag(position);
//        hoder.state.setTag(position);
//        hoder.pic.setTag(position);
//        hoder.pic.setTag(position);
//    }

    /**
     * vh
     */
    public class ViewHolder {
        ImageView pic;
        TextView name,time,state,detail,price,style,distance;
    }

}