package com.example.electivecompilation;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.content.BroadcastReceiver;

import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state;
        switch (intent.getAction()){
            case Intent.ACTION_BATTERY_CHANGED:
                state = intent.getAction();
                showNotification("Battery Info", state, context);
                break;
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                state = intent.getAction();
                showNotification("Airplane Mode", state, context);
                break;
            default:
        }
    }

    @SuppressLint("MissingPermission")
    public void showNotification(String title, String state, Context context){
        // Check if the Android version is Oreo (API 26) or higher to create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    "mnc", "mn", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        // Create a PendingIntent with FLAG_IMMUTABLE (required for Android 12 and above)
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 0, new Intent(context, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);  // Added FLAG_IMMUTABLE here

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "mnc")
                .setContentTitle(title)
                .setContentText(state)
                .setSmallIcon(R.drawable.ic_announcement)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)  // Set the PendingIntent
                .setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);  // Play only one alert sound

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, builder.build());

        // Play sound notification
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alert);
        mediaPlayer.start();
    }
}
