package com.example.pp;

import android.provider.BaseColumns;

public class ShopContract {
    private ShopContract(){}
    public static final class ShopEntry implements BaseColumns{
        public static final String TABLE_NAME = "shopList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TEL = "tel";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        // запрос створення таблиці
        public static final String SQL_CREATE_SHOPLIST_TABLE = "CREATE TABLE " +
                ShopEntry.TABLE_NAME + " (" +
                ShopEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ShopEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ShopEntry.COLUMN_TEL + " TEXT NOT NULL, " +
                ShopEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");";
        // запрос видалення таблиці
        public static final String SQL_DROP_SHOPLIST_TABLE = "DROP TABLE IF EXISTS " + ShopEntry.TABLE_NAME;

    }
}
