package com.example.bloodcare.cfinder.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bloodcare.cfinder.data.model.nearby.ModelResults;
import com.example.bloodcare.cfinder.data.response.ModelResultNearby;
import com.example.bloodcare.cfinder.networking.ApiClient;
import com.example.bloodcare.cfinder.networking.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<ModelResults>> modelResultsMutableLiveData = new MutableLiveData<>();
    public static String strApiKey = "AIzaSyAeyAROkO3ExuJsir1dr9-FrrN9ZJx0-ZA";

    public void setMarkerLocation(String strLocation, String strKeyword) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ModelResultNearby> call = apiService.getDataResult(strApiKey, strKeyword, strLocation, "distance");
        call.enqueue(new Callback<ModelResultNearby>() {
            @Override
            public void onResponse(Call<ModelResultNearby> call, Response<ModelResultNearby> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    ArrayList<ModelResults> items = new ArrayList<>(response.body().getModelResults());
                    modelResultsMutableLiveData.postValue(items);
                }
            }

            @Override
            public void onFailure(Call<ModelResultNearby> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<ArrayList<ModelResults>> getMarkerLocation() {
        return modelResultsMutableLiveData;
    }

}
