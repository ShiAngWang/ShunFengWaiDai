package com.example.sfwd.shunfengwaidai.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sfwd.shunfengwaidai.R;

public class NameActivity extends AppCompatActivity {

    private EditText name;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        name = (EditText) findViewById(R.id.name);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", name.getText().toString());
                intent.setClass(NameActivity.this, BasicInformationActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
