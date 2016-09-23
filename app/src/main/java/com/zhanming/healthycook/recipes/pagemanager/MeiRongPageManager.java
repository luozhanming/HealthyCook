package com.zhanming.healthycook.recipes.pagemanager;

import com.zhanming.healthycook.beans.Catalogue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanming on 2016/9/23.
 */
public class MeiRongPageManager {

    public static final int EACH_LOAD_PAGE_COUNT = 10;
    private static MeiRongPageManager instance;

    public static final int ID_YANGYAN = 2;
    public static final int ID_PAIDU = 3;
    public static final int ID_MEIBAI = 4;
    public static final int ID_KANGZHOU = 5;
    public static final int ID_QUDOU = 7;
    public static final int ID_RUNFU = 8;
    public static final int ID_BAOSHI = 9;

    public static final String KEY_YANGYAN = "yangyan";
    public static final String KEY_PAIDU = "paidu";
    public static final String KEY_MEIBAI = "meibai";
    public static final String KEY_KANGZHOU = "kangzhou";
    public static final String KEY_QUDOU = "qudou";
    public static final String KEY_RUNFU = "runfu";
    public static final String KEY_BAOSHI = "baoshi";

    private Map<String, Catalogue> catalogues;

    private MeiRongPageManager() {
        //从数据库中获取表
    }

    public static MeiRongPageManager getInstance() {
        if (instance == null) {
            synchronized (MeiRongPageManager.class) {
                instance = new MeiRongPageManager();
            }
        }
        return instance;
    }


}
