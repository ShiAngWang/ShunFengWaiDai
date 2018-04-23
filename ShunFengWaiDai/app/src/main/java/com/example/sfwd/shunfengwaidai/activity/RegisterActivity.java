package com.example.sfwd.shunfengwaidai.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.sfwd.shunfengwaidai.R;

public class RegisterActivity extends AppCompatActivity {

    private Button code;
    private Button reg;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg=findViewById(R.id.btn_register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//"注册"按钮的响应

            }
        });

        code=findViewById(R.id.btn_get_code);
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//"获取验证码"按钮的响应

            }
        });

        code=findViewById(R.id.btn_reg_back);
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//"返回"按钮的响应

            }
        });
    }
}
