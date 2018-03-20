package com.qiyi.search.druidclient.entity;

public interface DruidQuery {

    public static String QUERY_URL = "/druid/v2/?pretty";

    public String getSendUrl();

    public String getQueryJSON();
}
