package com.zhanming.healthycook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhanming on 2016/9/23.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_CATALOGUE = "catalogue.db";

    public static final String TABLE_MEIRONG = "meiRong";
    public static final String MEIRONG_ID = "id";
    public static final String MEIRONG_NAME = "name";
    public static final String MEIRONG_PAGE = "currentPage";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_CATALOGUE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_MEIRONG + " ( " +
                MEIRONG_ID + " integer, " +
                MEIRONG_NAME + " text, "
                +MEIRONG_PAGE + " integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
