package com.example.sfwd.shunfengwaidai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.manager.UserManager;
import com.example.sfwd.shunfengwaidai.untils.IDCard;
import com.example.sfwd.shunfengwaidai.untils.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class IdentifyActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout Name_Line;
    private LinearLayout Password_Line;
    private TextView Name,Password;
    private Button Modify,Back;
    private int Get_Name=0;
    private int Get_Password=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        init();
        Name.setText(UserManager.getInstance().getUser().getRealname());
        Password.setText(UserManager.getInstance().getUser().getID());
    }
    public void init(){
        Name_Line =(LinearLayout)findViewById(R.id.name_line);
        Password_Line =(LinearLayout)findViewById(R.id.password_line);
        Name=(TextView)findViewById(R.id.name);
        Password=(TextView)findViewById(R.id.Id_Card);
        Back=(Button)findViewById(R.id.back);
        Modify=(Button)findViewById(R.id.modify);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.name_line:
                GetValues(Get_Name);
                break;
            case R.id.password_line:
                GetValues(Get_Password);
                break;
            case R.id.modify:
                RequestParams params = new RequestParams();
                params.put("phoneNumber", UserManager.getInstance().getUser().getPhoneNumber());
                params.put("userName", UserManager.getInstance().getUser().getUserName());
                params.put("password", UserManager.getInstance().getUser().getPassWord());
                params.put("headImage", UserManager.getInstance().getUser().getHeadImage());
                params.put("realname", Name.getText().toString());
                params.put("ID", Password.getText().toString());
                MyRestClient.post("/userService/updateUser", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        UserManager.getInstance().getUser().setRealname(Name.getText().toString());
                        UserManager.getInstance().getUser().setPassWord(Password.getText().toString());
                        // If the response is JSONObject instead of expected JSONArray
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONArray timeline) {

                        try {
                            JSONObject firstEvent = null;
                            for(int i=0;i<timeline.length();i++) {
                                firstEvent = (JSONObject) timeline.get(i);
                                Map<String, Object> map=new HashMap<String, Object>();
                                Toast.makeText(IdentifyActivity.this, firstEvent.getString("userName"), Toast.LENGTH_SHORT).show();
                            }
                            // Do something with the response

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
                isTrue();
                break;
            case R.id.back:
                finish();
        }
    }
    public void GetValues(final int values){
        AlertDialog.Builder builder = new AlertDialog.Builder(IdentifyActivity.this);
        View view = View.inflate(IdentifyActivity.this, R.layout.information_alterdialog, null);
        builder.setView(view);
        builder.setCancelable(true);

        final TextView title = (TextView) view.findViewById(R.id.title);
        final EditText value = (EditText) view.findViewById(R.id.values);
        final Button button= (Button) view.findViewById(R.id.ok);
        final Button button1= (Button) view.findViewById(R.id.cancel);
        //判断是要获取哪个值
        switch(values){
            case 0:
                title.setText("请输入真实姓名");
                break;
            case 1:
                title.setText("请输入身份证号");
                break;
        }
        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(values){
                    case 0:
                        Name.setText(value.getText().toString());
                        break;
                    case 1:
                        Password.setText(value.getText().toString());
                        break;
                }
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
    public void isTrue(){
        try {
            if(IDCard.IDCardValidate(Password.getText().toString())){
                Toast.makeText(IdentifyActivity.this,"验证成功",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(IdentifyActivity.this, BasicInformationActivity.class));
            }
            else {
                Toast.makeText(IdentifyActivity.this,"您输入的身份证号不正确",Toast.LENGTH_SHORT).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
