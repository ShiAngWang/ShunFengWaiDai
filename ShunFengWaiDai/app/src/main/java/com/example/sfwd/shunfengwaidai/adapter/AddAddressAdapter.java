package com.example.sfwd.shunfengwaidai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.model.Address;

import java.util.ArrayList;

public class AddAddressAdapter extends BaseAdapter {
    private ArrayList<Address> data;
    private Context mContext;
    public AddAddressAdapter(Context mContext, ArrayList<Address> data) {
        super();
        this.mContext = mContext;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewHolder holder = null;
        if(holder==null){
            convertView= inflater.inflate(R.layout.address_listview_item,null);
            holder = new ViewHolder();
            holder.address=convertView.findViewById(R.id.address);
            holder.name=convertView.findViewById(R.id.name);
            holder.phonenumber=convertView.findViewById(R.id.phonenumber);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(data.get(i).getName());
        holder.phonenumber.setText(data.get(i).getPhonenumber());
        holder.address.setText(data.get(i).getAddress());
        return convertView;
    }
    static class ViewHolder{
        TextView name;
        TextView phonenumber;
        TextView address;
    }
}
