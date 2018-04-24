package com.example.sfwd.shunfengwaidai.sun;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MenuAdapter extends BaseAdapter{

    private Context mContext;
    private FilterInfo mFilterInfo;

//    private OnAdapterDataNotifiedListener mNotifiedCallback = null;

    public MenuAdapter(Context context, FilterInfo filterInfo) {
        this.mContext = context;
        this.mFilterInfo = filterInfo;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

//    public void setDataNotifyListener(OnAdapterDataNotifiedListener callback) {
//        this.mNotifiedCallback = callback;
//    }
}
