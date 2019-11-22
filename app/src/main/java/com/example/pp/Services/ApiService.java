package com.example.pp.Services;

import com.example.pp.models.Product;
import com.example.pp.models.Shop;
import com.example.pp.models.to.ProductTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/shops")
    Call<List<Shop>> getShops();

    @GET("/products")
    Call<List<Product>> getProductsByShop(int shopId);// @param

    @GET("/products")
    Call<List<Product>> getAllProducts();

    @POST("/order")
    void sendOrder(int shopId,String tel, List<ProductTO> productTOList);
}
