package com.tuccro.cars.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.tuccro.cars.database.DBUtils.generateUpdateBrandString;
import static com.tuccro.cars.database.DBUtils.generateUpdateEngineString;
import static com.tuccro.cars.database.DBUtils.generateUpdateModelString;

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

    public void updateBrand(int idBrand, String name) {
        String query = generateUpdateBrandString(idBrand, name);
        db.execSQL(query);
    }

    public void deleteBrand(int id) {
        db.execSQL(FOREIGN_KEYS_ON);
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

    public void updateModel(int idModel, int idBrand, String name, int startYear, int endYear) {

        String query = generateUpdateModelString(idModel, idBrand, name, startYear, endYear);
        db.execSQL(query);
    }

    public void addModel(int idBrand, String name, int startYear) {
        ContentValues cv = new ContentValues();
        cv.put(MODEL_BRAND_ID, idBrand);
        cv.put(MODEL_NAME, name);
        cv.put(MODEL_START_YEAR, startYear);
        db.insert(DB_TABLE_MODEL, null, cv);
    }

    public void deleteModel(int id) {
        db.execSQL(FOREIGN_KEYS_ON);
        db.delete(DB_TABLE_MODEL, MODEL_ID + " = " + id, null);
    }

    public void addEngine(int idModel, String name) {
        ContentValues cv = new ContentValues();
        cv.put(ENGINE_MODEL_ID, idModel);
        cv.put(ENGINE_NAME, name);
        db.insert(DB_TABLE_ENGINE, null, cv);
    }

    public void updateEngine(int idEngine, int idModel, String name) {

        String query = generateUpdateEngineString(idEngine, idModel, name);
        db.execSQL(query);
    }

    public void deleteEngine(int id) {
        db.delete(DB_TABLE_ENGINE, ENGINE_ID + " = " + id, null);
    }

    public Cursor getAllBrands() {
        return db.query(DB_TABLE_BRAND, null, null, null, null, null, BRAND_NAME);
    }

    public Cursor getAllModels() {
        return db.query(DB_TABLE_MODEL, null, null, null, null, null, MODEL_NAME);
    }


    public Cursor getAllEngines() {
        return db.query(DB_TABLE_ENGINE, null, null, null, null, null, ENGINE_NAME);
    }

    public Cursor getAllBrandModels(int idBrand) {
        return db.query(DB_TABLE_MODEL, null, MODEL_BRAND_ID + "=?",
                new String[]{String.valueOf(idBrand)}, null, null, MODEL_NAME);
    }

    public Cursor getAllModelEngines(int idModel) {
        return db.query(DB_TABLE_ENGINE, null, ENGINE_MODEL_ID + "=?",
                new String[]{String.valueOf(idModel)}, null, null, ENGINE_NAME);
    }
}
