package com.example.electivecompilation;

import android.content.Intent;
import android.content.Context;
import android.widget.Toast;
import android.content.BroadcastReceiver;


public class MyCustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getAction(),Toast.LENGTH_SHORT).show();
    }
}

