package com.example.mohit.codemania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class Browser extends AppCompatActivity {

    WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        url = getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.browser);
        webView.loadUrl(url);
    }
}
