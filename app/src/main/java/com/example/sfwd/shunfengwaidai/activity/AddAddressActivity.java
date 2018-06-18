package com.example.sfwd.shunfengwaidai.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.adapter.AddAddressAdapter;
import com.example.sfwd.shunfengwaidai.fragment.DaigouFragment;
import com.example.sfwd.shunfengwaidai.fragment.DaiquFragment;
import com.example.sfwd.shunfengwaidai.manager.AddressManager;
import com.example.sfwd.shunfengwaidai.model.Address;
import com.example.sfwd.shunfengwaidai.untils.DBHelper;
import com.example.sfwd.shunfengwaidai.untils.DividerItemDecoration;

import java.util.ArrayList;

public class AddAddressActivity extends AppCompatActivity {
    public AddAddressAdapter myadapter;
    public TextView manage;
    public Button button;
    public CheckBox checkBox;
    public Address address =new Address();
    ArrayList<Address> strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        manage = findViewById(R.id.manage);
        checkBox = findViewById(R.id.checkBox);
        button = findViewById(R.id.addAnItem);
        Initlist(initLayout());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addAnItem:
                if (button.getText().toString().equals("添加收货地址")) {
                    Intent intent = new Intent(this, AddAnItemActivity.class);
                    startActivity(intent);
                    finish();
                } else if (button.getText().toString().equals("删除")) {
                    modify(myadapter.removeSelect(strings));
                }
                break;
            case R.id.confirm:
                //Toast.makeText(AddAddressActivity.this,AddressManager.getInstance().getAddress().getAddress(),Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(this, MainActivity.class);
                //startActivity(intent);
                finish();

                break;
            case R.id.back_to_fragment_personal:
                Bundle bundle = new Bundle();
                bundle.putInt("key", 10);
                Intent intent2 = new Intent(this, MainActivity.class);
                intent2.putExtras(bundle);
                startActivity(intent2);
                break;
            case R.id.manage:
                if (manage.getText().toString().equals("管理")) {
                    manage.setText("保存");
                    button.setText("删除");
                } else if (manage.getText().toString().equals("保存")) {
                    manage.setText("管理");
                    button.setText("添加收货地址");
                }
                break;

        }
    }

    public RecyclerView initLayout() {
        RecyclerView mRecyclerView = findViewById(R.id.addaddress_listview);
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration());
        return mRecyclerView;
    }

    public void Initlist(final RecyclerView mRecyclerView) {

        final DBHelper dbHelper = new DBHelper(this, 1);
//      deleteUselessItem();
//      deleteAll();
//      设置适配器

        strings = readData(dbHelper);
        myadapter = new AddAddressAdapter(this, strings);
        mRecyclerView.setAdapter(myadapter);
        myadapter.setOnItemClickListener(new AddAddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AddAddressAdapter.MyHolder view, int position, ArrayList<Address> data) {
                Toast.makeText(AddAddressActivity.this, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
//                Intent intent =new Intent(AddAddressActivity.this,AddAnItemActivity.class);
//                Bundle bundle =new Bundle();
//                bundle.putString("name",holder.name.get);

                address.setAddress(data.get(position).getAddress());
                address.setName(data.get(position).getName());
                address.setPhonenumber(data.get(position).getPhonenumber());



                AddressManager.getInstance().setAddress(address);
                DaiquFragment.ifSetAddress=true;
                DaigouFragment.ifSetAddress=true;
            }
        });
    }

    public ArrayList<Address> readData(DBHelper dbHelper) {
        ArrayList<Address> strings = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("addressitem",
                new String[]{"name", "phonenumber", "address"},
                null, null, null, null, null);
        //对游标 Cursor 的遍历
        while (cursor.moveToNext()) {
            Address ads = new Address();
            int nameindex = cursor.getColumnIndex("name");
            String name = cursor.getString(nameindex);
            int numberindex = cursor.getColumnIndex("phonenumber");
            String phonenumber = cursor.getString(numberindex);
            int adsindex = cursor.getColumnIndex("address");
            String address = cursor.getString(adsindex);

            ads.setName(name);
            ads.setPhonenumber(phonenumber);
            ads.setAddress(address);
            ads.setChecked(false);
            strings.add(ads);
        }
        cursor.close();
        database.close();
        return strings;
    }

    public void deleteCurrentItem() {
        DBHelper dbHelper = new DBHelper(this, 1);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.delete("addressitem", "name=?", new String[]{""});
        database.close();
    }

    public void deleteUselessItem() {
        DBHelper dbHelper = new DBHelper(this, 1);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.delete("addressitem", "name=?", new String[]{""});
        database.close();
    }


    //删除所有地址
    public void deleteAll() {
        DBHelper dbHelper = new DBHelper(this, 1);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        /*
         * 第一个参数：表名
         * 第二个参数：删除条件，比如 c_age=?或者 c_age<?
         * 第三个参数：参数，用于替换？
         * 返回值：删除成功的个数 */
        database.delete("addressitem", null, null);
        database.close();

    }

    public void modify(ArrayList<Address> strings) {
        deleteAll();
        DBHelper dbHelper = new DBHelper(this, 1);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (int i = 0; i < strings.size(); i++) {
            values.put("name", strings.get(i).getName());
            values.put("phonenumber", strings.get(i).getPhonenumber());
            values.put("address", strings.get(i).getAddress());
            database.insert("addressitem", null, values);
        }
        database.close();
    }


}