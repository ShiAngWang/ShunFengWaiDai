package com.example.sfwd.shunfengwaidai.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.manager.UserManager;
import com.example.sfwd.shunfengwaidai.untils.Base64Tool;
import com.example.sfwd.shunfengwaidai.untils.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccountManagementActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout Password_Line;
    private TextView UserName,Password;
    private Button Modify,Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        init();
        isCliked();
        UserName.setText(UserManager.getInstance().getUser().getPhoneNumber());
        Password.setText(UserManager.getInstance().getUser().getPassWord());
    }
    private void init(){
        Password_Line = (LinearLayout) findViewById(R.id.password_line);
        UserName = (TextView) findViewById(R.id.username);
        Password = (TextView) findViewById(R.id.password);
        Back = (Button) findViewById(R.id.back);
        Modify = (Button)findViewById(R.id.modify);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.password_line:
                GetPassword();
                isCliked();
            case R.id.modify:
                RequestParams params = new RequestParams();
                params.put("phoneNumber", UserManager.getInstance().getUser().getPhoneNumber());
                params.put("userName", UserManager.getInstance().getUser().getUserName());
                params.put("password", Password.getText().toString());
                params.put("headImage", UserManager.getInstance().getUser().getHeadImage());
                params.put("realname", UserManager.getInstance().getUser().getRealname());
                params.put("ID", UserManager.getInstance().getUser().getID());
                MyRestClient.post("/userService/updateUser", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        UserManager.getInstance().getUser().setPassWord(Password.getText().toString());
                        // If the response is JSONObject instead of expected JSONArray
                        Toast.makeText(AccountManagementActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONArray timeline) {

                        try {
                            JSONObject firstEvent = null;
                            for(int i=0;i<timeline.length();i++) {
                                firstEvent = (JSONObject) timeline.get(i);
                                Map<String, Object> map=new HashMap<String, Object>();
                                Toast.makeText(AccountManagementActivity.this, firstEvent.getString("userName"), Toast.LENGTH_SHORT).show();
                            }
                            // Do something with the response

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                finish();
                break;
            case R.id.back:
                finish();
        }
    }
    //判定按钮可否点击，若有项为空，则不能点击
    public void isCliked(){
        if(TextUtils.isEmpty(Password.getText())){
            Modify.setEnabled(false);
            Modify.setBackgroundColor(Color.parseColor("#DEDEDE"));
            Modify.setTextColor(ContextCompat.getColor(AccountManagementActivity.this, R.color.newgray));
            Toast.makeText(AccountManagementActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }
        else{
            Modify.setEnabled(true);
            Modify.setBackgroundColor(Color.parseColor("#3399FF"));
            Modify.setTextColor(ContextCompat.getColor(AccountManagementActivity.this, R.color.white));
        }
    }
    public void GetPassword(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AccountManagementActivity.this);
        View view = View.inflate(AccountManagementActivity.this, R.layout.information_alterdialog, null);
        builder.setView(view);
        builder.setCancelable(true);

        final TextView title = (TextView) view.findViewById(R.id.title);
        final EditText value = (EditText) view.findViewById(R.id.values);
        final Button button= (Button) view.findViewById(R.id.ok);
        final Button button1= (Button) view.findViewById(R.id.cancel);
        title.setText("请输入您想要修改的密码");

        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Password.setText(value.getText().toString());
                dialog.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
