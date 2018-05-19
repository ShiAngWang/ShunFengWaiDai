package com.example.sfwd.shunfengwaidai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.sfwd.shunfengwaidai.R;

/**
 * Created by 54638 on 2018/5/14.
 */


public class IdentifyActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView image1,image2,image3,image4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        init();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.image1:
                Intent intent = new Intent(IdentifyActivity.this,BasicInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.image4:
                Intent intent1 = new Intent(IdentifyActivity.this,BachlorIdentifyActivity.class);
                startActivity(intent1);
        }
    }
    public void init(){
        image1 =(ImageView) findViewById(R.id.image1);
        image2 =(ImageView) findViewById(R.id.image2);
        image3 =(ImageView) findViewById(R.id.image3);
        image4 =(ImageView) findViewById(R.id.image4);
    }
}
