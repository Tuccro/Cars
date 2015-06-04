package com.tuccro.cars.database;

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
        insertModels(db);
        insertEngines(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void insertBrands(SQLiteDatabase db) {
        db.execSQL(INIT_BRANDS_TABLE);
    }

    private void insertModels(SQLiteDatabase db) {
        db.execSQL(INIT_MODELS_TABLE);
    }

    private void insertEngines(SQLiteDatabase db) {
        db.execSQL(INIT_ENGINES_TABLE);
    }
}