package com.example.sfwd.shunfengwaidai.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.model.Address;
import com.example.sfwd.shunfengwaidai.untils.DBHelper;

public class AddAnItemActivity extends AppCompatActivity  {

    public DBHelper dbHelper;
    public Address address=new Address();
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_an_item);
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);
        textView=findViewById(R.id.saveaddress);
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int rescode=WriteList(address);
                if(    rescode==0){
                    Toast.makeText(AddAnItemActivity.this,"请填写完整",Toast.LENGTH_SHORT).show();
                }else if( rescode==1){
                    Toast.makeText(AddAnItemActivity.this,"姓名太长或号码填写不规范",Toast.LENGTH_SHORT).show();
                }else {
                    //Intent intent = new Intent(AddAnItemActivity.this, AddAddressActivity.class);
                    //startActivity(intent);
                    finish();
                }
            }
        });


    }
    public int WriteList(Address address){
        dbHelper=new DBHelper(this,1);
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String name=editText.getText().toString();
        String phonenumber=editText2.getText().toString();
        String ads=editText3.getText().toString();
        if(name.equals("")||phonenumber.equals("")||ads.equals("")){

            return 0;
        }else if(name.length()>10||phonenumber.length()>11){

            return 1;
        }else {
            values.put("name", name);
            values.put("phonenumber",phonenumber );
            values.put("address", ads);
            database.insert("addressitem", null, values);
            database.close();
            return 2;
        }

    }


}
