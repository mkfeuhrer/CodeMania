package com.example.mohit.codemania;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Calendar extends AppCompatActivity {
    int month=06;
    int year = 2017;
    String url="http://clist.by:80/api/v1/contest/?start__gt=2017-06-21%2013%3A43&order_by=start";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_layout);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = sdf.format(new Date());
        Uri.Builder builder= new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("clist.by:80")
               .appendPath("api").appendPath("v1").appendPath("contest").
                appendQueryParameter("username","shubham7120k").
                appendQueryParameter("api_key","a9d4c90d7cda799cdca6b214d6695d466cd5df8b").
                appendQueryParameter("start__gt",now)
                .appendQueryParameter("order_by","start");

        url=builder.build().toString();
        Volley();
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Calendar.this, home.class));
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        return true;
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
    public void Volley(){
        RequestQueue mRequestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 5*1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            updateUi(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //  mTxtDisplay.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //TODO
                        //show error dialogue

                    }
                });
        mRequestQueue.add(jsObjRequest);
    }

    public void updateUi(JSONObject data) throws JSONException {
        ProcessDat parsed = new ProcessDat(data);
        ArrayList<CalData> pdata=parsed.dat() ;
        Iterator<CalData> iter = pdata.iterator();
        //avoiding rest other sites
        while (iter.hasNext()) {
            CalData val = iter.next();
            String site = val.getSite();
            if(site==null)
                iter.remove();
        }
        final ArrayList<CalData> pdata1;
        pdata1=pdata;
        ListView listView = (ListView)findViewById(R.id.list);

        //pdata = parsed.dat();
        //Log.i("result",parsed.dat().toString());
        CalAdapter adapter = new CalAdapter(this,pdata);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                CalData  cd = pdata1.get(position);
                String url = cd.getUrl();

                Intent it =  new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse(url));
                startActivity(it);
            }
        });



    }





}
