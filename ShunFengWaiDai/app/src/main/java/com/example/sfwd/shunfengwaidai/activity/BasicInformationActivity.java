package com.example.sfwd.shunfengwaidai.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.fragment.PersonalFragment;
import com.example.sfwd.shunfengwaidai.manager.UserManager;
import com.example.sfwd.shunfengwaidai.model.User;
import com.example.sfwd.shunfengwaidai.untils.Base64Tool;
import com.example.sfwd.shunfengwaidai.untils.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.makeramen.roundedimageview.RoundedImageView;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BasicInformationActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout Name_Line;
    private LinearLayout NickName_Line;
    private LinearLayout Account_Management;
    private TextView Name,NickNaem;
    private RoundedImageView Head_Photo;
    private Button Modify,Back;
    private Uri imageUri;
    private Bitmap head_image;
    public static File tempFile;
    private int Get_Name=0;
    private int Get_NickName=1;
    public static final int PHOTO_REQUEST_CAREMA = 2;// 拍照
    public static final int CROP_PHOTO = 3;//选照片
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);
        init();
        Modify.setEnabled(false);
        if(UserManager.getInstance().getUser().getHeadImage()!=null){
            Head_Photo.setImageBitmap(Base64Tool.base64ToBitmap(UserManager.getInstance().getUser().getHeadImage()));
        }
        Name.setText(UserManager.getInstance().getUser().getRealname());
        NickNaem.setText(UserManager.getInstance().getUser().getUserName());
    }
    private void init(){
        Head_Photo = (RoundedImageView)findViewById(R.id.community_portrait);
        Name_Line = (LinearLayout)findViewById(R.id.name_line);
        NickName_Line = (LinearLayout)findViewById(R.id.nickname_line);
        Account_Management = (LinearLayout)findViewById(R.id.account_management);
        Name = (TextView)findViewById(R.id.name);
        NickNaem = (TextView)findViewById(R.id.nickname);
        Back = (Button)findViewById(R.id.back);
        Modify = (Button)findViewById(R.id.modify);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.community_portrait:
                ChoosePhoto();
                break;
            case R.id.name_line:
                GetValues(Get_Name);
                break;
            case R.id.nickname_line:
                GetValues(Get_NickName);
                break;
            case R.id.account_management:
                startActivity(new Intent(BasicInformationActivity.this,AccountManagementActivity.class));
                break;
            case R.id.modify:
                RequestParams params = new RequestParams();
                params.put("phoneNumber", UserManager.getInstance().getUser().getPhoneNumber());
                params.put("userName", NickNaem.getText().toString());
                params.put("password", UserManager.getInstance().getUser().getPassWord());
                if(head_image == null){
                    Toast.makeText(BasicInformationActivity.this,"头像为空",Toast.LENGTH_SHORT).show();
                }
                params.put("headImage", Base64Tool.bitmapToBase64(head_image));
                //Toast.makeText(BasicInformationActivity.this,Base64Tool.bitmapToBase64(head_image),Toast.LENGTH_LONG);
                params.put("realname", Name.getText().toString());
                params.put("ID", UserManager.getInstance().getUser().getID());
                MyRestClient.post("/userService/updateUser", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        UserManager.getInstance().getUser().setHeadImage(Base64Tool.bitmapToBase64(head_image));
                        UserManager.getInstance().getUser().setRealname(Name.getText().toString());
                        UserManager.getInstance().getUser().setUserName(NickNaem.getText().toString());
                        // If the response is JSONObject instead of expected JSONArray
                        Toast.makeText(BasicInformationActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONArray timeline) {

                        try {
                            JSONObject firstEvent = null;
                            for(int i=0;i<timeline.length();i++) {
                                firstEvent = (JSONObject) timeline.get(i);
                                Map<String, Object> map=new HashMap<String, Object>();
                                Toast.makeText(BasicInformationActivity.this, firstEvent.getString("userName"), Toast.LENGTH_SHORT).show();
                            }
                            // Do something with the response

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Intent i=new Intent(BasicInformationActivity.this,MainActivity.class);
                i.putExtra("id","3");
                startActivity(i);
                break;
        }
    }
    //判定按钮可否点击，若有项为空，则不能点击
    public void isCliked(){
        if(TextUtils.isEmpty(Name.getText())){
            Modify.setEnabled(false);
            Modify.setBackgroundColor(Color.parseColor("#DEDEDE"));
            Modify.setTextColor(ContextCompat.getColor(BasicInformationActivity.this, R.color.newgray));
            Toast.makeText(BasicInformationActivity.this,"姓名不能为空",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(NickNaem.getText())){
            Modify.setEnabled(false);
            Modify.setBackgroundColor(Color.parseColor("#DEDEDE"));
            Modify.setTextColor(ContextCompat.getColor(BasicInformationActivity.this, R.color.newgray));
            Toast.makeText(BasicInformationActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
        }
        else{
            Modify.setEnabled(true);
            Modify.setBackgroundColor(Color.parseColor("#3399FF"));
            Modify.setTextColor(ContextCompat.getColor(BasicInformationActivity.this, R.color.white));
        }
    }
    public void GetValues(final int values){
        AlertDialog.Builder builder = new AlertDialog.Builder(BasicInformationActivity.this);
        View view = View.inflate(BasicInformationActivity.this, R.layout.information_alterdialog, null);
        builder.setView(view);
        builder.setCancelable(true);

        final TextView title = (TextView) view.findViewById(R.id.title);
        final EditText value = (EditText) view.findViewById(R.id.values);
        final Button button= (Button) view.findViewById(R.id.ok);
        final Button button1= (Button) view.findViewById(R.id.cancel);
        //判断是要获取哪个值
        switch(values){
            case 0:
                title.setText("请输入您的真实姓名");
                break;
            case 1:
                title.setText("请输入您的昵称");
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
                        NickNaem.setText(value.getText().toString());
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

    //选择相片
    private void ChoosePhoto() {

        AlertDialog.Builder builder = new AlertDialog.Builder(BasicInformationActivity.this);
        View view = View.inflate(BasicInformationActivity.this, R.layout.alterdialog_photo, null);
        builder.setView(view);
        builder.setCancelable(true);

        final LinearLayout Photo_Shoot = (LinearLayout) view.findViewById(R.id.line1);
        final LinearLayout Photo_Pick = (LinearLayout) view.findViewById(R.id.line2);
        final Button button= (Button) view.findViewById(R.id.button);


        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
        Photo_Shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera(BasicInformationActivity.this);
                dialog.dismiss();
            }
        });
        Photo_Pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
                dialog.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_CAREMA:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        if(null!=data&&null!=data.getData()){
                            Toast.makeText(BasicInformationActivity.this,"fyjgk1",Toast.LENGTH_SHORT).show();
                            imageUri = data.getData();
                            int targetW = Head_Photo.getWidth();
                            int targetH = Head_Photo.getHeight();
                            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                            bmOptions.inJustDecodeBounds = true;
                            int PhotoW = bmOptions.outWidth;
                            int PhotoH = bmOptions.outHeight;
                            int scaleFactor = Math.min(PhotoW/targetW,PhotoH/targetH);
                            bmOptions.inJustDecodeBounds =false;
                            bmOptions.inSampleSize =scaleFactor;
                            bmOptions.inPurgeable = true;
                            Bitmap bitmap;
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri),null,bmOptions);
                            Head_Photo.setImageBitmap(bitmap);
                            if(bitmap==null){
                                Toast.makeText(BasicInformationActivity.this,"kongjjkkk",Toast.LENGTH_SHORT).show();
                            }
                            head_image = bitmap;
                        }else{
                            Toast.makeText(BasicInformationActivity.this,"fyjgk2",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                            Head_Photo.setImageBitmap(bitmap);
                            if(bitmap==null){
                                Toast.makeText(BasicInformationActivity.this,"kongjjjjj",Toast.LENGTH_SHORT).show();
                            }
                            head_image = bitmap;
                        }
                    }  catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
    //打开系统相机
    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, CROP_PHOTO);
    }
    //打开照相机拍照
    public void openCamera(Activity activity) {
        //獲取系統版本
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String filename = timeStampFormat.format(new Date());
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    filename + ".jpg");
            if (currentapiVersion < 24) {
                // 从文件中创建uri
                imageUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                //兼容android7.0 使用共享文件的形式
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
                //检查是否有存储权限，以免崩溃
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    Toast.makeText(this,"请开启存储权限",Toast.LENGTH_SHORT).show();
                    return;
                }
                imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        activity.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    /*
* 判断sdcard是否被挂载
*/
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
}

