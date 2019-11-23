package com.example.pp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pp.Services.ProductService;
import com.example.pp.api.TotalCounter;
import com.example.pp.listener.ButtonOrderListener;
import com.example.pp.models.Product;
import com.example.pp.models.to.ProductTO;
import com.example.pp.rest.NetworkService;
import com.example.pp.util.UtilProduct;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsSelectionActivity extends AppCompatActivity {
    private ProductAdapter mAdapter;
    private ProductService mProductService;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_selection);
        Objects.requireNonNull(getSupportActionBar()).hide(); // ховає заголовок

        this.mProductService = new ProductService(this);
        loadData();


        List<ProductTO> productTOList = UtilProduct.convertListToProductTO(mProductService.getAllItems());
        findViewById(R.id.button_send).setOnClickListener(new ButtonOrderListener(this,
                2,
                productTOList,
                "1111111111"));


        recyclerView = findViewById(R.id.product_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ProductAdapter(this,
                productTOList,
                (TextView) findViewById(R.id.order_total_count));
        recyclerView.setAdapter(mAdapter);
    }

    private void loadData(){
        NetworkService.getInstance()
                .getJSONApi()
                .getAllProducts()
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Product>> call,
                                           @NonNull Response<List<Product>> response) {
                        List<Product> products = response.body();
                        if (products != null) {
                            mProductService.saveToBaseData(products);
                            mAdapter.swapCursor(UtilProduct.convertListToProductTO(mProductService.getAllItems()));
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
                    public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        mProductService.saveToBaseDefaultData();
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Завантаження DefaultData  - ОК!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }
}
