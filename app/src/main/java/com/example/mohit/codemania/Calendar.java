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
import java.util.ArrayList;

public class Calendar extends AppCompatActivity {
    String url="http://clist.by/api/v1/contest/?username=shubham7120k&api_key=a9d4c90d7cda799cdca6b214d6695d466cd5df8b&limit=50&start__year=2017&start__month=06&order_by=start";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_layout);
        Volley();
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Calendar.this, home.class));
        finish();
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
        final ArrayList<CalData> pdata1;
        pdata1=pdata;


        ListView listView = (ListView)findViewById(R.id.list);

        pdata = parsed.dat();
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
