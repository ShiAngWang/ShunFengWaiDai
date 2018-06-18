package com.example.sfwd.shunfengwaidai.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sfwd.shunfengwaidai.R;

public class CardNumberActivity extends AppCompatActivity {

    private EditText number;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_number);
        number =(EditText)findViewById(R.id.number);
        button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String num1 = number.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("cardnumber", num1);
                intent.setClass(CardNumberActivity.this, BasicInformationActivity.class);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
