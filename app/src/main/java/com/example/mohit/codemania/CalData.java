package com.example.mohit.codemania;

/**
 * Created by shubham on 24/3/17.
 */

public class CalData {
    String requiredArr[] = {"codeforces","codechef","hackerrank","hackerearth","topcoder","csacademy"};
    private String name,site;
    private String url;
    private String date;
    private String time;

    CalData(String cname,String curl,String cdate,String ctime){
        name=cname;
        url=curl;
        date=cdate;
        time=ctime;
        int checksite = CheckSite(url);
        if(checksite!=-1)
        site=requiredArr[checksite];
        else site=null;
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
    public String getSite(){return site;}

    public int CheckSite( String urlname) {
        // Works, but is not the best.
        for(int i = 0; i < requiredArr.length;i++)
        {
            if(urlname.toLowerCase().contains(requiredArr[i].toLowerCase()))
                return i;
        }
        return -1;
    }
}
