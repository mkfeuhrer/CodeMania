package com.example.mohit.codemania;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
//to create custom list view
public class Adapter extends ArrayAdapter<ProblemData> {
    public Adapter(Context context, List<ProblemData> pdata) {
        super(context,0, pdata);
    }
    public View getView(int pos,View recyle,ViewGroup parent){
        View list = recyle;
        if(list==null){
            list = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        ProblemData nxtprob = getItem(pos);
        String name = nxtprob.getName();
        int id= nxtprob.getid();
        String idx = nxtprob.getIndex();

        TextView pname = (TextView) list.findViewById(R.id.name);
        pname.setText(name);

    return list;

    }
}
