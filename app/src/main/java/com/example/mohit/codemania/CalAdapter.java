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

    public CalAdapter(Context context, List<CalData> caldatas) {
        super(context, 0,caldatas);

    }
    public View getView(int pos, View convertView, ViewGroup parent){
        View list = convertView;
        // if(list==null){
        list= LayoutInflater.from(getContext()).inflate(R.layout.caldata,parent,false);

        //}
        CalData data = getItem(pos);
        TextView dateText,nameText,timeText;
        dateText= (TextView) list.findViewById(R.id.date);
        nameText=(TextView)list.findViewById(R.id.name);
        timeText =(TextView)list.findViewById(R.id.time);

        String formattedDate = null;
        Date d1;
        long days;

        // Find the root view
        View root = nameText.getRootView();

        // Set the color

        ImageView imageView = (ImageView) list.findViewById(R.id.img);
        try {
            d1=formatDate(data.date());


            SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
            formattedDate= dateFormat.format(d1);
            Date d2 = new Date();
            days =getDifferenceDays(d2,d1);

            root.setBackgroundColor(getColor(days));




           // Log.d("khlkhlk ",days+" "+d1.getDate());
            //formattedDate=formatDate(data.date());
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
        String url = data.getUrl();
        if(url.contains("codeforces"))
            imageView.setImageResource(R.drawable.codeforces);
        else if(url.contains("hackerrank"))
            imageView.setImageResource(R.drawable.hackerrank);
        else if(url.contains("codechef"))
            imageView.setImageResource(R.drawable.codechef);
        else if(url.contains("hackerearth"))
            imageView.setImageResource(R.drawable.hackerearth);



        return list;
    }
    Date formatDate(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date1;
        date1 = df.parse(date);
       // Log.e("asdasda",date1+" ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        String formattedDate= dateFormat.format(date1);

        return date1;
    }
    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    private int getColor(long day) {
        int magnitudeColorResourceId;
        if(day<=3){
            magnitudeColorResourceId=R.color.less_days;

        }else{
            magnitudeColorResourceId=R.color.more_days;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}
