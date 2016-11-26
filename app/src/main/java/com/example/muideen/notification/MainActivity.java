package com.example.muideen.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1001;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Button showButton = (Button) findViewById(R.id.show_notification);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });

        Button hideButton = (Button) findViewById(R.id.cancel_notification);
        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideNotification();
            }
        });
    }

    private void showNotification() {
        Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext());
        notification.setTicker("Basic Notification Demo");
        notification.setContentTitle("Basic Notification Demo");
        notification.setContentText("You have new notification");
        notification.setSmallIcon(R.drawable.ic_notifications_active_black_24dp);
        notification.setContentIntent(pendingIntent);
        notification.setAutoCancel(true);
        notification.setWhen(System.currentTimeMillis());

        manager.notify(NOTIFICATION_ID, notification.build());
    }

    private void hideNotification() {
        manager.cancel(NOTIFICATION_ID);
    }
}
