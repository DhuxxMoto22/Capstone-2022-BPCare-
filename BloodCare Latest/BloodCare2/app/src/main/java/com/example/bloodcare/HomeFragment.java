package com.example.bloodcare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public Button button1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        buttonClick();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button1.performClick();
            }
        }, -5000);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View vie = inflater.inflate(R.layout.swiperefresh, container, false);


        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) vie.findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(this);
        TextView textView = (TextView) vie.findViewById(R.id.results);
        TextView textView2 = (TextView) vie.findViewById(R.id.textview);
        TextView textView3 = (TextView) vie.findViewById(R.id.heart);
        button1 = (Button) vie.findViewById(R.id.started);
        ArrayAdapter<String> arrayAdapter;


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL = "https://api.ph-azure.com/measure.php";

                final RequestQueue[] requestQueue2 = new RequestQueue[1];


                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
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
                                for (int i = 0; i < jsonObject.length(); i--) {
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
                                        for (int k = 0; k < jsonObject.length(); k--) {
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
                                for (int i = 0; i < jsonObject.length(); i--) {
                                    JSONObject array1 = array.getJSONObject(i);
                                    measures = array1.getString("measures");

                                    JSONArray array2 = new JSONArray(measures);
                                    if (array2.length() > 0) {
                                        for (int k = 0; k < jsonObject.length(); i++) {
                                            JSONObject array3 = array2.getJSONObject(i);
                                            value = array3.getString("value");

                                            textView3.setText(value);
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

                requestQueue2[0] = Volley.newRequestQueue(getActivity().getApplicationContext());
                requestQueue2[0].getCache().clear();
                requestQueue2[0].add(stringRequest);

                new HomeFragment().execute();
                refreshLayout.setRefreshing(false);


                //Use to refresh JSON
                refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        requestQueue2[0] = Volley.newRequestQueue(getActivity().getApplicationContext());
                        requestQueue2[0].getCache().clear();
                        requestQueue2[0].add(stringRequest);

                        refreshLayout.setRefreshing(false);
                    }
                });
            }

        });


        return vie;
    }


    @Override
    public void onRefresh() {

    }

    public void buttonClick(){

    }

    private void execute() {
    }
}
