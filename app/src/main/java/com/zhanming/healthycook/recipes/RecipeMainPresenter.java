package com.zhanming.healthycook.recipes;

import android.content.Context;

import com.zhanming.healthycook.R;
import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.recipes.pagemanager.PageManager;

import java.util.Map;

/**
 * Created by zhanming on 2016/9/27.
 */
public class RecipeMainPresenter implements RecipesContract.ActivityPresenter {

    private PageManager mPageManager;
    private RecipesContract.ActivityView mView;
    private Map<String, Catalogue> mCatalogues;
    private String[] mCatalogueName;
    private String mClass;

    public RecipeMainPresenter(Context context, RecipesContract.ActivityView view, String className) {
        mPageManager = PageManager.getInstance(context);
     //   mCatalogues = mPageManager.getLowCatalogues(className);
        mView = view;
        mClass = className;
        switch (mClass) {
            case "MeiRong":
                mCatalogueName = context.getResources().getStringArray(R.array.jianFeiCatalogue);
                break;
            case "JianFei":
                mCatalogueName = context.getResources().getStringArray(R.array.jianFeiCatalogue);
                break;
            case "BaoJianYangSheng":
                mCatalogueName = context.getResources().getStringArray(R.array.baoJianYangSheng);
                break;
        }
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.initFragments(mCatalogueName);
    }

    @Override
    public void destroy() {

    }
}
