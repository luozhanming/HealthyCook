package com.zhanming.healthycook.recipes.pagemanager;

import com.zhanming.healthycook.beans.Catalogue;

import java.util.Map;

/**
 * Created by zhanming on 2016/9/25.
 */
public interface IPageManager {
    Map<String,Catalogue> getLowCatalogues(String topCatalogueName);
    Catalogue getCatalogue(String topCatalogue,String lowCatalogue);
    boolean updateCataloguePage(Catalogue catalogue);
}
