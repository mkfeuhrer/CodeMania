package com.example.mohit.codemania;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import org.json.JSONException;
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

    String url = "http://codeforces.com/api/problemset.problems?tags=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest_main);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.problem_tags,android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tag;
                ArrayList<String> ss= new ArrayList<>();
                ss.add("dp");
                ss.add("dfs");
                ss.add("implementation");
                ss.add("tree");
                ss.add("binary+search");
                ss.add("divide+and+conquer");
                ss.add("greedy");
                ss.add("math");
                tag= ss.get(position);
                String url2=url+tag;
                problemsAsyncTask ptask = new problemsAsyncTask();
                ptask.execute(url2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SuggestMain.this, home.class));
        finish();
    }


    public class problemsAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... url) {
            String data = null;
            URL queryurl = null;
            try {
                queryurl = new URL(url[0]);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String response ="";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection  = (HttpURLConnection)queryurl.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(2000);
                if(urlConnection.getResponseCode()==200){
                    inputStream=urlConnection.getInputStream();
                    response=readData(inputStream);
                    data = response;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
            }


        protected void onPostExecute(String data)
        {
          ///  Log.e("hah",data);
            try {
                if(data!=null) {
                    updateUi(data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public String  readData(InputStream is) throws IOException {
        StringBuilder out = new StringBuilder();

        String read=null;
        if(is!=null) {
            InputStreamReader inputStreamReader = new InputStreamReader(is, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            read = reader.readLine();
            while(read!=null){
                out.append(read);
                read = reader.readLine();

            }
        }
        return out.toString();

    }
    public void updateUi(String data) throws JSONException {
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
