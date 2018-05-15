package com.example.sfwd.shunfengwaidai.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.adapter.AddAddressAdapter;
import com.example.sfwd.shunfengwaidai.model.Address;
import com.example.sfwd.shunfengwaidai.untils.DBHelper;

import java.util.ArrayList;

public class AddAddressActivity extends AppCompatActivity {


    public ListView listView;
    public DBHelper dbHelper;
    public ArrayList<Address> strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        Initlist();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addAnItem:
                Intent intent =new Intent(this,AddAnItemActivity.class);
                startActivity(intent);
                break;
            case R.id.back_to_fragment_personal:
                Bundle bundle=new Bundle();
                bundle.putInt("key",10);
                Intent intent2 =new Intent(this,MainActivity.class);
                intent2.putExtras(bundle);
                startActivity(intent2);
                break;
        }
    }
    public void Initlist(){
        listView = findViewById(R.id.addaddress_listview);
        strings=new ArrayList<>();
        dbHelper=new DBHelper(this,1);
        deleteUselessItem();
//        deleteAll();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("addressitem",
                new String[] { "name", "phonenumber", "address" },
                null, null, null, null, null);
       //对游标 Cursor 的遍历
        while (cursor.moveToNext()) {
            Address ads=new Address();
            int nameindex=cursor.getColumnIndex("name");
            String name = cursor.getString(nameindex);
            int numberindex=cursor.getColumnIndex("phonenumber");
            String phonenumber = cursor.getString(numberindex);
            int adsindex=cursor.getColumnIndex("address");
            String address = cursor.getString(adsindex);
            ads.setName(name);
            ads.setPhonenumber(phonenumber);
            ads.setAddress(address);
            strings.add(ads);
        }
        AddAddressAdapter mBaseAdapter = new AddAddressAdapter(getApplicationContext(), strings);
        listView.setAdapter(mBaseAdapter);
        cursor.close();
        database.close();
    }

    public void deleteUselessItem(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        /*
         * 第一个参数：表名
         * 第二个参数：删除条件，比如 c_age=?或者 c_age<?
         * 第三个参数：参数，用于替换？
         * 返回值：删除成功的个数 */
        database.delete("addressitem", "name=?", new String[] { "" });
        database.close();

    }
    //删除所有地址
    public void deleteAll(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        /*
         * 第一个参数：表名
         * 第二个参数：删除条件，比如 c_age=?或者 c_age<?
         * 第三个参数：参数，用于替换？
         * 返回值：删除成功的个数 */
        database.delete("addressitem", null, null);
        database.close();

    }
}