package com.tuccro.cars.database;

public interface IDBStrings {

    String CREATE_TABLE_BRAND = "CREATE TABLE `BRAND` (\n" +
            "\t`id_brand`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`name`\tTEXT NOT NULL UNIQUE\n" +
            ");";

    String CREATE_TABLE_ENGINE = "CREATE TABLE `ENGINE` (\n" +
            "\t`id_engine`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`id_model`\tINTEGER NOT NULL,\n" +
            "\t`name`\tTEXT,\n" +
            "\tFOREIGN KEY (id_model) REFERENCES MODEL(id_model)\n" +
            ");\n";

    String CREATE_TABLE_MODEL = "CREATE TABLE `MODEL` (\n" +
            "\t`id_model`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`id_brand`\tINTEGER NOT NULL,\n" +
            "\t`name`\tTEXT NOT NULL,\n" +
            "\t`start_year`\tINTEGER,\n" +
            "\t`end_year`\tINTEGER,\n" +
            "\tFOREIGN KEY (id_brand) REFERENCES BRAND(id_brand)\n" +
            ");";

    String DB_TABLE_BRAND = "BRAND";
    String DB_TABLE_MODEL = "MODEL";
    String DB_TABLE_ENGINE = "ENGINE";

    String DB_NAME = "cars.sqlite";
    int DB_VERSION = 1;

    String BRAND_ID = "id_brand";
    String BRAND_NAME = "name";

    String MODEL_ID = "id_model";
    String MODEL_BRAND_ID = "id_brand";
    String MODEL_NAME = "name";
    String MODEL_START_YEAR = "start_year";
    String MODEL_END_YEAR = "end_year";

    String ENGINE_ID = "id_engine";
    String ENGINE_MODEL_ID = "id_model";
    String ENGINE_NAME = "name";

}
