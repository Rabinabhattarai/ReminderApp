package com.example.hp.myapplication.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hp.myapplication.R;

public class routineholder extends RecyclerView.ViewHolder {
    TextView rid, rdate, rname, rtask;
    public routineholder(@NonNull View itemView) {
        super(itemView);

        rid = (TextView) itemView.findViewById(R.id.showrid);
        rdate = (TextView) itemView.findViewById(R.id.showdater);
        rname = (TextView) itemView.findViewById(R.id.shownamer);
        rtask = (TextView) itemView.findViewById(R.id.showtaskr);
    }

    public TextView getRid() {
        return rid;
    }

    public void setRid(TextView rid) {
        this.rid = rid;
    }

    public TextView getRdate() {
        return rdate;
    }

    public void setRdate(TextView rdate) {
        this.rdate = rdate;
    }

    public TextView getRname() {
        return rname;
    }

    public void setRname(TextView rname) {
        this.rname = rname;
    }

    public TextView getRtask() {
        return rtask;
    }

    public void setRtask(TextView rtask) {
        this.rtask = rtask;
    }
}
