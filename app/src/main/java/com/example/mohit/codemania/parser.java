package com.example.mohit.codemania;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// to parse json data

import java.util.ArrayList;
public class parser{
    String jsondata="";
    parser(String data){
        jsondata=data;
    }
   public  ArrayList<ProblemData> parseJson() throws JSONException {
        ArrayList<ProblemData> data = new ArrayList<>();
        JSONObject jsonObject  = new JSONObject(jsondata);
       JSONObject res = jsonObject.getJSONObject("result");
       JSONArray problems = res.getJSONArray("problems");
        JSONArray stats = res.getJSONArray("problemStatistics");
        for(int i=0;i<20 && i< stats.length();i++){
            JSONObject nxtprob = problems.getJSONObject(i);
            JSONObject probstat = stats.getJSONObject(i);
            int id  = nxtprob.getInt("contestId");
            String idx = nxtprob.getString("index");
            String name = nxtprob.getString("name");
            int solvecnt = probstat.getInt("solvedCount");
            ProblemData dat = new ProblemData(id,idx,name,solvecnt);
            data.add(dat);


        }

        return data;
    }
}