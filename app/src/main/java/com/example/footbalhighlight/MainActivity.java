package com.example.footbalhighlight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnItemClickedListener {

    private RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;

    private ProgressDialog mDialog;

    String theType;

    ArrayList<FootballModel> highlight_List = new ArrayList<>();;

    //the URL
    private static final String URL = "https://free-football-soccer-videos1.p.rapidapi.com/v1/";

    TextView noConnection,connectMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        noConnection = (TextView)findViewById(R.id.textView_NoConnection);
        connectMe = (TextView)findViewById(R.id.textView_ConnectMe);;

        mDialog=new ProgressDialog(this);

        recyclerView=findViewById(R.id.recycler_home);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // get the vedioHTml from MainActivity
        Intent intent = getIntent();
        theType = intent.getStringExtra("type");

        loadData();
    }

    private void loadData() {

        if(new Utilites().isNetworkAvailable(this))
        {
            recyclerView.setVisibility(View.VISIBLE);

            noConnection.setVisibility(View.GONE);
            connectMe.setVisibility(View.GONE);


            mDialog.setMessage("Loading..");
            mDialog.show();


            //creating a string request to send request to the url
            JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, URL,null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            //hiding the progressbar after completion
                            mDialog.dismiss();

                            try {


                                //now looping through all the elements of the json array
                                for (int i = 0; i < response.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject ftballObject = response.getJSONObject(i);
                                    JSONObject catagory = ftballObject.getJSONObject("competition");

                                    if(theType.equalsIgnoreCase("OTHER"))
                                    {
                                        if( !catagory.getString("name").contains("ENGLAND") && !catagory.getString("name").contains("SPAIN") && !catagory.getString("name").contains("ITALY")&& !catagory.getString("name").contains("FRANCE") && !catagory.getString("name").contains("GERMANY") )
                                        {
                                            //creating a hero object and giving them the values from json object
                                            FootballModel footballModel = new FootballModel(ftballObject.getString("title"), catagory.getString("name"), new Utilites().dateFormater(ftballObject.getString("date")),ftballObject.getString("thumbnail"),ftballObject.getString("embed"));

                                            //adding the hero to highlight_List
                                            highlight_List.add(footballModel);
                                        }
                                    }
                                    else {
                                        if( catagory.getString("name").contains(theType))
                                        {
                                            //creating a hero object and giving them the values from json object
                                            FootballModel footballModel = new FootballModel(ftballObject.getString("title"), catagory.getString("name"), new Utilites().dateFormater(ftballObject.getString("date")),ftballObject.getString("thumbnail"),ftballObject.getString("embed"));

                                            //adding the hero to highlight_List
                                            highlight_List.add(footballModel);
                                        }

                                    }






                                }

                                //creating custom adapter object
                                recycleAdapter = new RecycleAdapter(getApplicationContext(),highlight_List,MainActivity.this::onItemClicked);

                                //adding the adapter to listview
                                recyclerView.setAdapter(recycleAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //displaying the error in toast if occurrs
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }



            )
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("x-rapidapi-host", "free-football-soccer-videos1.p.rapidapi.com");
                    headers.put("x-rapidapi-key", "64bca0886bmsha7377930000aed5p1a6aecjsn8fa7b68dbbfb");
                    return headers;
                }
            }
                    ;

            //creating a request queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            //adding the string request to request queue
            requestQueue.add(stringRequest);
        }

        else {

            noConnection.setVisibility(View.VISIBLE);
            connectMe.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }



    }

    @Override
    public void onItemClicked(int position) {
        FootballModel shopingModle = highlight_List.get(position);

        String vedioHtml=shopingModle.getVedioHtml();

        // start the SecondActivity
        Intent intent = new Intent(this, Vedio_View.class);
        intent.putExtra("vedio", vedioHtml);
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_item,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(recyclerView.getVisibility() == View.VISIBLE) {
                    recycleAdapter.getFilter().filter(s);
                } else {
                    // Invisible
                }

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void refreshView(View view) {
        loadData();

    }
}