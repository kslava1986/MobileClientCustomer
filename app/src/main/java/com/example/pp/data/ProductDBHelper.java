package com.example.pp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ProductDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "productlist.db";
    public static final int DATABASE_VERSION = 1;

    public ProductDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProductContract.ProductEntry.SQL_CREATE_PRODUCTLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ProductContract.ProductEntry.SQL_DROP_PRODUCTLIST_TABLE);
        onCreate(db);
    }
}
