package com.tuccro.cars.utils;

import android.database.Cursor;

import com.tuccro.cars.core.Brand;
import com.tuccro.cars.core.Engine;
import com.tuccro.cars.core.Model;
import com.tuccro.cars.database.IDBStrings;

import java.util.ArrayList;

public abstract class Utils implements IDBStrings {

    public static ArrayList<Brand> getBrandsFromDBCursor(Cursor cursor) {
        ArrayList<Brand> brands = new ArrayList<>();

        int id;
        String name;

        cursor.moveToFirst();
        do {
            id = cursor.getInt(cursor.getColumnIndex(BRAND_ID));
            name = cursor.getString(cursor.getColumnIndex(BRAND_NAME));

            brands.add(new Brand(id, name));
        } while (cursor.moveToNext());

        return brands;
    }

    public static ArrayList<Model> getModelsFromDBCursor(Cursor cursor) {
        ArrayList<Model> models = new ArrayList<>();

        int id;
        int brandId;
        String name;
        int startYear;
        int endYear;

        cursor.moveToFirst();
        do {
            id = cursor.getInt(cursor.getColumnIndex(MODEL_ID));
            brandId = cursor.getInt(cursor.getColumnIndex(MODEL_BRAND_ID));
            name = cursor.getString(cursor.getColumnIndex(MODEL_NAME));
            startYear = cursor.getInt(cursor.getColumnIndex(MODEL_START_YEAR));
            endYear = cursor.getInt(cursor.getColumnIndex(MODEL_END_YEAR));

            models.add(new Model(id, brandId, name, startYear, endYear));
        } while (cursor.moveToNext());

        return models;
    }

    public static ArrayList<Engine> getEnginesFromDBCursor(Cursor cursor) {
        ArrayList<Engine> brands = new ArrayList<>();

        int id;
        int idModel;
        String name;

        cursor.moveToFirst();
        do {
            id = cursor.getInt(cursor.getColumnIndex(ENGINE_ID));
            idModel = cursor.getInt(cursor.getColumnIndex(ENGINE_MODEL_ID));
            name = cursor.getString(cursor.getColumnIndex(ENGINE_NAME));

            brands.add(new Engine(id, idModel, name));
        } while (cursor.moveToNext());

        return brands;
    }

}
