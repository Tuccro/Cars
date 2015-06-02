package com.tuccro.cars.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {


    public static final String CREATE_TABLE_BRAND = "CREATE TABLE `BRAND` (\n" +
            "\t`id_brand`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`name`\tTEXT NOT NULL\n" +
            ");";

    public static final String CREATE_TABLE_ENGINE = "CREATE TABLE `ENGINE` (\n" +
            "\t`id_engine`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`id_model`\tINTEGER NOT NULL,\n" +
            "\t`name`\tTEXT\n" +
            ");";

    public static final String CREATE_TABLE_MODEL = "CREATE TABLE `MODEL` (\n" +
            "\t`id_model`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`id_brand`\tINTEGER NOT NULL,\n" +
            "\t`name`\tTEXT NOT NULL,\n" +
            "\t`start_year`\tINTEGER,\n" +
            "\t`end_year`\tINTEGER\n" +
            ");";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BRAND);
        db.execSQL(CREATE_TABLE_ENGINE);
        db.execSQL(CREATE_TABLE_MODEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}