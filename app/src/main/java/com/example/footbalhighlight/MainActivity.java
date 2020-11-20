package com.example.footbalhighlight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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

    ArrayList<FootballModel> highlight_List = new ArrayList<>();;

    //the URL
    private static final String URL = "https://free-football-soccer-videos1.p.rapidapi.com/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler_home);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        loadData();
    }

    private void loadData() {



        //creating a string request to send request to the url
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, URL,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {



                        try {


                            //now looping through all the elements of the json array
                            for (int i = 0; i < response.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = response.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                FootballModel footballModel = new FootballModel(heroObject.getString("title"), heroObject.getString("title"),heroObject.getString("date"));

                                //adding the hero to highlight_List
                                highlight_List.add(footballModel);
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

    @Override
    public void onItemClicked(int position) {


    }


}