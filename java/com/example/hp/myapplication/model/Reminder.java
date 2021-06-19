package com.example.hp.myapplication.model;

public class Reminder {
    private String remid;
    private String date;
    private String tme;
    private String comment;
    private String uid;

    public String getRemid() {
        return remid;
    }

    public void setRemid(String remid) {
        this.remid = remid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTme() {
        return tme;
    }

    public void setTme(String tme) {
        this.tme = tme;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAlarmtype() {
        return alarmtype;
    }

    public void setAlarmtype(String alarmtype) {
        this.alarmtype = alarmtype;
    }

    private String alarmtype;

    public Reminder(String remid,String date, String tme, String comment, String alarmtype, String uid) {
        this.remid = remid;
        this.date = date;
        this.tme = tme;
        this.comment = comment;
        this.alarmtype = alarmtype;
        this.uid = uid;
    }

    public Reminder(){}
}
