package com.example.mohit.codemania;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Contact extends AppCompatActivity {
    Button github,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        github = (Button)findViewById(R.id.github);
        email = (Button)findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="mailto:mohitfeuhrer@gmail.com";
                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="http://github.com/mkfeuhrer/CodeMania";
                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Contact.this, home.class));
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        return true;
    }
}
