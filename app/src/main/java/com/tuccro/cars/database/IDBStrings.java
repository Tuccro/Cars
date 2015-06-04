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

    String INIT_BRANDS_TABLE = "INSERT INTO `BRAND`(name) VALUES\n" +
            "('Audi'),\n" +
            "('BMW'),\n" +
            "('Mercedes'),\n" +
            "('Ford'),\n" +
            "('Daewoo'),\n" +
            "('Kia'),\n" +
            "('Skoda'),\n" +
            "('Seat'),\n" +
            "('VW'),\n" +
            "('Chevrolet'),\n" +
            "('Porsche');";

    String INIT_MODELS_TABLE = "INSERT INTO `MODEL`(id_brand, name, start_year, end_year) VALUES\n" +
            "(1, 'A4', 2000, 2015),\n" +
            "(1, 'A6', 1995, 2000),\n" +
            "(2, '3-Series E30', 1975, 1983),\n" +
            "(2, '3-Series E36', 1983, 1989),\n" +
            "(2, '5-Series E39', 1992, 1999),\n" +
            "(3, 'G-Class', 1965, 2015),\n" +
            "(3, 'S-Class W221', 2001, 2013),\n" +
            "(4, 'Fiesta Mk1', 1975, 1983),\n" +
            "(5, 'Lanos', 1994, 2014),\n" +
            "(7, 'Octavia', 2000, 2003),\n" +
            "(8, 'Ibiza', 1985, 1992),\n" +
            "(9, 'Golf', 1992, 1997),\n" +
            "(10, 'Aveo', 2003, 2012),\n" +
            "(11, 'Cayenne', 2001, 2007);";

    String INIT_ENGINES_TABLE = "INSERT INTO `ENGINE`(id_model, name) VALUES\n" +
            "(1, '1.8'),\n" +
            "(1, '1.8D'),\n" +
            "(1, '1.8TSI'),\n" +
            "(2, '2.0D'),\n" +
            "(2, '2.0'),\n" +
            "(3, '2.4D'),\n" +
            "(4, '3.0'),\n" +
            "(5, '4.0'),\n" +
            "(5, '4.5'),\n" +
            "(5, '2.2'),\n" +
            "(6, '5.5 AMG'),\n" +
            "(6, '3.0 Diesel'),\n" +
            "(7, '500'),\n" +
            "(7, '400 Hybrid'),\n" +
            "(8, '1.0'),\n" +
            "(9, '1.5 SOHC'),\n" +
            "(9, '1.6 DOHC'),\n" +
            "(9, '1.4 MeMZ'),\n" +
            "(10, '1.6'),\n" +
            "(10, '2.0'),\n" +
            "(10, '1.8TSI'),\n" +
            "(11, '1.4'),\n" +
            "(11, '2.0'),\n" +
            "(12, '1.4'),\n" +
            "(12, '2.0'),\n" +
            "(12, '1.8'),\n" +
            "(13, '1.5'),\n" +
            "(13, '1.6'),\n" +
            "(14, '3.0'),\n" +
            "(14, '3.0 Hybrid'),\n" +
            "(14, '4.5 S Turbo');";

}
