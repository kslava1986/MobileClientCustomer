package com.example.pp.util;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.pp.Services.ShopsService;
import com.example.pp.models.Shop;
import com.example.pp.rest.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilRequest {
/*
    private void loadDataShops(ShopsService shopsService) {


        NetworkService.getInstance()
                .getJSONApi()
                .getShops()
                .enqueue(new Callback<List<Shop>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Shop>> call, @NonNull Response<List<Shop>> response) {
                        List<Shop> shops = response.body();
                        if (shops != null) {
                            shopsService.saveToBaseData(shops);
                            recyclerView.invalidate();
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Завантаження Сервер - ОК!", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Завантаження - не відбулось!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Shop>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        saveToBaseDefaultData();
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Завантаження DefaultData  - ОК!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }*/
}
