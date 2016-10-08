package com.zhanming.healthycook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhanming.healthycook.utils.MyStringBuffer;

/**
 * Created by zhanming on 2016/10/6.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String TABLE_CATALOGUE = "Catalogue";
    public static final String FEILD_GROUP = "_group";
    public static final String FEILD_ID = "id";
    public static final String FEILD_NAME = "Name";
    public static final String FEILD_PAGE = "Page";

    public static final String DB_COLLECTION = "Collection.db";
    public static final String TABLE_COLLECTION = "Collection";
    public static final String FEILD_COLLECTION_ID = "ID";
    public static final String FEILD_COLLECTION_NAME = "Name";
    public static final String FEILD_COLLECTION_DESCRIPTION = "Description";
    public static final String FEILD_COLLECTION_IMG = "Image";
    public static final String FEILD_COLLECTION_KEYWORD = "Keyword";
    public static final String FEILD_COLLECTION_MSG = "Message";

    public DBHelper(Context context) {
        super(context, DB_COLLECTION, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        MyStringBuffer msb = new MyStringBuffer();
        String createCollection = msb.appendWithSpace("create table").appendWithSpace(TABLE_COLLECTION)
                .append("(")
                .appendWithSpace(FEILD_COLLECTION_ID).appendWithSpace("text primary key,")
                .appendWithSpace(FEILD_COLLECTION_NAME).appendWithSpace("text,")
                .appendWithSpace(FEILD_COLLECTION_DESCRIPTION).appendWithSpace("text,")
                .appendWithSpace(FEILD_COLLECTION_KEYWORD).appendWithSpace("text,")
                .appendWithSpace(FEILD_COLLECTION_IMG).appendWithSpace("text,")
                .appendWithSpace(FEILD_COLLECTION_MSG).appendWithSpace("text").append(")")
                .string();
        db.execSQL(createCollection);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
