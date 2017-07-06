package com.example.mohit.codemania;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by shubham on 24/3/17.
 */

public class CalAdapter extends ArrayAdapter<CalData> {

    String requiredArr[] = {"codeforces","codechef","hackerrank","hackerearth","topcoder","csacademy"};

    public CalAdapter(Context context, List<CalData> caldatas) {
        super(context, 0,caldatas);

    }
    public View getView(int pos, View convertView, ViewGroup parent){
        View list = convertView;
         if(list==null){
            list= LayoutInflater.from(getContext()).inflate(R.layout.caldata,parent,false);
         }
        CalData data = getItem(pos);
        TextView dateText,nameText,timeText;
        dateText= (TextView) list.findViewById(R.id.date);
        nameText=(TextView)list.findViewById(R.id.name);
        timeText =(TextView)list.findViewById(R.id.time);

        String formattedDate = null;
        Date d1;
        long days;

        View root = nameText.getRootView();


        ImageView imageView = (ImageView) list.findViewById(R.id.img);
        try {
            d1=formatDate(data.date());
            SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
            formattedDate= dateFormat.format(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateText.setText(formattedDate);
        nameText.setText(data.getName());
        timeText.setText(data.getTime());
        try {
            formatDate(data.date());
        } catch (ParseException e) {
            e.printStackTrace();
        }



        int checksiteans = CheckSite(data.getUrl());
        if(checksiteans != -1)
        {
            Log.i("name:",data.getUrl());
            if(checksiteans==0)
                imageView.setImageResource(R.drawable.codeforces);
            else if(checksiteans==1)
                imageView.setImageResource(R.drawable.codechef);
            else if(checksiteans==2)
                imageView.setImageResource(R.drawable.hackerrank);
            else if(checksiteans==3)
                imageView.setImageResource(R.drawable.hackerearth);
            else if(checksiteans==4)
                imageView.setImageResource(R.drawable.topcoder);
            else if(checksiteans==5)
                imageView.setImageResource(R.drawable.csacademy);

        }

        return list;
    }

    public int CheckSite( String urlname) {
        // Works, but is not the best.
        //return haystack.toLowerCase().indexOf( needle.toLowerCase() ) > -1
        for(int i = 0; i < requiredArr.length;i++)
        {
            if(urlname.toLowerCase().contains(requiredArr[i].toLowerCase()))
                return i;
        }
        return -1;
    }

    Date formatDate(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        date1 = df.parse(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");

        return date1;
    }

}
