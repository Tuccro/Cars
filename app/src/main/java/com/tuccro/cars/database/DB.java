package com.tuccro.cars.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DB {

    private static final String DB_NAME = "cars.sqlite";
    private static final int DB_VERSION = 1;

    private final Context context;


    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DB(Context context) {
        this.context = context;
    }

    public void open() {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (dbHelper != null) dbHelper.close();
    }
}
