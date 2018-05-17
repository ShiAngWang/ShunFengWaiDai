package com.example.sfwd.shunfengwaidai.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;
import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.activity.AddAddressActivity;
import com.example.sfwd.shunfengwaidai.model.Address;
import com.example.sfwd.shunfengwaidai.untils.DBHelper;
import java.util.ArrayList;

public class AddAddressAdapter extends RecyclerView.Adapter<AddAddressAdapter.MyHolder> {
    public ArrayList<Address> getData() {
        return data;
    }

    private ArrayList<Address> data;
    private Context mContext;
    private OnItemClickListener mItemClickListener;

    public AddAddressAdapter(Context mContext, ArrayList<Address> data ) {
        super();
        this.mContext = mContext;
        this.data = data;
    }


    public interface OnItemClickListener{
        void onItemClick(MyHolder view,int Position,ArrayList<Address> i);

    }
    public void onItemClick(MyHolder view,int Position,ArrayList<Address> data){

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
        final Address bean = data.get(position);
        holder.name.setText(bean.getName());
        holder.phonenumber.setText(bean.getPhonenumber());
        holder.address.setText(bean.getAddress());
        if(mItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.checkBox.setChecked(!bean.getChecked());
                    data.get(position).setChecked( !data.get(position).getChecked());
                    notifyDataSetChanged();
                    mItemClickListener.onItemClick(holder, position,data);

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

    public ArrayList<Address> removeSelect(ArrayList<Address> strings){
        DBHelper dbHelper=new DBHelper(mContext,1);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        for(int i=0;i<strings.size();i++) {
            if (!strings.get(i).getChecked()) {
                continue;
            }
            database.delete("addressitem", "name=? and phonenumber=? and address=?",
                    new String[] { data.get(i).getAddress(),data.get(i).getPhonenumber() ,data.get(i).getAddress()});
            data.remove(i);
            notifyItemRemoved(i);
            if (i != getItemCount()) {
                notifyItemRangeChanged(i, getItemCount() - i);
            }
        }

        database.close();
        return data;

    }
     public class MyHolder extends RecyclerView.ViewHolder {
         protected TextView name;
         protected TextView phonenumber;
         protected TextView address;
         public CheckBox checkBox;
         public MyHolder(View view) {
             super(view);
             name =(TextView) view.findViewById(R.id.name);
             phonenumber =(TextView) view.findViewById(R.id.phonenumber);
             address =(TextView) view.findViewById(R.id.address);
             checkBox=view.findViewById(R.id.checkBox);
         }

    }
}

