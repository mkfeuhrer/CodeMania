package com.example.mohit.codemania;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class CodeForcesFrag extends Fragment implements View.OnClickListener{

    EditText handle, webpage;
    Button submission;
    String handles;
    ImageButton queryButton;
    TextView responseView, country, rating, username, city, maxrating, contribution, codechefrating;
    ProgressBar progressBar;
    static final String API_URL = "http://codeforces.com/api/user.info?";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_code_forces, container, false);

        responseView = (TextView) rootView.findViewById(R.id.responseView);
        country = (TextView) rootView.findViewById(R.id.country);
        rating = (TextView) rootView.findViewById(R.id.rating);
        handle = (EditText) rootView.findViewById(R.id.handle);
        city = (TextView) rootView.findViewById(R.id.city);
        maxrating = (TextView) rootView.findViewById(R.id.maxrating);
        contribution = (TextView) rootView.findViewById(R.id.contribution);
        username = (TextView) rootView.findViewById(R.id.username);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        //submission = (Button) rootView.findViewById(R.id.submissions);
        queryButton = (ImageButton) rootView.findViewById(R.id.queryButton);

        queryButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.queryButton:
                handles=handle.getText().toString();
                new RetrieveFeedTask().execute();
                break;

            default:

                break;
        }
    }


    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.GONE);
            responseView.setText("");
        }

        protected String doInBackground(String... urls) {

            try {
                URL url = new URL(API_URL + "handles=" + handles);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream inputStream=urlConnection.getInputStream();
                    if(inputStream==null){
                        return "";
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
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
                Toast.makeText(getActivity(), "Invalid Handle", Toast.LENGTH_SHORT).show();

            }else {
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
                    /*submission.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "http://codeforces.com/submissions/" + handle.getText().toString();
                            Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }
                    });*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



        }
    }


}