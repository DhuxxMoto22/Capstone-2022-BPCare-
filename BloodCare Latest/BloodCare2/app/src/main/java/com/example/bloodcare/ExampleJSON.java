package com.example.bloodcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.MediaRouteButton;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExampleJSON extends AppCompatActivity {

    private String URL = "https://pastebin.com/raw/VNcwYJtv";
    private RecyclerView mList;
    public Button button1;

    private LinearLayoutManager linearLayoutManager;
    private List<HomeString> homeList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_json);

        buttonClick();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button1.performClick();
            }
        }, 1500);

        mList = findViewById(R.id.main_list);

        homeList = new ArrayList<>();
        adapter = new HomeAdapter(getApplicationContext(), homeList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.setAdapter(adapter);


    }
    public void buttonClick(){

        button1 = findViewById(R.id.starteds);
        TextView textView = findViewById(R.id.main_value);
        TextView textView2 = findViewById(R.id.main_value2);

       button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, URL, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       try {
                           JSONObject jsonObject = new JSONObject(response);
                           String body = jsonObject.getString("body");
                           JSONObject object1 = new JSONObject(body);
                           String measuregrps = object1.getString("measuregrps");
                           JSONArray array = new JSONArray(measuregrps);
                           String measures = null;
                           String value = null;
                           String value2 = null;
                           if (array.length() > 0) {
                               for (int i = 0; i < jsonObject.length(); i++) {
                                   JSONObject array1 = array.getJSONObject(i);
                                   measures = array1.getString("measures");

                                   JSONArray array2 = new JSONArray(measures);
                                   if (array2.length() > 0) {
                                       for (int k = 0; k < jsonObject.length(); k++) {
                                           JSONObject array3 = array2.getJSONObject(k);
                                           value = array3.getString("value");

                                           textView.setText(value);
                                       }
                                   }

                                   JSONArray array4 = new JSONArray(measures);
                                   if (array4.length() > 0) {
                                       for (int k = 0; k < jsonObject.length(); k++) {
                                           JSONObject array5 = array4.getJSONObject(k);
                                           value2 = array5.getString("value");

                                           textView2.setText(value2);
                                       }
                                   }

                               }
                           }

                       } catch (Exception e) {
                           e.printStackTrace();
                       }

                   }

               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               });
               RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
               requestQueue1.add(stringRequest);
           }
       });


    }
}