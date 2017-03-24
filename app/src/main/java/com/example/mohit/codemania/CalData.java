package com.example.mohit.codemania;

/**
 * Created by shubham on 24/3/17.
 */

public class CalData {
    private String name;
    private String url;
    private String date;
    private String time;

    CalData(String cname,String curl,String cdate,String ctime){
        name=cname;
        url=curl;
        date=cdate;
        time=ctime;
    }
    public String getTime(){
        return time;
    }
    public String getName(){
        return name;
    }
    public String getUrl(){
        return url;
    }
    public String date(){
        return date;
    }
}
