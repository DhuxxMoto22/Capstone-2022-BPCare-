package com.example.bloodcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class okhttpsample extends AppCompatActivity {

    private TextView mOkhttp;
    private TextView mOkhttp1;
    private TextView mOkhttp2;
    private TextView mOkhttp3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttpsample);

        mOkhttp = findViewById(R.id.okhttp);
        mOkhttp1 = findViewById(R.id.okhttp);
        mOkhttp2 = findViewById(R.id.okhttp2);
        mOkhttp3 = findViewById(R.id.okhttp3);


        OkHttpClient client = new OkHttpClient();

        String url = "https://pastebin.com/raw/VNcwYJtv";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    okhttpsample.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                                    try {
                                        JSONObject jsonObject = new JSONObject(myResponse);
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

                                                        mOkhttp1.setText(value);
                                                    }
                                                }

                                                JSONArray array4 = new JSONArray(measures);
                                                if (array4.length() > 0) {
                                                    for (int k = 0; k < jsonObject.length(); k--) {
                                                        JSONObject array5 = array4.getJSONObject(k);
                                                        value2 = array5.getString("value");

                                                        mOkhttp2.setText(value2);
                                                    }
                                                }

                                            }
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    try {
                                        JSONObject jsonObject = new JSONObject(myResponse);
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

                                                        mOkhttp3.setText(value);
                                                    }
                                                }


                                            }
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                            mOkhttp.setText(myResponse);
                        }
                    });
                }
            }
        });
    }
}