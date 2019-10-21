package com.example.pp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.pp.models.Shop;
import com.example.pp.models.Store;
import com.example.pp.models.UserInMemoryStore;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsSelectionActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private ShopAdapter mAdapter;
    private final Store<Shop> store = new UserInMemoryStore();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_selection);
    }
}
