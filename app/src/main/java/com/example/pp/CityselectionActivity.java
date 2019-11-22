package com.example.pp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pp.Services.ShopsService;
import com.example.pp.models.Shop;
import com.example.pp.rest.NetworkService;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pp.data.ShopContract.ShopEntry.TABLE_NAME;

public class CityselectionActivity extends AppCompatActivity {
    private ShopsService mShopService;
    private ShopAdapter mAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cityselection);
        Objects.requireNonNull(getSupportActionBar()).hide();
        // создаем объект для создания и управления версиями БД
        // TODO: 19.09.2019 перемістити код у аплікейшн клас

        mShopService = new ShopsService(this);

        loadData();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView textViewTel = (TextView) view.findViewById(R.id.telephone);
                        TextView textViewAddress = (TextView) view.findViewById(R.id.address);
                        Intent intent = new Intent(getApplicationContext(),DialogActivity.class);
                        intent.putExtra("address",textViewAddress.getText().toString());
                        intent.putExtra("tel",textViewTel.getText().toString());
                        intent.putExtra("shopId", "566554564"); //todo
                        //mShopService.getShopIdByPosition(); //або
                        //mShopService.getShop(position);
                        //mShopService.getAllItems().get(position).getId();
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        TextView textViewTel = (TextView) view.findViewById(R.id.telephone);
                         dial(textViewTel.getText().toString());
                    }
                })
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShopAdapter(this, mShopService.getAllItems());
        recyclerView.setAdapter(mAdapter);
        //заповнення бази тестовими даними
        mShopService.saveToBaseDefaultData();

    }


    private void loadData() {
        NetworkService.getInstance()
                .getJSONApi()
                .getShops()
                .enqueue(new Callback<List<Shop>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Shop>> call, @NonNull Response<List<Shop>> response) {
                        List<Shop> shops = response.body();
                        if (shops != null) {
                            mShopService.saveToBaseData(shops);
                            mAdapter.swapCursor(mShopService.getAllItems());
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
                        mShopService.saveToBaseDefaultData();
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Завантаження DefaultData  - ОК!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }







    // виклик дзвінка
    public void dial(String tel) {
        Uri uri = Uri.parse(String.format("tel:%s", tel));
        startActivity(new Intent(Intent.ACTION_DIAL, uri));
    }
}
