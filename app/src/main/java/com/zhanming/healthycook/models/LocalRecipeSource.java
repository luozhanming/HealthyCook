package com.zhanming.healthycook.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanming on 2016/10/4.
 */
public class LocalRecipeSource implements IRecipeSource {


    private DBHelper mHelper;

    public LocalRecipeSource(Context context) {
        mHelper = new DBHelper(context);
    }

    @Override
    public boolean addRecipes(List<Recipe> recipes) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        if (recipes == null) {
            return false;
        }
        db.beginTransaction();
        for (Recipe recipe : recipes) {
            ContentValues values = new ContentValues();
            values.put(DBHelper.FEILD_COLLECTION_ID, recipe.getId());
            values.put(DBHelper.FEILD_COLLECTION_NAME, recipe.getName());
            values.put(DBHelper.FEILD_COLLECTION_KEYWORD, recipe.getKey());
            values.put(DBHelper.FEILD_COLLECTION_IMG, recipe.getImgUrl());
            values.put(DBHelper.FEILD_COLLECTION_DESCRIPTION, recipe.getDescription());
            values.put(DBHelper.FEILD_COLLECTION_MSG, recipe.getMessage());
            db.insert(DBHelper.TABLE_COLLECTION, null, values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return true;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        if (recipe == null) {
            return false;
        }
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(DBHelper.FEILD_COLLECTION_ID, recipe.getId());
        values.put(DBHelper.FEILD_COLLECTION_NAME, recipe.getName());
        values.put(DBHelper.FEILD_COLLECTION_KEYWORD, recipe.getKey());
        values.put(DBHelper.FEILD_COLLECTION_IMG, recipe.getImgUrl());
        values.put(DBHelper.FEILD_COLLECTION_DESCRIPTION, recipe.getDescription());
        values.put(DBHelper.FEILD_COLLECTION_MSG, recipe.getMessage());
        db.insert(DBHelper.TABLE_COLLECTION, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return true;
    }

    @Override
    public boolean deleteRecipe(Recipe recipe) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int deleteRow = db.delete(DBHelper.TABLE_COLLECTION, DBHelper.FEILD_COLLECTION_ID + "=?"
                , new String[]{recipe.getId()});
        if (deleteRow > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRecipes() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int deleteRow = db.delete(DBHelper.TABLE_COLLECTION, null, null);
        if (deleteRow > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Recipe> getRecipes() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_COLLECTION
                , new String[]{DBHelper.FEILD_COLLECTION_ID
                        , DBHelper.FEILD_COLLECTION_NAME
                        , DBHelper.FEILD_COLLECTION_DESCRIPTION
                        , DBHelper.FEILD_COLLECTION_KEYWORD
                        , DBHelper.FEILD_COLLECTION_IMG
                        , DBHelper.FEILD_COLLECTION_MSG}
                , null, null, null, null, null);
        List<Recipe> recipes = new ArrayList<>();
        while (cursor.moveToNext()) {
            Recipe recipe = new Recipe();
            recipe.setId(cursor.getString(0));
            recipe.setName(cursor.getString(1));
            recipe.setDescription(cursor.getString(2));
            recipe.setKey(cursor.getString(3));
            recipe.setImgUrl(cursor.getString(4));
            recipe.setMessage(cursor.getString(5));
            recipes.add(recipe);
        }
        cursor.close();
        db.close();
        return recipes;
    }

    @Override
    public Recipe getRecipe(String name) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_COLLECTION
                , new String[]{DBHelper.FEILD_COLLECTION_ID
                        , DBHelper.FEILD_COLLECTION_NAME
                        , DBHelper.FEILD_COLLECTION_DESCRIPTION
                        , DBHelper.FEILD_COLLECTION_KEYWORD
                        , DBHelper.FEILD_COLLECTION_IMG
                        , DBHelper.FEILD_COLLECTION_MSG}
                , DBHelper.FEILD_COLLECTION_NAME + "=?"
                , new String[]{name}
                , null, null, null);
        cursor.moveToNext();
        Recipe recipe = new Recipe();
        recipe.setId(cursor.getString(0));
        recipe.setName(cursor.getString(1));
        recipe.setDescription(cursor.getString(2));
        recipe.setKey(cursor.getString(3));
        recipe.setImgUrl(cursor.getString(4));
        recipe.setMessage(cursor.getString(5));
        return recipe;
    }
}
