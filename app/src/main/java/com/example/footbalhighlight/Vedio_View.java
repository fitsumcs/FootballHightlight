package com.example.footbalhighlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Vedio_View extends AppCompatActivity {

    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio__view);

        tv_title = (TextView)findViewById(R.id.textView_title);

        // get the text from MainActivity
        Intent intent = getIntent();
        String text = intent.getStringExtra("title");

        tv_title.setText(text);

    }
}