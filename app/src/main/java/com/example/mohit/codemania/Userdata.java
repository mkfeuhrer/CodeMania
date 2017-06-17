package com.example.mohit.codemania;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Userdata extends AppCompatActivity {

    Button submission, queryButton;
    TextView responseView, country, rating, username, city, maxrating, contribution, handle;
    ProgressBar progressBar;
    static final String API_URL = "http://codeforces.com/api/user.info?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        responseView = (TextView) findViewById(R.id.responseView);
        country = (TextView) findViewById(R.id.country);
        rating = (TextView) findViewById(R.id.rating);
        handle = (TextView) findViewById(R.id.handle);
        city = (TextView) findViewById(R.id.city);
        maxrating = (TextView) findViewById(R.id.maxrating);
        contribution = (TextView) findViewById(R.id.contribution);
        username = (TextView) findViewById(R.id.username);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        submission = (Button) findViewById(R.id.submissions);
        //webpage = (EditText) findViewById(R.id.Codechef_handle);
        //codechefrating = (TextView) findViewById(R.id.Codechef_handle);


        queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String ccuser = webpage.getText().toString();

                //Log.e("user", ccuser);

                new RetrieveFeedTask().execute();
            }
        });
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.GONE);
            responseView.setText("");
        }

        protected String doInBackground(String... urls) {
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String s1 = settings.getString("username", null);
            SQLiteDatabase data=openOrCreateDatabase("codemania",MODE_PRIVATE,null); //nobody other can access
            data.execSQL("create table if not exists hint (name varchar, password varchar,confirm_password varchar,email varchar,codeforces varchar,phone varchar);");
            String handles = "select codeforces from hint where name='" + s1;
            //String handles2 = webpage.getText().toString();
            // Do some validation here

            try {
                URL url = new URL(API_URL + "handles=" + handles);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            //responseView.setText(response);
            try {
                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                JSONArray obj1 = object.getJSONArray("result");
                JSONObject obj = obj1.getJSONObject(0);
                //   String email1 = obj.getString("country");
                // System.out.println(email1);
                int rating1 = 0, maxrating1 = 0;
                String country1 = null, city1 = null, contribution1 = null, firstname = null, lastname = null;
                if (obj.has("rating"))
                    rating1 = obj.getInt("rating");
                if (rating1 != 0)
                    rating.setText(rating1 + "");
                else rating.setText("Not Found ");
                if (obj.has("country"))
                    country1 = obj.getString("country");
                else country1 = "Not Found ";
                country.setText(country1);
                if (obj.has("city"))
                    city1 = obj.getString("city");
                else city1 = "Not Found ";
                city.setText(city1);
                if (obj.has("maxRating"))
                    maxrating1 = obj.getInt("maxRating");
                if (maxrating1 != 0)
                    maxrating.setText(maxrating1 + "");
                else maxrating.setText("Not Found");
                if (obj.has("contribution"))
                    contribution1 = obj.getString("contribution");
                else contribution1 = "Not Found";
                contribution.setText(contribution1);
                if (obj.has("firstName"))
                    firstname = obj.getString("firstName");
                else firstname = "Not Found";
                if (obj.has("lastName"))
                    lastname = obj.getString("lastName");
                else lastname = "";
                username.setText(firstname + " " + lastname);
                submission.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "http://codeforces.com/submissions/" + handle.getText().toString();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }


//            try {
//                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//                String requestID = object.getString("requestId");
//                int likelihood = object.getInt("likelihood");
//                JSONArray photos = object.getJSONArray("photos");
//                .
//                .
//                .
//                .
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this,home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Userdata.this, home.class));
        finish();

    }
}