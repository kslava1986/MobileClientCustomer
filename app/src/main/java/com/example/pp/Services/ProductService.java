package com.example.pp.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pp.data.ProductContract;
import com.example.pp.data.ProductDBHelper;
import com.example.pp.models.Product;


import java.util.ArrayList;
import java.util.List;

import static com.example.pp.data.ProductContract.ProductEntry.TABLE_NAME;


public class ProductService {
    private ProductDBHelper dbHelper;
    private SQLiteDatabase mDatabase;

    public ProductService(Context context) {
        this.dbHelper = new ProductDBHelper(context);
//        this.mDatabase = dbHelper.getWritableDatabase();
    }

    public void addItem(String name, String description, float price) {
        this.mDatabase = dbHelper.getWritableDatabase();
        if (name.trim().length() == 0) return;
        // создаем объект для данных
        ContentValues contentValues = new ContentValues();
        // получаем данные из полей ввода
        contentValues.put(ProductContract.ProductEntry.COLUMN_NAME, name);
        contentValues.put(ProductContract.ProductEntry.COLUMN_DESCRIPTION, description);
        contentValues.put(ProductContract.ProductEntry.COLUMN_PRICE, price);
        // подключаемся к БД
        mDatabase.insert(TABLE_NAME, null, contentValues);
        //mAdapter.swapCursor(getAllItems());
        mDatabase.close();
    }

    //заполвнив тестовими даними
    private void cleanDBProduct() {
        this.mDatabase = dbHelper.getWritableDatabase();
        mDatabase.execSQL(ProductContract.ProductEntry.SQL_DROP_PRODUCTLIST_TABLE);
        mDatabase.execSQL(ProductContract.ProductEntry.SQL_CREATE_PRODUCTLIST_TABLE);
        mDatabase.close();
    }

    public void saveToBaseData(List<Product> products) {
        cleanDBProduct();
        for (Product product : products) {
            addItem(product.getName(), product.getDescription(), product.getPrice());
        }
    }

    public void saveToBaseDefaultData() {
        cleanDBProduct();
        addItem("Піца1","опис1, опис1опис1опис1опис1опис1опис1опис1опис1",15.89f);
        addItem("Піца2","опис1, опис1опис1опис1опис1опис1опис1опис1опис1",115.89f);
    }

    public List<Product> getAllItems() {

        this.mDatabase = dbHelper.getReadableDatabase();

        List<Product> products = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            int indexId = cursor.getColumnIndex(ProductContract.ProductEntry._ID);
            int indexName = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME);
            int indexDescription = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_DESCRIPTION);
            int indexPrice = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE);



            Product product = new Product();
            product.setId(cursor.getLong(indexId));
            product.setName(cursor.getString(indexName));
            product.setDescription(cursor.getString(indexDescription));
            product.setPrice(cursor.getLong(indexPrice));

            products.add(product);
        }

        mDatabase.close();

        return products;
    }
}
