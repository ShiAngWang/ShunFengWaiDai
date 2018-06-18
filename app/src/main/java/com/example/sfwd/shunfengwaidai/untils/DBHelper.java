package com.example.sfwd.shunfengwaidai.untils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    //定义数据库文件名
    public static final String TABLE_NAME = "my.db";

    /**
     * @param context
     * @param name    数据库文件的名称
     * @param factory null
     * @param version 数据库文件的版本
     */
    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                               int version) {
        super(context, name, factory, version);
    }

    // 对外提供构造函数
    public DBHelper(Context context, int version) {
        //调用该类中的私有构造函数
        this(context, TABLE_NAME, null, version);
    }

    // 当第一次创建数据的时候回调方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
//创建数据库表的语句
        String sql = "create table addressitem(addressid integer primary key not null, name varchar (20), phonenumber varchar (20), address varchar(20))";
        db.execSQL(sql);
    }

    // 当数据库升级是回调该方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: oldVersion" + oldVersion + " newVersion=" + newVersion);
        String sql = "alter table t_user add c_money float";
        db.execSQL(sql);
    }

    // 当数据库被打开时回调该方法
    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.d(TAG, "onOpen");
    }
}

