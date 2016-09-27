package com.zhanming.healthycook.recipes.pagemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public static final String KEY_YANGYAN = "MeiYan";
    public static final String KEY_PAIDU = "PaiDu";
    public static final String KEY_MEIBAI = "MeiBai";
    public static final String KEY_KANGZHOU = "KangZhou";
    public static final String KEY_QUDOU = "QuDou";
    public static final String KEY_RUNFU = "RunFu";
    public static final String KEY_BAOSHI = "BaoShi";

    private SQLiteDatabase db;
    //  private Map<String, Catalogue> meiRongCatalogues;
    private Map<String, Map<String, Catalogue>> topCatalogues;
    private Context mContext;

    private PageManager(Context context) {
        //从数据库中获取表（程序上默认录好）
        topCatalogues = new HashMap<>();
        mContext = context;
        loadDBData(context);
    }

    private void loadDBData(Context context) {
        db = context.openOrCreateDatabase(context.getCacheDir() + "/" + "catalogue.db"
                , Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select _group,id,Name,Page from Catalogue",null);
        Map<String, Catalogue> lowCatalogues = new HashMap();
        while (cursor.moveToNext()) {
            String group = cursor.getString(0);
            int id = cursor.getInt(1);
            String name = cursor.getString(2);
            int page = cursor.getInt(3);
            Catalogue catalogue = new Catalogue(id, page, group, name);
            lowCatalogues.put(name, catalogue);
        }
        topCatalogues.put("MeiRong", lowCatalogues);
        db.close();
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
        db = mContext.openOrCreateDatabase(mContext.getCacheDir() + "/" + "catalogue.db"
                , Context.MODE_PRIVATE, null);
        Catalogue catalogue = topCatalogues.get(cl.getGroup()).get(cl.getName());
        catalogue.setPage(cl.getPage());
        //TO DO WITH 修改进数据库中
        ContentValues values = new ContentValues();
        values.put(DBHelper.FEILD_PAGE, catalogue.getPage());
        int row = db.update(DBHelper.TABLE_CATALOGUE
                , values
                , DBHelper.FEILD_NAME + "=?"
                , new String[]{catalogue.getName()});
        if (row != 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }
}
