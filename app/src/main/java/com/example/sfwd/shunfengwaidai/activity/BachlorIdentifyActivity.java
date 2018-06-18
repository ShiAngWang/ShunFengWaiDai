package com.example.sfwd.shunfengwaidai.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;

import org.w3c.dom.Text;

public class BachlorIdentifyActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView Name;
    private TextView CardNumber;
    private TextView Province;
    private TextView School;
    private TextView Canlendar;
    private TextView EnrollmentYear;

    private ImageView provinceimage;
    private ImageView schoolimage;
    private ImageView canlendarimage;
    private ImageView enrollmentimage;


    private CheckBox agree;
    private Button Identify;

    private int Get_Name=0;
    private int Get_Card_Number=1;
    private int Get_Province=2;
    private int Get_School=3;
    private int Get_Enrollment_Year=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bachlor_identify);
        //初始化控件
        init();
        //判定按钮是否可点击
        isCliked();
    }

    //初始化控件，得到控件资源
    public void init() {
        Name = (TextView)findViewById(R.id.name);
        CardNumber = (TextView)findViewById(R.id.id_card_number);
        Province = (TextView)findViewById(R.id.province);
        School = (TextView)findViewById(R.id.school);
        Canlendar = (TextView)findViewById(R.id.caneldar);
        EnrollmentYear = (TextView)findViewById(R.id.enrollment_year);

        provinceimage =(ImageView)findViewById(R.id.image1);
        schoolimage =(ImageView)findViewById(R.id.image2);
        canlendarimage =(ImageView)findViewById(R.id.image3);
        enrollmentimage =(ImageView)findViewById(R.id.image4);

        agree = (CheckBox)findViewById(R.id.checkbox);
        Identify =(Button)findViewById(R.id.identify);
    }

    //判定按钮可否点击，若有项为空，则不能点击
    public void isCliked(){
        if(TextUtils.isEmpty(Name.getText())){
            Identify.setEnabled(false);
            Identify.setBackgroundColor(Color.parseColor("#DEDEDE"));
        }
        else if(TextUtils.isEmpty(CardNumber.getText())){
            Identify.setEnabled(false);
            Identify.setBackgroundColor(Color.parseColor("#DEDEDE"));
            Identify.setTextColor(ContextCompat.getColor(BachlorIdentifyActivity.this,R.color.newgray));
        }
        else if(TextUtils.isEmpty(Province.getText())){
            Identify.setEnabled(false);
            Identify.setBackgroundColor(Color.parseColor("#DEDEDE"));
            Identify.setTextColor(ContextCompat.getColor(BachlorIdentifyActivity.this,R.color.newgray));
        }
        else if(TextUtils.isEmpty(School.getText())){
            Identify.setEnabled(false);
            Identify.setBackgroundColor(Color.parseColor("#DEDEDE"));
            Identify.setTextColor(ContextCompat.getColor(BachlorIdentifyActivity.this,R.color.newgray));
        }
        else if(TextUtils.isEmpty(Canlendar.getText())){
            Identify.setEnabled(false);
            Identify.setBackgroundColor(Color.parseColor("#DEDEDE"));
            Identify.setTextColor(ContextCompat.getColor(BachlorIdentifyActivity.this,R.color.newgray));
        }
        else if(TextUtils.isEmpty(EnrollmentYear.getText())){
            Identify.setEnabled(false);
            Identify.setBackgroundColor(Color.parseColor("#DEDEDE"));
            Identify.setTextColor(ContextCompat.getColor(BachlorIdentifyActivity.this,R.color.newgray));
        }
        else if(agree.isChecked()==false){
            Identify.setEnabled(false);
            Identify.setBackgroundColor(Color.parseColor("#DEDEDE"));
            Identify.setTextColor(ContextCompat.getColor(BachlorIdentifyActivity.this,R.color.newgray));
        }
        else{
            Identify.setEnabled(true);
            Identify.setBackgroundColor(Color.parseColor("#3399FF"));
            Identify.setTextColor(ContextCompat.getColor(BachlorIdentifyActivity.this,R.color.white));
        }
    }
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.name:
                GetValues(Get_Name);
                break;
            case R.id.id_card_number:
                GetValues(Get_Card_Number);
                break;
            case R.id.image1:
                GetValues(Get_Province);
                break;
            case R.id.image2:
                GetValues(Get_School);
                break;
            case R.id.image3:
                ShowChoise();
                break;
            case R.id.image4:
                GetValues(Get_Enrollment_Year);
                break;
            case R.id.checkbox:
                isCliked();
                break;
            case R.id.identify:
                Intent intent= new Intent(BachlorIdentifyActivity.this,IdentifyActivity.class);
                startActivity(intent);
                break;
            case R.id.back:
                Intent intent1= new Intent(BachlorIdentifyActivity.this,IdentifyActivity.class);
                startActivity(intent1);
        }
    }
    private void ShowChoise()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(BachlorIdentifyActivity.this);
        View view = View.inflate(BachlorIdentifyActivity.this, R.layout.alterdialog_education, null);
        builder.setView(view);
        builder.setCancelable(true);

        final TextView title2= (TextView) view.findViewById(R.id.title2);
        final TextView title3= (TextView) view.findViewById(R.id.title3);
        final TextView title4= (TextView) view.findViewById(R.id.title4);
        final Button button= (Button) view.findViewById(R.id.button);

        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
        window.setGravity(Gravity.BOTTOM);//底部出现
        title2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Canlendar.setText(title2.getText().toString());
                isCliked();
            }
        });
        title3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Canlendar.setText(title3.getText().toString());
                isCliked();
            }
        });
        title4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Canlendar.setText(title4.getText().toString());
                isCliked();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    //重写onRestart方法，获得
    @Override
    protected void onRestart() {
        super.onRestart();
        isCliked();
    }
    private void GetValues(final int values) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BachlorIdentifyActivity.this);
        View view = View.inflate(BachlorIdentifyActivity.this, R.layout.alterdialog_location, null);
        builder.setView(view);
        builder.setCancelable(true);

        final TextView title = (TextView) view.findViewById(R.id.title);
        final EditText location = (EditText) view.findViewById(R.id.location);
        final Button button= (Button) view.findViewById(R.id.ok);
        final Button button1= (Button) view.findViewById(R.id.cancel);
        //判断是要获取哪个值
        switch(values){
            case 0:
                title.setText("请输入您的名字");
                break;
            case 1:
                title.setText("请输入您的身份证号");
                break;
            case 2:
                title.setText("请输入您的省份");
                break;
            case 3:
                title.setText("请输入您的学校");
                break;
            case 4:
                title.setText("请输入您的入学年份");
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
                        Name.setText(location.getText().toString());
                        break;
                    case 1:
                        CardNumber.setText(location.getText().toString());
                        break;
                    case 2:
                        Province.setText(location.getText().toString());
                        break;
                    case 3:
                        School.setText(location.getText().toString());
                        break;
                    case 4:
                        EnrollmentYear.setText(location.getText().toString());
                        break;
                }
                dialog.dismiss();
                isCliked();
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


