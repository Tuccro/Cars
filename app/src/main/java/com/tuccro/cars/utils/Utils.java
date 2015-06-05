package com.tuccro.cars.utils;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.tuccro.cars.core.Brand;
import com.tuccro.cars.core.Engine;
import com.tuccro.cars.core.Item;
import com.tuccro.cars.core.Model;
import com.tuccro.cars.database.IDBStrings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public abstract class Utils implements IDBStrings {

    private static int currentYear = 0;

    public static ArrayList<Item> getBrandsFromDBCursor(Cursor cursor) {
        ArrayList<Item> brands = new ArrayList<>(cursor.getCount());
        if (cursor.getCount() < 1) return brands;

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

    public static ArrayList<Item> getModelsFromDBCursor(Cursor cursor) {
        ArrayList<Item> models = new ArrayList<>(cursor.getCount());
        if (cursor.getCount() < 1) return models;

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

    public static ArrayList<Item> getEnginesFromDBCursor(Cursor cursor) {
        ArrayList<Item> engines = new ArrayList<>(cursor.getCount());
        if (cursor.getCount() < 1) return engines;

        int id;
        int idModel;
        String name;

        cursor.moveToFirst();
        do {
            id = cursor.getInt(cursor.getColumnIndex(ENGINE_ID));
            idModel = cursor.getInt(cursor.getColumnIndex(ENGINE_MODEL_ID));
            name = cursor.getString(cursor.getColumnIndex(ENGINE_NAME));

            engines.add(new Engine(id, idModel, name));
        } while (cursor.moveToNext());

        return engines;
    }

    public static ArrayAdapter<String> getItemsArrayAdapter(Context context, List list) {
        Item item;
        String brands[] = new String[list.size()];
        for (int k = 0; k < brands.length; k++) {
            item = (Item) list.get(k);
            brands[k] = item.getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, brands);

        return adapter;
    }

    public static int getCurrentYear() {
        if (currentYear == 0) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault(),
                    Locale.getDefault());
            calendar.setTime(new Date());
            currentYear = calendar.get(Calendar.YEAR);
        }
        return currentYear;
    }
}
