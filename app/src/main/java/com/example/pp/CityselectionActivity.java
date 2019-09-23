package com.example.pp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pp.models.Shop;
import com.example.pp.models.Store;
import com.example.pp.models.UserInMemoryStore;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.example.pp.ShopContract.ShopEntry.COLUMN_NAME;
import static com.example.pp.ShopContract.ShopEntry.COLUMN_TEL;
import static com.example.pp.ShopContract.ShopEntry.TABLE_NAME;

public class CityselectionActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private ShopAdapter mAdapter;
    // TODO: 12.09.2019 Замінити на об'єкти
    String s1 = "Київська,88       (0988467236)";
    String s2 = "Бульвар Польський 13а (0983995114)";
    String s3 = "Хлібна,39/19          (0971146295)";
    String s4 = "М-н Станишівський,3/2 (0986408197)";
    String s5 = "пр-т Миру,61/2        (0971084756)";
    String s6 = "Вільський Шлях,14     (0985660818)";
    String s7 = "Івана Мазепи,5        (0985660818)";
    String[] shops = {s1, s2, s3, s4, s5, s6, s7};
    List<Shop> shopsList = convertStringToShop(shops);


    private List<Shop> convertStringToShop(String[] shops) {
        List<Shop> shopsList = new ArrayList<>();
        for (int i = 0; i < shops.length; i++) {
            shopsList.add(new Shop(i, shops[i], ""));
        }
        return shopsList;
    }

    private final Store<Shop> store = new UserInMemoryStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cityselection);
        ImageButton buttonCall = findViewById(R.id.callButton);

        // создаем объект для создания и управления версиями БД
        // TODO: 19.09.2019 перемістити код у аплікейшн клас
        ShopDBHelper dbHelper = new ShopDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        //
//        addItem("Київська,88","0988467236");
//        addItem("Бульвар Польський 13а","0983995114");
//        addItem("Хлібна,39/19","0971146295");
//        addItem("М-н Станишівський,3/2","0971084756");
//        addItem("Вільський Шлях,14","0985660818");
//        addItem("Івана Мазепи,5","0985660818");

        //--
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShopAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial(v);
            }
        });
        // TODO: 12.09.2019 Запуск завантаження списку магазинів у окремому потоці за допомогою Retrofit
        // TODO: 12.09.2019 використати RecyclerViewAdapter замість ArrayAdapter
        // находим список
        ListView lvMain = (ListView) findViewById(R.id.shopsListView);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, shops);

        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);

        //додати onClickListener to adapter
        //call another screen
    }

    // метод привязується до кнопки
    public void dial(View v) {
        String toDial = "tel:0638237775";
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
    }

    public void addItem(String name, String tel) {
        if (name.trim().length() == 0) return;

        // создаем объект для данных
        ContentValues contentValues = new ContentValues();

        // получаем данные из полей ввода
        contentValues.put(ShopContract.ShopEntry.COLUMN_NAME, name);
        contentValues.put(ShopContract.ShopEntry.COLUMN_TEL, tel);

        // подключаемся к БД
        mDatabase.insert(TABLE_NAME, null, contentValues);
        mAdapter.swapCursor(getAllItems());
        //mEditTextName.getText().clear;    //после записи очищаем поле
    }

    protected Cursor getAllItems() {
        return mDatabase.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                ShopContract.ShopEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
