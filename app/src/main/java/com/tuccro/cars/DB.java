package com.tuccro.cars;


public class DB {

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
}
