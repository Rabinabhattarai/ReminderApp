package com.example.hp.myapplication.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hp.myapplication.R;

public class Myholder extends RecyclerView.ViewHolder {
    TextView remid, mdate, mtime, mcommnet;

    public Myholder(@NonNull View itemView) {
        super(itemView);


        //ASSIGN
        remid = (TextView) itemView.findViewById(R.id.showremid);
        mdate = (TextView) itemView.findViewById(R.id.showdaterem);
        mtime = (TextView) itemView.findViewById(R.id.showtimerem);
        mcommnet = (TextView) itemView.findViewById(R.id.showcommentrem);

    }

    public TextView getRemid() {
        return remid;
    }

    public void setRemid(TextView remid) {
        this.remid = remid;
    }

    public TextView getMdate() {
        return mdate;
    }

    public void setMdate(TextView mdate) {
        this.mdate = mdate;
    }

    public TextView getMtime() {
        return mtime;
    }

    public void setMtime(TextView mtime) {
        this.mtime = mtime;
    }

    public TextView getMcommnet() {
        return mcommnet;
    }

    public void setMcommnet(TextView mcommnet) {
        this.mcommnet = mcommnet;
    }

}

