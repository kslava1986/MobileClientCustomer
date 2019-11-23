package com.example.pp.Services;

import com.example.pp.models.Product;
import com.example.pp.models.Shop;
import com.example.pp.models.to.ProductTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/shops")
    Call<List<Shop>> getShops();

    @GET("/products")
    Call<List<Product>> getProductsByShop(int shopId);// @param

    @GET("/products")
    Call<List<Product>> getAllProducts();

    @POST("/order")
    Call<String> sendOrder(@Query("shopId") int shopId,
                           @Query("tel") String tel,
                           @Body List<ProductTO> productTOList);
    // 213.223.2262.22?shopId=2&tel=1111111&{list productTO}
}
