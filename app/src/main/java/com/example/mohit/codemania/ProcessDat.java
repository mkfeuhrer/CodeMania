package com.example.mohit.codemania;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by shubham on 24/3/17.
 */

public class ProcessDat {
    String data="[{\"time\":\"17:35\",\"date\":\"27/03/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"Educational Codeforces Round 18\"},{\"time\":\"19:05\",\"date\":\"29/03/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"Codeforces Round 407 (Div 1)\"},{\"time\":\"19:05\",\"date\":\"29/03/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"Codeforces Round 407 (Div 2)\"},{\"time\":\"18:00\",\"date\":\"31/03/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"April Fools Contest 2017\"},{\"time\":\"18:35\",\"date\":\"5/04/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"VK Cup 2017 - Wild Card Round 1\"},{\"time\":\"18:35\",\"date\":\"16/04/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"VK Cup 2017 - Round 2\"},{\"time\":\"18:35\",\"date\":\"26/04/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"VK Cup 2017 - Wild Card Round 2\"},{\"time\":\"18:35\",\"date\":\"7/05/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"VK Cup - Round 3\"}]";
    ProcessDat(String cdata){
        data=cdata;
    }
    public ArrayList<CalData> dat() throws JSONException {
        ArrayList<CalData> dat = new ArrayList<>();

        JSONArray jarr= new JSONArray(data);

        for(int i=0;i<jarr.length();i++){
            JSONObject obj= jarr.getJSONObject(i);
            String time = obj.getString("time");
            String date = obj.getString("date");
            String name = obj.getString("contest-name");
            String url=obj.getString("url");
            Log.e("nnmnn",name);
            dat.add(new CalData(name,url,date,time));
        }
        return dat;

    }
}
