package com.example.mohit.codemania;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SpojFrag extends Fragment implements View.OnClickListener {

    ImageButton submit;
    EditText handle;
    TextView username, hand, place, joined, rank, points, institution, prob, submitted;

    /*@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        submit.setOnClickListener();
    }*/



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_spoj, container, false);
        handle = (EditText) rootView.findViewById(R.id.handle);

        username = (TextView) rootView.findViewById(R.id.name);
        hand = (TextView) rootView.findViewById(R.id.hand);
        place = (TextView) rootView.findViewById(R.id.place);
        joined = (TextView) rootView.findViewById(R.id.joined);
        rank = (TextView) rootView.findViewById(R.id.rank);
        points = (TextView) rootView.findViewById(R.id.points);
        institution = (TextView) rootView.findViewById(R.id.institution);
        prob = (TextView) rootView.findViewById(R.id.problems);
        submitted = (TextView) rootView.findViewById(R.id.submitted);

        Log.e("abc", "Inside On Create View");

        submit = (ImageButton) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) { //check for what button is pressed
        switch (v.getId()) {
            case R.id.submit:
                String username = handle.getText().toString();
                if(username.length()!=0) {
                    String ur = "http://www.spoj.com/users/" + username + "/";
                    new QuestionAsynTask().execute(ur);
                }
                else {
                    if (username.length() == 0) {
                        Toast.makeText(getActivity(), "Invalid Username",Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            default:

                break;
        }
    }




    public class QuestionAsynTask extends AsyncTask<String, Void,String>
    {

        SpojData spojData;
        @Override
        protected String doInBackground(String... params) {

            try {
                URL url=new URL(params[0]);
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();

                int status=conn.getResponseCode();

                Log.e("abc", status+"");

                Log.e("abc", "out");
                if (status==HttpURLConnection.HTTP_OK) {
                    Log.e("abc", "inside");

                    InputStream is=conn.getInputStream();
                    BufferedReader br=new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while((line=br.readLine())!=null)
                    {
                        sb.append(line);
                    }
                    String data = sb.toString();
                    Log.e("abc", data);
                    if(data.contains("Become a true programming master")) return "";
                    //System.out.println(data);
                    //Log.e("abc",data+"");
                    return data;
                }
                else {
                    Toast.makeText(getActivity(), "Invalid Handle", Toast.LENGTH_SHORT).show();
                    return "";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("")) {
                Toast.makeText(getActivity(),"Wrong Handle Entered", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(Async.this, "True", Toast.LENGTH_SHORT).show();
                String data = result;

                //Log.e("abc",data.length()+" "+data);

                spojData = new SpojData();

                String name = "user-profile-left";
                int idx = data.indexOf(name)+name.length();
                idx = data.indexOf("<h3>",idx);
                int idx2 = data.indexOf("</h3>", idx);
                //Log.e("abc","name"+" "+idx2);
                //Log.e("abc",data.substring(idx+1, idx2));
                spojData.setName(data.substring(idx+4, idx2));

                idx = data.indexOf("<h4>", idx2)+4;
                idx2 = data.indexOf("</h4>", idx);
                Log.e("abc",data.substring(idx, idx2));
                spojData.setHandle(data.substring(idx+1, idx2));

                name = "<p><i class=\"fa fa-map-marker\"></i> ";
                idx = data.indexOf(name, idx2)+name.length();
                idx2 = data.indexOf("<", idx);
                Log.e("abc",data.substring(idx, idx2));
                spojData.setPlace(data.substring(idx, idx2));

                idx = data.indexOf("Joined", idx2);
                idx2 = data.indexOf("<", idx);
                Log.e("abc",data.substring(idx, idx2));
                spojData.setJoined(data.substring(idx, idx2));

                idx = data.indexOf("World Rank: ", idx2)+"World Rank: ".length();
                idx2 = data.indexOf(" ", idx);
                spojData.setRank(data.substring(idx+1, idx2));

                idx = data.indexOf("(",idx)+1;
                idx2 = data.indexOf(" ", idx);
                spojData.setPoints(data.substring(idx, idx2));

                idx = data.indexOf("Institution: ", idx2)+"Institution: ".length();
                idx2 = data.indexOf("</p>", idx);
                spojData.setInstitution(data.substring(idx, idx2));

                idx = data.indexOf("Problems solved");
                idx = data.indexOf("<dd>", idx)+4;
                idx2 = data.indexOf("</dd>", idx);
                spojData.setProblemSolved(data.substring(idx, idx2));

                idx = data.indexOf("Solutions submitted");
                idx = data.indexOf("<dd>", idx)+4;
                idx2 = data.indexOf("</dd>", idx);
                spojData.setSolutionSubmitted(data.substring(idx, idx2));


                username.setText(spojData.getName());
                hand.setText(spojData.getHandle());
                place.setText(spojData.getPlace());
                joined.setText(spojData.getJoined());
                rank.setText(spojData.getRank());
                points.setText(spojData.getPoints());
                institution.setText(spojData.getInstitution());
                prob.setText(spojData.getProblemSolved());
                submitted.setText(spojData.getSolutionSubmitted());

            }
        }


    }
}