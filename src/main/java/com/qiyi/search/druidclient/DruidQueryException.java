package com.qiyi.search.druidclient;

public class DruidQueryException extends RuntimeException{

    public DruidQueryException(String msg){
        super(msg);
    }

    public DruidQueryException(String msg, Exception exception){
        super(msg, exception);
    }
}
