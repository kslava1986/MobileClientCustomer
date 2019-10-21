package com.example.pp;

import com.example.pp.models.Product;
import com.example.pp.models.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/shops")
    Call<List<Shop>> getShops();

    @GET("/products")
    Call<List<Product>> getProductsByShop(int shopId);// @param
}
