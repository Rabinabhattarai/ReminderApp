package com.example.hp.myapplication.model;

public class routine {
    private String routineid;
    private String routinedate;
    private String routinename;
    private String routinetasktodo;
    private String uid;

    public String getRoutineid() {
        return routineid;
    }

    public void setRoutineid(String routineid) {
        this.routineid = routineid;
    }

    public String getRoutinedate() {
        return routinedate;
    }

    public void setRoutinedate(String routinedate) {
        this.routinedate = routinedate;
    }

    public String getRoutinename() {
        return routinename;
    }

    public void setRoutinename(String routinename) {
        this.routinename = routinename;
    }

    public String getRoutinetasktodo() {
        return routinetasktodo;
    }

    public void setRoutinetasktodo(String routinetasktodo) {
        this.routinetasktodo = routinetasktodo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public routine(String routineid, String routinedate, String routinename, String routinetasktodo, String uid) {
        this.routineid = routineid;
        this.routinedate = routinedate;
        this.routinename = routinename;
        this.routinetasktodo = routinetasktodo;
        this.uid = uid;
    }

    public routine(){}
}
