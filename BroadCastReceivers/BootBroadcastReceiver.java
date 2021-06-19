//package com.example.hp.myapplication.BroadCastReceivers;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//
//import com.example.hp.myapplication.Water.Dialouge.Setting.PrefsHelper;
//import com.example.hp.myapplication.database.DrinkDataSource;
//
///**
// * Created by Abeer on 5/2/2017.
// */
//public class BootBroadcastReceiver extends BroadcastReceiver {
//    @Override
//    public void onReceive(Context pContext, Intent intent) {
//        DrinkDataSource db= new DrinkDataSource(pContext);
//        db.open();
//        int waterNeed= PrefsHelper.getWaterNeedPrefs(pContext);
//        db.createMissingDateLog(0,waterNeed);
//        AlarmHelper.setDBAlarm(pContext);
//    }
//}
