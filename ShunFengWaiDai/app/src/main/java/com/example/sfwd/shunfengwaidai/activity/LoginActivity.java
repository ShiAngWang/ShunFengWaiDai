package com.example.sfwd.shunfengwaidai.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.untils.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private TextView reg;
    private TextView codeLoin;
    private TextView noPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//登录按钮的响应

                RequestParams params = new RequestParams();
               params.put("id", "12345678");
                MyRestClient.get("/userService/get-by-id", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        try {
                            Toast.makeText(LoginActivity.this,response.get("id").toString(), Toast.LENGTH_SHORT).show();
                            JSONObject firstEvent = null;
                            firstEvent = response;
                            String tweetText = firstEvent.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONArray timeline) {
                        // Pull out the first event on the public timeline
                        try {
                            JSONObject firstEvent = null;
                            firstEvent = (JSONObject) timeline.get(0);
                            String tweetText = firstEvent.getString("id");
                            System.out.println(tweetText);
                            Toast.makeText(LoginActivity.this,tweetText, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
        reg=findViewById(R.id.txt_reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//"注册账号"按钮的响应

            }
        });
        noPassword=findViewById(R.id.txt_cannot);
        noPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//"忘记密码"按钮的响应

            }
        });
        codeLoin=findViewById(R.id.txt_codeLogin);
        codeLoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//"短信验证码登录"按钮的响应

            }
        });
    }
}
