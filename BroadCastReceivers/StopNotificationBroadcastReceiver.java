package com.example.hp.myapplication.BroadCastReceivers;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StopNotificationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //AlarmHelper.stopNotificationsAlarm(context);
        System.out.println("fired!");
    }
}
