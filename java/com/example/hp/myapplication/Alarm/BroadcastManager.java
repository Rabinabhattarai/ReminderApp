package com.example.hp.myapplication.Alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.example.hp.myapplication.R;
import com.example.hp.myapplication.database.DatabaseHelper;
import com.example.hp.myapplication.remlist;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BroadcastManager extends BroadcastReceiver {
    private DatabaseHelper db;
    public remlist remlist;

    public void onReceive(Context context, Intent intent) {
        //try {
        db = new DatabaseHelper(context);
        remlist = new remlist();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate1 = df1.format(c.getTime());

        SimpleDateFormat df2 = new SimpleDateFormat("HH:MM");
        String foemattedtime = df2.format(c.getTime());

        //String timereal = hours + ":" + min;

        String show = String.valueOf(remlist.uidfech());
        Cursor fetch = db.checkUserfornotify(show);
        fetch.moveToFirst();

        while (!fetch.isAfterLast()) {

            String getdate = fetch.getString(0);
            String gettime = fetch.getString(1);
            String getcomment = fetch.getString(2);

            fetch.moveToNext();
            //Log.d(TAG, "getdate: " + getdate + "gettime" + gettime);
            //scheduleNotification(getNotification(getcomment));
            if (formattedDate1.equals(getdate) && foemattedtime.equals(gettime)) {
                Intent it = new Intent(context, com.example.hp.myapplication.remlist.class);
                createNotification(context, it, "new mensage", "body!", "this is a mensage");
//                Log.d(TAG, "onReceive: receive");
                //Log.d(TAG, "getdate: " + getdate + "gettime" + gettime);
            }

        }
    }

//        catch(
//    Exception e)
//
//    {
//        Log.i("date", "error == " + e.getMessage());
//    }

    public void createNotification(Context context, Intent intent, CharSequence ticker, CharSequence title, CharSequence descricao) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(descricao);
        builder.setSmallIcon(R.drawable.notifylogo);
        builder.setContentIntent(p);
        Notification n = builder.build();
        //create the notification
        n.vibrate = new long[]{150, 300, 150, 400};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.notifylogo, n);
        //create a vibration
        try {

            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        } catch (Exception e) {
        }
    }
}
