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

public class InfoAdapter extends BaseAdapter {
    private List<InfoItem> mDatas;
    /**
     * 布局加载器
     */
    private LayoutInflater mInflater;

    public InfoAdapter(Context context, List<InfoItem> mDatas) {
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
                    R.layout.info_item, null);
            viewHolder.info_name = convertView.findViewById(R.id.info_name);
            viewHolder.info = convertView.findViewById(R.id.info);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.info_name.setText(mDatas.get(i).getName());
        viewHolder.info.setText(mDatas.get(i).getInfo());
        return convertView;
    }

    /**
     * vh
     */
    public class ViewHolder {
        /**
         * 筛选项文字tv
         */
        TextView info_name,info;
    }

}

