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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pp.models.Shop;
import com.example.pp.models.Store;
import com.example.pp.models.UserInMemoryStore;
import static com.example.pp.ShopContract.ShopEntry.TABLE_NAME;

public class CityselectionActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private ShopAdapter mAdapter;
    private final Store<Shop> store = new UserInMemoryStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cityselection);

        // создаем объект для создания и управления версиями БД
        // TODO: 19.09.2019 перемістити код у аплікейшн клас
        ShopDBHelper dbHelper = new ShopDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        //--
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        // слухач RecyclerView
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
//                        Toast toast = Toast.makeText(getApplicationContext(),
//                                "Пора покормить кота!", Toast.LENGTH_SHORT);
//                        toast.show();
                        TextView textView = (TextView)view.findViewById(R.id.telephone);
                            dial(textView.getText().toString());
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShopAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        //заповнення бази тестовими даними
        saveToBaseTestData();
        // TODO: 12.09.2019 Запуск завантаження списку магазинів у окремому потоці за допомогою Retrofit
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
//заполвнив тестовими даними
    private void saveToBaseTestData(){
        mDatabase.execSQL(ShopContract.ShopEntry.SQL_DROP_SHOPLIST_TABLE);
        mDatabase.execSQL(ShopContract.ShopEntry.SQL_CREATE_SHOPLIST_TABLE);
          addItem("Київська,88","0988467236");
          addItem("Бульвар Польський 13а","0983995114");
          addItem("Хлібна,39/19","0971146295");
          addItem("М-н Станишівський,3/2","0971084756");
          addItem("Вільський Шлях,14","0985660818");
          addItem("Івана Мазепи,5","0985660818");
    }

    // виклик дзвінка
    public void dial(String tel) {
        Uri uri = Uri.parse(String.format("tel:%s", tel));
        startActivity(new Intent(Intent.ACTION_DIAL, uri));
    }
}
