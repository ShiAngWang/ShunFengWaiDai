package com.example.sfwd.shunfengwaidai.sun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");
        Toast.makeText(context, data + "/cast", Toast.LENGTH_LONG).show();
    }
}