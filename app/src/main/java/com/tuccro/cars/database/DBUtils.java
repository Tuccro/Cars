package com.tuccro.cars.database;


public abstract class DBUtils implements IDBStrings {

    static String generateUpdateBrandString(int id, String name) {
        return "UPDATE " + DB_TABLE_BRAND + "\n" +
                "SET " + BRAND_NAME + "='" + name +
                "'\n WHERE " + BRAND_ID + "=" + String.valueOf(id) + ";";
    }

    static String generateUpdateModelString(int idModel, int idBrand, String name, int startYear, int endYear) {

        return "UPDATE " + DB_TABLE_MODEL + "\n" +
                "SET " + MODEL_BRAND_ID + "=" + String.valueOf(idBrand) + ",\n" +
                MODEL_NAME + "='" + name + "',\n" +
                MODEL_START_YEAR + "=" + String.valueOf(startYear) + ",\n" +
                MODEL_END_YEAR + "=" + String.valueOf(endYear) + " \n" +
                "WHERE " + MODEL_ID + "=" + String.valueOf(idModel) + ";";
    }

    static String generateUpdateEngineString(int id, int idModel, String name) {
        return "UPDATE " + DB_TABLE_ENGINE + "\n" +
                "SET " + ENGINE_MODEL_ID + "=" + String.valueOf(idModel) + ",\n" +
                ENGINE_NAME + "='" + name + "'\n" +
                "WHERE " + ENGINE_ID + "=" + String.valueOf(id) + ";";
    }
}
