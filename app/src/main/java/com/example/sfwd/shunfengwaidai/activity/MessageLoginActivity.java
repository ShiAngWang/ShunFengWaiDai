package com.example.sfwd.shunfengwaidai.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.manager.UserManager;
import com.example.sfwd.shunfengwaidai.model.User;
import com.example.sfwd.shunfengwaidai.untils.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

public class MessageLoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText login_phone,login_code;

    Boolean IfCodeTrue;


    private Button btn_get_code3;  //获取信息按钮
    //private Button reg;           //注册按钮
    private Button btn_login2;           //登陆按钮
    //private Button back;          //返回按钮

    private TextView txt_userlogin;
    private TextView txt_reg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_login);
        IfCodeTrue = false;
        initBomb();
        initView();
        initEvent();

    }

    private void initEvent(){
        btn_get_code3.setOnClickListener(this);
        btn_login2.setOnClickListener(this);
        txt_userlogin.setOnClickListener(this);
        txt_reg2.setOnClickListener(this);
    }

    private void initBomb(){
        BmobSMS.initialize(MessageLoginActivity.this,"e8b99ee9537df3885ce7ddcc3eeeca71");
    }

    @Override
    public void onClick(View v) {
        String userName = login_phone.getText().toString();
        String inputCode = login_code.getText().toString();


        switch (v.getId()) {
            case R.id.btn_get_code3:
                if (userName.length() != 11) {
                    Toast.makeText(this, "请输入11位有效手机号码", Toast.LENGTH_SHORT).show();
                }
                else {
                    //进行获取验证码操作和倒计时1分钟操作
                    BmobSMS.requestSMSCode(this, userName, "短信模板", new RequestSMSCodeListener() {
                        @Override
                        public void done(Integer integer, BmobException e) {
                            if (e == null) {
                                //发送成功时，让获取验证码按钮不可点击，且为灰色
                                btn_get_code3.setClickable(false);
                                btn_get_code3.setBackgroundColor(Color.GRAY);
                                Toast.makeText(MessageLoginActivity.this, "验证码发送成功，请尽快使用", Toast.LENGTH_SHORT).show();
                                /**
                                 * 倒计时1分钟操作
                                 * 说明：
                                 * new CountDownTimer(60000, 1000),第一个参数为倒计时总时间，第二个参数为倒计时的间隔时间
                                 * 单位都为ms，其中必须要实现onTick()和onFinish()两个方法，onTick()方法为当倒计时在进行中时，
                                 * 所做的操作，它的参数millisUntilFinished为距离倒计时结束时的时间，以此题为例，总倒计时长
                                 * 为60000ms,倒计时的间隔时间为1000ms，然后59000ms、58000ms、57000ms...该方法的参数
                                 * millisUntilFinished就等于这些每秒变化的数，然后除以1000，把单位变成秒，显示在textView
                                 * 或Button上，则实现倒计时的效果，onFinish()方法为倒计时结束时要做的操作，此题可以很好的
                                 * 说明该方法的用法，最后要注意的是当new CountDownTimer(60000, 1000)之后，一定要调用start()
                                 * 方法把该倒计时操作启动起来，不调用start()方法的话，是不会进行倒计时操作的
                                 */
                                new CountDownTimer(60000, 1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        //Message_btn.setBackgroundResource(R.drawable.button_shape02);
                                        btn_get_code3.setText(millisUntilFinished / 1000 + "秒");
                                    }

                                    @Override
                                    public void onFinish() {
                                        btn_get_code3.setClickable(true);
                                        //Message_btn.setBackgroundResource(R.drawable.button_shape);
                                        btn_get_code3.setText("重新发送");
                                    }
                                }.start();

                            }
                            else {
                                Toast.makeText(MessageLoginActivity.this, "验证码发送失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                break;
            case R.id.btn_login2:
                if(IfCodeTrue==false){
                    BmobSMS.verifySmsCode(this, userName,inputCode,new VerifySMSCodeListener() {

                        public void done(BmobException ex) {
                            if (ex == null) {// 短信验证码已验证成功
                                Toast.makeText(MessageLoginActivity.this, "bmob验证通过", Toast.LENGTH_SHORT).show();

                                IfCodeTrue=true;

                                //登陆
                                //获取用户填写的信息
                                ;
                                RequestParams params = new RequestParams();
                                params.put("phoneNumber", login_phone.toString());
                                MyRestClient.get("/userService/queryUser", params, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers,
                                                          JSONObject response) {
                                        // If the response is JSONObject instead of expected JSONArray
                                        try {
                                            Intent i=new Intent(MessageLoginActivity.this,MainActivity.class);
                                            i.putExtra("id","0");
                                            User u=new User(response.get("phoneNumber").toString(),response.get("userName").toString(),response.get("passWord").toString(),response.get("headImage").toString(),response.get("realname").toString(),response.get("iD").toString(),response.get("realNameState").toString());
                                            UserManager.getInstance().setUser(u);
                                            startActivity(i);
                                            //img.setImageBitmap(Base64Tool.base64ToBitmap(response.get("headImage").toString()));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(MessageLoginActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers,
                                                          JSONArray timeline) {

                                        try {
                                            JSONObject firstEvent = null;
                                            for(int i=0;i<timeline.length();i++) {
                                                firstEvent = (JSONObject) timeline.get(i);
                                                Map<String, Object> map=new HashMap<String, Object>();
                                                Toast.makeText(MessageLoginActivity.this, firstEvent.getString("userName"), Toast.LENGTH_SHORT).show();
                                            }
                                            // Do something with the response

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                });





                            } else {
                                Toast.makeText(MessageLoginActivity.this, "bmob验证失败", Toast.LENGTH_SHORT).show();
                                System.out.println("bmob验证失败：code ="
                                        + ex.getErrorCode() + ",msg = "
                                        + ex.getLocalizedMessage());
                            }
                        }
                    });}


                break;

            case R.id.txt_userlogin:
                Intent intent = new Intent(MessageLoginActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_reg2:
                Intent intent1 = new Intent(MessageLoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;

            default:
                break;
        }
    }

    private void initView() {
        login_phone = (EditText)findViewById(R.id.login_phone);
        login_code= (EditText)findViewById(R.id.login_code);

        btn_get_code3 = (Button)findViewById(R.id.btn_get_code3);  //获取信息按钮

        btn_login2=(Button)findViewById(R.id.btn_login2);;           //登陆按钮

        txt_userlogin=(TextView)findViewById(R.id.txt_userlogin);
        txt_reg2=(TextView)findViewById(R.id.txt_reg2);


    }
}
