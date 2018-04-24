package com.example.sfwd.shunfengwaidai.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.untils.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userName_et,code_et,passWord1_et,passWord2_et;

    Boolean IfCodeTrue;


    private Button Message_btn;  //获取信息按钮
    private Button reg;           //注册按钮
    private Button back;          //返回按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        IfCodeTrue = false;

        initBomb();
        initView();
        initEvent();

    }

    private void initEvent(){
        Message_btn.setOnClickListener(this);
        reg.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void initBomb(){
        BmobSMS.initialize(RegisterActivity.this,"e8b99ee9537df3885ce7ddcc3eeeca71");
    }

    @Override
    public void onClick(View v) {
        String userName = userName_et.getText().toString();
        String inputCode = code_et.getText().toString();
        String passWord1 = passWord1_et.getText().toString();
        String passWord2 = passWord2_et.getText().toString();

        switch (v.getId()) {
            case R.id.btn_get_code:
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
                                Message_btn.setClickable(false);
                                Message_btn.setBackgroundColor(Color.GRAY);
                                Toast.makeText(RegisterActivity.this, "验证码发送成功，请尽快使用", Toast.LENGTH_SHORT).show();
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
                                        Message_btn.setText(millisUntilFinished / 1000 + "秒");
                                    }

                                    @Override
                                    public void onFinish() {
                                        Message_btn.setClickable(true);
                                        //Message_btn.setBackgroundResource(R.drawable.button_shape);
                                        Message_btn.setText("重新发送");
                                    }
                                }.start();

                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "验证码发送失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                break;
            case R.id.btn_register:
                if(IfCodeTrue==false){
                    BmobSMS.verifySmsCode(this, userName,inputCode,new VerifySMSCodeListener() {

                        public void done(BmobException ex) {
                            if (ex == null) {// 短信验证码已验证成功
                                Toast.makeText(RegisterActivity.this, "bmob验证通过", Toast.LENGTH_SHORT).show();

                                IfCodeTrue=true;
                            } else {
                                Toast.makeText(RegisterActivity.this, "bmob验证失败", Toast.LENGTH_SHORT).show();
                                System.out.println("bmob验证失败：code ="
                                        + ex.getErrorCode() + ",msg = "
                                        + ex.getLocalizedMessage());
                            }
                        }
                    });}

                if(passWord1.equals(passWord2)){
                    //向后端发送用户名密码
                    //成功提示成功返回登陆界面
                    RequestParams params = new RequestParams();
                    params.put("phoneNumber",userName);
                    params.put("password",passWord1);
                    MyRestClient.get("/userService/addUser", params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers,
                                              JSONObject response) {
                            try {
                                if(response.get("login").toString().equals("true")){
                                    Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(RegisterActivity.this,"注册失败", Toast.LENGTH_SHORT).show();
                                }
                                //Toast.makeText(LoginActivity.this,response.get("login").toString(), Toast.LENGTH_SHORT).show();
                            /*JSONObject firstEvent = null;
                            firstEvent = response;
                            String tweetText = firstEvent.getString("id");*/
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers,
                                              JSONArray timeline) {
                        }
                    });

                }
                else{
                    Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void initView() {
        userName_et = (EditText) findViewById(R.id.edt_phone);
        passWord1_et = (EditText) findViewById(R.id.edt_password);
        passWord2_et=(EditText) findViewById(R.id.editText_password);
        code_et = (EditText) findViewById(R.id.edt_code);
        Message_btn = (Button) findViewById(R.id.btn_get_code);
        reg = (Button) findViewById(R.id.btn_register);
        back = (Button) findViewById(R.id.btn_reg_back);


    }
}
