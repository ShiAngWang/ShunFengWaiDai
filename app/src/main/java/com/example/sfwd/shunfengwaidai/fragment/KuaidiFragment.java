package com.example.sfwd.shunfengwaidai.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;
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
 * Created by 士昂 on 2018/4/22.
 */

public class KuaidiFragment extends Fragment{
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //view = inflater.inflate(R.layout.fragment_kuaidi, container, false);
       if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        }else {
            view = inflater.inflate(R.layout.fragment_kuaidi, container, false);
            initView();
        }
        //initView();
        return view;
    }
    public void initView(){
        edt_userName = (EditText) view.findViewById(R.id.edituname1);
        edt_phoneNumber = (EditText) view.findViewById(R.id.editPhone1);
        edt_positionTo = (EditText) view.findViewById(R.id.editPort1);
        edt_positionGet = (EditText) view.findViewById(R.id.editEmail1);
        edt_info = (EditText) view.findViewById(R.id.shortMessage);
        edt_deadLine = (EditText) view.findViewById(R.id.editTime);
        edt_reward = (EditText) view.findViewById(R.id.editMoney);
        cancel = (Button) view.findViewById(R.id.buCancel);

        //按钮的响应
        submit = (Button) view.findViewById(R.id.btn_sub);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "提交订单 ", Toast.LENGTH_SHORT).show();
                RequestParams params = new RequestParams();
                params.put("positionOfGet", edt_positionGet.getText().toString());
                params.put("positionTo", edt_positionTo.getText().toString());
                params.put("numberTail",edt_phoneNumber.getText().toString());
                params.put("expressInfo",edt_info.getText().toString());
                params.put("deadLine",edt_deadLine .getText().toString());
                params.put("reward", edt_reward.getText().toString());
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // HH ~ 24小时制
                params.put("date",sdf1.format(calendar.getTime()));
                params.put("userName", UserManager.getInstance().getLoginUserName());
                params.put("state", "notAccepted");
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
        });
    }
}
