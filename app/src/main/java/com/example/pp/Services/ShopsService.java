package com.example.pp.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pp.data.ShopContract;
import com.example.pp.data.ShopDBHelper;
import com.example.pp.models.Shop;

import java.util.List;

import static com.example.pp.data.ShopContract.ShopEntry.TABLE_NAME;

public class ShopsService {
    private SQLiteDatabase mDatabase;

    public ShopsService(Context context) {
        ShopDBHelper dbHelper = new ShopDBHelper(context);
        this.mDatabase = dbHelper.getWritableDatabase();
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
        //mAdapter.swapCursor(getAllItems());
    }

    //заполвнив тестовими даними
    private void cleanDBShop() {
        mDatabase.execSQL(ShopContract.ShopEntry.SQL_DROP_SHOPLIST_TABLE);
        mDatabase.execSQL(ShopContract.ShopEntry.SQL_CREATE_SHOPLIST_TABLE);
    }

    public void saveToBaseData(List<Shop> shops) {
        cleanDBShop();
        for (Shop shop : shops) {
            addItem(shop.getName(), shop.getTel());
        }
    }

    public void saveToBaseDefaultData() {
        cleanDBShop();
        addItem("Київська,88", "0988467236");
        addItem("Бульвар Польський 13а", "0983995114");
        addItem("Хлібна,39/19", "0971146295");
        addItem("М-н Станишівський,3/2", "0971084756");
        addItem("Вільський Шлях,14", "0985660818");
        addItem("Івана Мазепи,5", "0985751069");
        addItem("ГАРЯЧА ЛІНІЯ", "0800505084");
    }

    public Cursor getAllItems() {

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
    //this.mDatabase = dbHelper.getReadableDatabase();
    //
    //        List<Product> products = new ArrayList<>();
    //
    //        Cursor cursor = mDatabase.query(
    //                TABLE_NAME,
    //                null,
    //                null,
    //                null,
    //                null,
    //                null,
    //                null
    //        );
    //
    //        while (cursor.moveToNext()){
    //            int indexId = cursor.getColumnIndex(ProductContract.ProductEntry._ID);
    //            int indexName = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME);
    //            int indexDescription = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_DESCRIPTION);
    //            int indexPrice = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE);
    //
    //
    //
    //            Product product = new Product();
    //            product.setId(cursor.getLong(indexId));
    //            product.setName(cursor.getString(indexName));
    //            product.setDescription(cursor.getString(indexDescription));
    //            product.setPrice(cursor.getLong(indexPrice));
    //
    //            products.add(product);
    //        }
    //
    //        mDatabase.close();
    //
    //        return products;//
}
