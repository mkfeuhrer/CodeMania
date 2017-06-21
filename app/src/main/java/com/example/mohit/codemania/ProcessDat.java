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
    JSONObject data;
    ProcessDat(JSONObject cdata){
        data=cdata;
    }
    public ArrayList<CalData> dat() throws JSONException {
        ArrayList<CalData> dat = new ArrayList<>();
        JSONObject jobj =data;

        JSONArray jarr= jobj.getJSONArray("objects");

        for(int i=0;i<jarr.length();i++){
            JSONObject obj= jarr.getJSONObject(i);
            String time = obj.getString("start");
            String time1=time.substring(time.indexOf("T")+1,time.length());
            String date = time.substring(0,time.indexOf("T"));
            String name = obj.getString("event");
            String url=obj.getString("href");
            dat.add(new CalData(name,url,date,time1));
        }
        return dat;

    }
}
