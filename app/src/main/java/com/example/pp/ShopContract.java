package com.example.pp;

import android.provider.BaseColumns;

public class ShopContract {
    private ShopContract(){}

    public static final class ShopEntry implements BaseColumns{
        public static final String TABLE_NAME = "shopList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TEL = "tel";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
