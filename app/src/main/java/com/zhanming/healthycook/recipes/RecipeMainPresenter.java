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

    private RecipesContract.ActivityView mView;
    private Map<String, Catalogue> mCatalogues;
    private String[] mCatalogueName;
    private String mClass;

    public RecipeMainPresenter(Context context, RecipesContract.ActivityView view, String className) {
        //   mCatalogues = mPageManager.getLowCatalogues(className);
        mView = view;
        mClass = className;
        mCatalogues = PageManager.getInstance(context).getLowCatalogues(className);
        switch (mClass) {
            case "美容":
                setCatalogueName(context,R.array.meiRongCatalogue);
                break;
            case "减肥":
                setCatalogueName(context,R.array.jianFeiCatalogue);
                break;
            case "保健养生":
                setCatalogueName(context,R.array.baoJianYangSheng);
                break;
            case "餐时":
                setCatalogueName(context,R.array.canShi);
                break;
            case "调养":
                setCatalogueName(context,R.array.tiaoYanG);
                break;
            case "肠胃消化":
                setCatalogueName(context,R.array.changWeiXiaoHua);
                break;
            case "皮肤":
                setCatalogueName(context,R.array.piFu);
                break;
            case "人群":
                setCatalogueName(context,R.array.renQun);
                break;
            case "其他":
                setCatalogueName(context,R.array.qiTa);
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
        mView = null;
    }

    private void setCatalogueName(Context context, int resId) {
        mCatalogueName = context.getResources().getStringArray(resId);
    }
}
