package com.example.sfwd.shunfengwaidai.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.model.Address;
import com.example.sfwd.shunfengwaidai.untils.DBHelper;
import java.util.ArrayList;

public class AddAddressAdapter extends RecyclerView.Adapter<AddAddressAdapter.MyHolder> {
    private ArrayList<Address> data;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    public AddAddressAdapter(Context mContext, ArrayList<Address> data) {
        super();
        this.mContext = mContext;
        this.data = data;
    }


    public interface OnItemClickListener{
        void onItemClick(View view,int Position,int i);
    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.address_listview_item, parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.phonenumber.setText(data.get(position).getPhonenumber());
        holder.address.setText(data.get(position).getAddress());
        if(mItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(holder.itemView, position,getItemCount());

                }
            });
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ArrayList<Address> remove(int i){
        DBHelper dbHelper=new DBHelper(mContext,1);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.delete("addressitem", "name=? and phonenumber=? and address=?",
                new String[] { data.get(i).getAddress(),data.get(i).getPhonenumber() ,data.get(i).getAddress()});
        data.remove(i);
        database.close();
        return data;

    }
     class MyHolder extends RecyclerView.ViewHolder {
         protected TextView name;
         protected TextView phonenumber;
         protected TextView address;
         public MyHolder(View view) {
             super(view);
             name =(TextView) view.findViewById(R.id.name);
             phonenumber =(TextView) view.findViewById(R.id.phonenumber);
             address =(TextView) view.findViewById(R.id.address);
         }

    }
}
