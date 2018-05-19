package com.example.sfwd.shunfengwaidai.activity;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;

import java.lang.reflect.Field;

public class BasicInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView Natinclity;
    private TextView Name;
    private TextView Sex;
    private TextView CardType;
    private TextView CardNumber;
    private TextView CardValidTerm;
    private TextView Work;
    private TextView Location;
    private TextView DetailAddress;

    private ImageView nameimage;
    private ImageView cardtermimage;
    private ImageView workimage;
    private ImageView locationimage;
    private ImageView detailaddressimage;
    private CheckBox checkBox1,checkBox2 ;

    private int Get_Name=0;
    private int Get_Card_Number=1;
    private int Get_Card_Vaild_Date=2;
    private int Get_Location=3;
    private int Get_Detail_Address=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);
        init();
    }
    public void init(){
        Natinclity =(TextView) findViewById(R.id.nationnailty);
        Name =(TextView) findViewById(R.id.name);
        Sex =(TextView) findViewById(R.id.sex);
        CardType =(TextView) findViewById(R.id.certificate_type);
        CardNumber =(TextView) findViewById(R.id.id_card_number);
        CardValidTerm =(TextView) findViewById(R.id.valiable_ceritificate);
        Work =(TextView) findViewById(R.id.work);
        Location =(TextView) findViewById(R.id.location);
        DetailAddress =(TextView) findViewById(R.id.detail_address);

        nameimage =(ImageView) findViewById(R.id.image1);
        cardtermimage =(ImageView) findViewById(R.id.image2);
        workimage =(ImageView) findViewById(R.id.image3);
        locationimage =(ImageView) findViewById(R.id.image4);
        detailaddressimage =(ImageView) findViewById(R.id.image5);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.nationnailty:
                ChooseNation();
                break;
            case R.id.image1:
                GetValues(Get_Name);
                break;
            case R.id.sex:
                ChooseSex();
                break;
            case R.id.certificate_type:
                ChooseCeritificate();
                break;
            case R.id.id_card_number:
                GetValues(Get_Card_Number);
                break;
            case R.id.image2:
                GetValues(Get_Card_Vaild_Date);
                break;
            case R.id.image3:
                ChooseWork();
                break;
            case R.id.image4:
                GetValues(Get_Location);
                break;
            case R.id.image5:
                GetValues(Get_Detail_Address);
                break;
            case R.id.back:
                Intent intent= new Intent(BasicInformationActivity.this,IdentifyActivity.class);
                startActivity(intent);
        }
    }

    //显示国籍（地区）对话框
    private void ChooseNation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(BasicInformationActivity.this);
        View view = View.inflate(BasicInformationActivity.this, R.layout.my_alterdialog, null);
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
                Natinclity.setText(title2.getText().toString());
            }
        });
        title3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Natinclity.setText(title3.getText().toString());
            }
        });
        title4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Natinclity.setText(title4.getText().toString());
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    //选择性别
    private void ChooseSex() {

        AlertDialog.Builder builder = new AlertDialog.Builder(BasicInformationActivity.this);
        View view = View.inflate(BasicInformationActivity.this, R.layout.alterdialog1, null);
        builder.setView(view);
        builder.setCancelable(true);

        final CheckBox checkBox1 = (CheckBox) view.findViewById(R.id.checkbox1);
        final CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.checkbox2);
        final Button button= (Button) view.findViewById(R.id.button);

        checkBox1.setChecked(false);
        checkBox2.setChecked(false);

        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
        checkBox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBox1.setChecked(true);
                if(b) {
                    Sex.setText("男");
                    dialog.dismiss();
                }

            }
        });
        checkBox2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBox2.setChecked(true);
                if(b) {
                    Sex.setText("女");
                    dialog.dismiss();
                }

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }

        });
    }
    //显示证件类型
    private void ChooseCeritificate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BasicInformationActivity.this);
        View view = View.inflate(BasicInformationActivity.this, R.layout.alterdialog_ceritificate, null);
        builder.setView(view);
        builder.setCancelable(true);

        final TextView title1= (TextView) view.findViewById(R.id.title1);
        final TextView title2= (TextView) view.findViewById(R.id.title2);
        final TextView title3= (TextView) view.findViewById(R.id.title3);
        final TextView title4= (TextView) view.findViewById(R.id.title4);
        final TextView title5= (TextView) view.findViewById(R.id.title5);
        final Button button= (Button) view.findViewById(R.id.button);

        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
        window.setGravity(Gravity.BOTTOM);//底部出现
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                CardType.setText(title1.getText().toString());
            }
        });
        title2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                CardType.setText(title2.getText().toString());
            }
        });
        title3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                CardType.setText(title3.getText().toString());
            }
        });
        title4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                CardType.setText(title4.getText().toString());
            }
        });
        title5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                CardType.setText(title5.getText().toString());
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    //显示职业类型
    private void ChooseWork()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(BasicInformationActivity.this);
        View view = View.inflate(BasicInformationActivity.this, R.layout.alterdialog_information, null);
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
                Work.setText(title2.getText().toString());
            }
        });
        title3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Work.setText(title3.getText().toString());
            }
        });
        title4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Work.setText(title4.getText().toString());
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    //显示各种输入框
    private void GetValues(final int values) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BasicInformationActivity.this);
        View view = View.inflate(BasicInformationActivity.this, R.layout.alterdialog_location, null);
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
                title.setText("请输入您的证件号码");
                break;
            case 2:
                title.setText("请输入您的证件有效期");
                break;
            case 3:
                title.setText("请输入您的所在地区");
                break;
            case 4:
                title.setText("请输入您的详细地址");
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
                        CardValidTerm.setText(location.getText().toString());
                        break;
                    case 3:
                        Location.setText(location.getText().toString());
                        break;
                    case 4:
                        DetailAddress.setText(location.getText().toString());
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


}
