package com.example.mohit.codemania;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Calendar extends AppCompatActivity {
    String data="[{\"time\":\"17:35\",\"date\":\"27/03/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"Educational Codeforces Round 18\"},{\"time\":\"19:05\",\"date\":\"29/03/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"Codeforces Round 407 (Div 1)\"},{\"time\":\"19:05\",\"date\":\"29/03/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"Codeforces Round 407 (Div 2)\"},{\"time\":\"18:00\",\"date\":\"31/03/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"April Fools Contest 2017\"},{\"time\":\"18:35\",\"date\":\"5/04/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"VK Cup 2017 - Wild Card Round 1\"},{\"time\":\"18:35\",\"date\":\"16/04/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"VK Cup 2017 - Round 2\"},{\"time\":\"18:35\",\"date\":\"26/04/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"VK Cup 2017 - Wild Card Round 2\"},{\"time\":\"18:35\",\"date\":\"7/05/2017\",\"url\":\"codeforces.com\",\"contest-name\":\"VK Cup - Round 3\"},{\"time\":\"21:30\",\"date\":\"31/03/2017\",\"url\":\"https://hackerrank.com\",\"contest-name\":\"Indeed Machine Learning CodeSprint\"},{\"time\":\"20:30\",\"date\":\"2/04/2017\",\"url\":\"https://hackerrank.com\",\"contest-name\":\"HourRank - 19\"},{\"time\":\"12:30\",\"date\":\"10/04/2017\",\"url\":\"https://hackerrank.com\",\"contest-name\":\"Week of Code 31\"},{\"time\":\"21:30\",\"date\":\"22/04/2017\",\"url\":\"https://hackerrank.com\",\"contest-name\":\"101 Hack 48\"}]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_layout);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            updateUi(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Log.e("aa","done");
        startActivity(new Intent(Calendar.this, home.class));
        finish();
    }


    //asynctask to get caldata
    public class getCaldatasync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            try {

                URL queryurl = new URL(url[0]);
                String response = "";
                HttpURLConnection urlConnection = null;
                InputStream inputStream = null;

                urlConnection = (HttpURLConnection) queryurl.openConnection();

                urlConnection.setRequestMethod("GET");

                urlConnection.setConnectTimeout(2000);

                urlConnection.connect();

                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    response = readfromstream(inputStream);
                    return response;

                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String dat) {
            try {
                updateUi(dat);
            } catch (JSONException e) {
                alert();

                e.printStackTrace();
            }


        }
    }

    private void alert() {
       AlertDialog.Builder b1 = new AlertDialog.Builder(this);
        b1.setMessage(" No Internet ");
        b1.setCancelable(true);

        b1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

    }
    public void updateUi(String data) throws JSONException {
        ProcessDat parsed = new ProcessDat(data);
        ArrayList<CalData> pdata=parsed.dat() ;
        final ArrayList<CalData> pdata1;
        pdata1=pdata;
        Log.e("haha",data);


        ListView listView = (ListView)findViewById(R.id.list);

        pdata = parsed.dat();
        CalAdapter adapter = new CalAdapter(this,pdata);

        listView.setAdapter(adapter);



    }

    public  String readfromstream(InputStream is) throws IOException {
       StringBuilder res = null;
       if(is!=null) {
           InputStreamReader inputStreamReader = new InputStreamReader(is, Charset.forName("UTF-8"));
           BufferedReader reader = new BufferedReader(inputStreamReader);

           String line = reader.readLine();
           while (line != null) {
               res.append(line);
               line = reader.readLine();

           }
       }
       return res.toString();
   }



}
