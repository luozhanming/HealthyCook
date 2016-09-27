package com.zhanming.healthycook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhanming on 2016/9/23.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_CATALOGUE = "catalogue.db";

    public static final String TABLE_CATALOGUE = "Catalogue";

    public static final String FEILD_GROUP = "_group";
    public static final String FEILD_ID = "id";
    public static final String FEILD_NAME = "Name";
    public static final String FEILD_PAGE = "Page";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_CATALOGUE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
