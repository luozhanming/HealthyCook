package com.zhanming.healthycook.recipes.pagemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.database.DBHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>提供Catalogue类实例</p>
 * <p>提供操作Catalogue类数据实体的增删减改</p>
 * Created by zhanming on 2016/9/23.
 */
public class PageManager implements IPageManager {

    public static final int EACH_LOAD_PAGE_COUNT = 10;
    private static PageManager instance;

    public static final String KEY_YANGYAN = "yangyan";
    public static final String KEY_PAIDU = "paidu";
    public static final String KEY_MEIBAI = "meibai";
    public static final String KEY_KANGZHOU = "kangzhou";
    public static final String KEY_QUDOU = "qudou";
    public static final String KEY_RUNFU = "runfu";
    public static final String KEY_BAOSHI = "baoshi";

    //  private Map<String, Catalogue> meiRongCatalogues;
    private Map<String, Map<String, Catalogue>> topCatalogues;
    private DBHelper mDBHelper;

    private PageManager(Context context) {
        //从数据库中获取表（程序上默认录好）
        mDBHelper = new DBHelper(context, DBHelper.DB_CATALOGUE, null, 1);
        topCatalogues = new HashMap<>();

    }

    public static PageManager getInstance(Context context) {
        if (instance == null) {
            synchronized (PageManager.class) {
                instance = new PageManager(context);
            }
        }
        return instance;
    }


    @Override
    public Map<String, Catalogue> getLowCatalogues(String group) {
        //保护性拷贝
        return new HashMap(topCatalogues.get(group));

    }

    @Override
    public Catalogue getCatalogue(String group, String catalogue) {
        return new Catalogue(topCatalogues.get(group).get(catalogue));
    }

    @Override
    public boolean updateCataloguePage(Catalogue cl) {
        Catalogue catalogue = topCatalogues.get(cl.getGroup()).get(cl.getName());
        catalogue.setPage(cl.getPage());
        //TO DO WITH 修改进数据库中
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.FEILD_PAGE, catalogue.getPage());
        int row = db.update(DBHelper.TABLE_CATALOGUE
                , values
                , DBHelper.FEILD_NAME + "=?"
                , new String[]{catalogue.getName()});
        if(row!=0){
            db.close();
            return true;
        }else{
            db.close();
            return false;
        }
    }
}
