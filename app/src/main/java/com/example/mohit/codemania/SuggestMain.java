package com.example.mohit.codemania;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;



public class SuggestMain extends AppCompatActivity  {
    //TODO:Find new problem suggestion API
    String urlx = "http://codeforces.com/api/problemset.problems?tags=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest_main);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String url="http://codeforces.com/api/problemset.problems?tags=implementation";
        Volley(url);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.problem_tags,android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tag;
                ArrayList<String> ss= new ArrayList<>();
                ss.add("brute+force");
                ss.add("implementation");
                ss.add("sortings");
                ss.add("math");
                ss.add("number+theory");
                ss.add("data+structures");
                ss.add("dp");
                ss.add("binary+search");
                ss.add("divide+and+conquer");
                ss.add("greedy");
                ss.add("constructive+algorithms");

                if(position>0) {
                    tag = ss.get(position);
                    String url2 = urlx + tag;
                    String url=url2;
                    Volley(url);
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    public void Volley(String url){
        RequestQueue mRequestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 5*1024 * 1024); // 5MB cap
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
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SuggestMain.this, home.class));
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        return true;
    }

    public void updateUi(JSONObject data) throws JSONException {
        parser parsed = new parser(data);
         ArrayList<ProblemData> pdata ;
        final ArrayList<ProblemData> pdata1;
        pdata1=parsed.parseJson();
        ListView listView = (ListView)findViewById(R.id.list);

        pdata = parsed.parseJson();
        Adapter adapter = new Adapter(this,pdata);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                view.setSelected(true);
                ProblemData dat = pdata1.get(position);
                String url1 = "http://codeforces.com/problemset/problem/";
                url1+=dat.getid()+"/";
                url1+=dat.getIndex();
                Intent it =  new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse(url1));
                startActivity(it);
            }
        });

    }
}
