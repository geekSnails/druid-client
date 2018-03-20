package com.qiyi.search.druidclient.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Intervals {

    private Date startTime;

    private Date endTime;

    public Intervals(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getIntervalsString() {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+08:00'");
        StringBuilder builder = new StringBuilder();
        builder.append(simpleDateFormat.format(startTime))
                .append("/")
                .append(simpleDateFormat.format(endTime));
        return builder.toString();
    }
}
