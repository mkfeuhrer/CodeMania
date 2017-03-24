package com.example.mohit.codemania;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shubham on 24/3/17.
 */

public class CalAdapter extends ArrayAdapter<CalData> {

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

        ImageView imageView = (ImageView) list.findViewById(R.id.img);

        dateText.setText(data.date());
        nameText.setText(data.getName());
        timeText.setText(data.getTime());

        String url = data.getUrl();
        if(url.contains("codeforces"))
            imageView.setImageResource(R.drawable.codeforces);
        else if(url.contains("hackerrank"))
            imageView.setImageResource(R.drawable.hackerrank);

        return list;
    }
}
