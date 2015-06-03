package com.tuccro.cars.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DB implements IDBStrings {

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

    public void addBrand(String name) {
        ContentValues cv = new ContentValues();
        cv.put(BRAND_NAME, name);
        db.insert(DB_TABLE_BRAND, null, cv);
    }

    public void deleteBrand(int id) {
        db.delete(DB_TABLE_BRAND, BRAND_ID + " = " + id, null);
    }

    public void addModel(int idBrand, String name, int startYear, int endYear) {
        ContentValues cv = new ContentValues();
        cv.put(MODEL_BRAND_ID, idBrand);
        cv.put(MODEL_NAME, name);
        cv.put(MODEL_START_YEAR, startYear);
        cv.put(MODEL_END_YEAR, endYear);
        db.insert(DB_TABLE_MODEL, null, cv);
    }

    public void addModel(int idBrand, String name, int startYear) {
        ContentValues cv = new ContentValues();
        cv.put(MODEL_BRAND_ID, idBrand);
        cv.put(MODEL_NAME, name);
        cv.put(MODEL_START_YEAR, startYear);
        db.insert(DB_TABLE_MODEL, null, cv);
    }

    public void deleteModel(int id) {
        db.delete(DB_TABLE_MODEL, MODEL_ID + " = " + id, null);
    }

    public void addEngine(int idModel, String name) {
        ContentValues cv = new ContentValues();
        cv.put(ENGINE_MODEL_ID, idModel);
        cv.put(ENGINE_NAME, name);
        db.insert(DB_TABLE_ENGINE, null, cv);
    }

    public void deleteEngine(int id) {
        db.delete(DB_TABLE_ENGINE, ENGINE_ID + " = " + id, null);
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
