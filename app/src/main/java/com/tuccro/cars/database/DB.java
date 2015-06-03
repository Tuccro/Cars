package com.tuccro.cars.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DB implements IDBStrings{

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

    public Cursor getAllBrands() {
        return db.query(DB_TABLE_BRAND, null, null, null, null, null, null);
    }


    public Cursor getAllModels() {
        return db.query(DB_TABLE_MODEL, null, null, null, null, null, null);
    }


    public Cursor getAllEngines() {
        return db.query(DB_TABLE_ENGINE, null, null, null, null, null, null);
    }
}
