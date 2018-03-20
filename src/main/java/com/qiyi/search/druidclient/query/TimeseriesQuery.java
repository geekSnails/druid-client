package com.qiyi.search.druidclient.query;

import com.qiyi.search.druidclient.entity.AggregationQuery;
import com.qiyi.search.druidclient.entity.DruidQuery;

import java.util.ArrayList;

public class TimeseriesQuery extends AggregationQuery implements DruidQuery {

    /* 结果排序 默认是false */
    private Boolean descending;

    public TimeseriesQuery() {
        super.setContext(new ArrayList<String>());
        super.setQueryType("timeseries");
    }

    @Override
    public String getSendUrl() {
        return DruidQuery.QUERY_URL;
    }

    @Override
    public String getQueryJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append(super.parse());
        if (descending != null) {
            json.append(",");
            json.append("\"descending\":\"").append(descending).append("\"");
        }
        json.append("}");
        return json.toString();
    }

    @Override
    public String toString() {
        return getQueryJSON();
    }

    public Boolean getDescending() {
        return descending;
    }

    public void setDescending(Boolean descending) {
        this.descending = descending;
    }

}
