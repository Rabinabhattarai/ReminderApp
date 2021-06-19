package com.example.hp.myapplication.Water.Dialouge.Setting;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.hp.myapplication.R;

public class NoteService extends BroadcastReceiver{
    int MID = 0;
    @Override
    public void onReceive(Context p_context, Intent p_intent)
    {
        NotificationManager notificationManager = (NotificationManager) p_context.getSystemService(Context.NOTIFICATION_SERVICE);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder mNotifyBuilder = new Notification.Builder(p_context);

        mNotifyBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Did you forget to drink Water?")
                .setContentText("Water drinking reminder!!")
                .setSound(alarmSound)
                //.setAutoCancel(true).setWhen(when)
                //.setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        notificationManager.notify(MID, mNotifyBuilder.build());
        MID++;
    }
}
