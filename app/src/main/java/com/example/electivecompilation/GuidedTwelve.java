package com.example.electivecompilation;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.electivecompilation.R;

public class GuidedTwelve extends AppCompatActivity {

    String packageName;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_twelve);
        packageName = getApplicationContext().getPackageName().concat(".");
        broadcastIntent();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        MyReceiver myReceiver = new MyReceiver();
        registerReceiver(myReceiver,intentFilter );
    }

    public void broadcastIntent(){
        intent = new Intent();
        intent.setAction(packageName + "MY_CUSTOM_ACTION");
        intent.setClass(this, MyCustomReceiver.class);
        sendBroadcast(intent);
    }
}
