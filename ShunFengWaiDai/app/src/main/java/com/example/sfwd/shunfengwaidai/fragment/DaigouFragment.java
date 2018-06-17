package com.example.sfwd.shunfengwaidai.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.activity.AddAddressActivity;
import com.example.sfwd.shunfengwaidai.manager.AddressManager;
import com.example.sfwd.shunfengwaidai.manager.UserManager;
import com.example.sfwd.shunfengwaidai.untils.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by 士昂 on 2018/6/15.
 */

public class DaigouFragment extends Fragment {
    private View view;
    private Button submit;
    private EditText edt_userName;
    private EditText edt_phoneNumber;
    private EditText edt_positionTo;
    private EditText edt_positionGet;
    private EditText edt_info;
    private EditText edt_deadLine;
    private EditText edt_reward;
    private Button cancel;

    private EditText edit_Message;


    private EditText edit_Daiqu;
    private EditText edit_texttime;
    private EditText daiqu_money;

    private Button button2_daiqu;
    private Button daiqu_fadanB;

    public static boolean ifSetAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //view = inflater.inflate(R.layout.fragment_kuaidi, container, false);
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        }else {
            view = inflater.inflate(R.layout.fragment_daigou, container, false);
            initView();
        }
        //initView();
        return view;
    }
    public void initView(){
//        edt_userName = (EditText) view.findViewById(R.id.edituname1);
//        edt_phoneNumber = (EditText) view.findViewById(R.id.editPhone1);
//        edt_positionTo = (EditText) view.findViewById(R.id.editPort1);
//        edt_positionGet = (EditText) view.findViewById(R.id.editEmail1);
//        edt_info = (EditText) view.findViewById(R.id.shortMessage);
//        edt_deadLine = (EditText) view.findViewById(R.id.editTime);
//        edt_reward = (EditText) view.findViewById(R.id.editMoney);
//        cancel = (Button) view.findViewById(R.id.buCancel);

        ifSetAddress=false;

        edit_Daiqu = (EditText)view.findViewById(R.id.edit_text1);
        edit_texttime =(EditText)view.findViewById(R.id.edit_texttime2);
        daiqu_money=(EditText)view.findViewById(R.id.edit_mmoney2);
        daiqu_fadanB=(Button)view.findViewById(R.id.b_fadan3);
        button2_daiqu=(Button)view.findViewById(R.id.button2_daigou);

        //选取收货地点的响应
        button2_daiqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), AddAddressActivity.class),0);


            }
        });


        //按钮的响应
        //daiqu_fadanB = (Button) view.findViewById(R.id.daiqu_fadanB);
        daiqu_fadanB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(ifSetAddress==true&&edit_Daiqu.getText().toString()!=""&&edit_texttime.getText().toString()!=""&&daiqu_money.getText().toString()!=""){
                    Toast.makeText(getActivity(), "提交订单 ", Toast.LENGTH_SHORT).show();
                    RequestParams params = new RequestParams();
                    params.put("type", "代购");
                    params.put("userName", UserManager.getInstance().getUser().getUserName());
                    params.put("userPhone",UserManager.getInstance().getUser().getPhoneNumber());
                    params.put("orderInfo",edit_Daiqu.getText().toString());
                    params.put("positionTo", AddressManager.getInstance().getAddress().getName()+""+AddressManager.getInstance().getAddress().getAddress());
                    params.put("deadLine", edit_texttime.getText().toString());
                    params.put("reward",new Float(daiqu_money.getText().toString()).floatValue());
                    params.put("acceptPhone",AddressManager.getInstance().getAddress().getPhonenumber());
                    MyRestClient.get("/orderService/addExpress", params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers,
                                              JSONObject response) {
                            // If the response is JSONObject instead of expected JSONArray
                            Toast.makeText(getActivity(),"提交成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers,
                                              JSONArray timeline) {
                            // Pull out the first event on the public timeline


                        }
                    });

                }
                else{
                    Toast.makeText(getActivity(), "请完整填写订单信息 ", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
