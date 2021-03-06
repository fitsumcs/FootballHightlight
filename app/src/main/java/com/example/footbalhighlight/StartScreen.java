package com.example.footbalhighlight;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartScreen extends AppCompatActivity {

    TextView tv_england , tv_spain, tv_italy,tv_france,tv_germany,tv_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);


        tv_england  = (TextView)findViewById(R.id.textView_england);
        tv_spain =  (TextView)findViewById(R.id.textView_spain);
        tv_italy  = (TextView)findViewById(R.id.textView_italy);
        tv_france = (TextView)findViewById(R.id.textView_france);
        tv_germany = (TextView)findViewById(R.id.textView_germany);
        tv_other = (TextView)findViewById(R.id.textView_other);;
    }

    //england
    public void viewEngland(View view) {
        // start the SecondActivity
        gotActivity(this, "ENGLAND" ,  MainActivity.class);
    }

    //spain

    public void viewSpain(View view) {
        // start the SecondActivity
        gotActivity(this, "SPAIN" ,  MainActivity.class);
    }

    //italy
    public void viewItaly(View view) {
        // start the SecondActivity
        gotActivity(this, "ITALY" ,  MainActivity.class);
    }

    //france
    public void viewFrance(View view) {
        // start the SecondActivity
        gotActivity(this, "FRANCE" ,  MainActivity.class);
    }

    //germany

    public void viewGermany(View view) {

        // start the SecondActivity
        gotActivity(this, "GERMANY" ,  MainActivity.class);
    }

    //other
    public void viewOther(View view) {
        // start the SecondActivity
        gotActivity(this, "OTHER" ,  MainActivity.class);
    }


    public void gotActivity(Context context, String type , Class cls)
    {
        // start the SecondActivity
        Intent intent = new Intent(context, cls);
        intent.putExtra("type", type);
        startActivity(intent);
    }






}