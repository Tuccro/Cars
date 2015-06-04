package com.tuccro.cars.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper implements IDBStrings {


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BRAND);
        db.execSQL(CREATE_TABLE_ENGINE);
        db.execSQL(CREATE_TABLE_MODEL);

        insertBrands(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void insertBrands(SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        String brands[] = {"Audi", "BMW", "Mercedes","Porsche","Honda","Skoda"};
        for (String name:brands){
            cv.put(BRAND_NAME, name);
            db.insert(DB_TABLE_BRAND, null, cv);
        }
    }
}