package com.example.footbalhighlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class Vedio_View extends AppCompatActivity {



    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio__view);


        // get the vedioHTml from MainActivity
        Intent intent = getIntent();
        String vhtl = intent.getStringExtra("vedio");


        webview = (WebView)this.findViewById(R.id.vedioHtml);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadData(vhtl, "text/html; charset=utf-8", "UTF-8");


    }
}