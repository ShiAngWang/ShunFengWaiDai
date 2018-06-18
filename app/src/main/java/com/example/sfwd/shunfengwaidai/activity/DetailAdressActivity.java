package com.example.sfwd.shunfengwaidai.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sfwd.shunfengwaidai.R;

public class DetailAdressActivity extends AppCompatActivity {

    private EditText detail_address;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_adress);

        detail_address = (EditText) findViewById(R.id.detail_address);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String num1 = detail_address.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("detailadress", num1);
                intent.setClass(DetailAdressActivity.this, BasicInformationActivity.class);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}
