package com.example.pp.data;

import android.provider.BaseColumns;

public class ProductContract {

    public ProductContract() {
    }

    public static final class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "productList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PRICE = "price";
        // запрос створення таблиці
        public static final String SQL_CREATE_PRODUCTLIST_TABLE = "CREATE TABLE " +
                ProductEntry.TABLE_NAME + " (" +
                ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ProductEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ProductEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                ProductEntry.COLUMN_PRICE + " REAL NOT NULL" +
                ");";
        // запрос видалення таблиці
        public static final String SQL_DROP_PRODUCTLIST_TABLE = "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME;

    }
}

