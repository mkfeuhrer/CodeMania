package com.example.mohit.codemania;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class TopCoder extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_coder, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        TutorialData tutorialData = new TutorialData();
        final ArrayList<Tutorial> tuts = tutorialData.getTutorialData();

        Adaptor adaptor = new Adaptor(getActivity(), tuts);
        listView.setAdapter(adaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("hh", "hello you have clicked"+i+" "+l);
                String url = tuts.get(i).getUrl();
                Intent intent = new Intent(getActivity(), Browser.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        return rootView;
    }
}