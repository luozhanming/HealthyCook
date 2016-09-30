package com.zhanming.healthycook.recipes;

import android.content.Context;

import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.recipes.pagemanager.PageManager;

/**
 * Created by zhanming on 2016/9/23.
 */
public class RecipeListPresenter implements RecipesContract.Presenter {

    private RecipesContract.PageView mView;
    private Catalogue mCatalogue;



    public RecipeListPresenter(Context context, RecipesContract.PageView view,String group, String calogue){
        mView = view;
        mCatalogue = PageManager.getInstance(context).getCatalogue(group,calogue);
    }


    public void loadInitialRecipe(){

    };

    @Override
    public void loadMoreRecipesByPullDown() {

    }

    @Override
    public void loadMoreRecipesByPullUp() {

    }

    @Override
    public void changePage(int page) {

    }

    @Override
    public void start() {
        loadInitialRecipe();
    }

    @Override
    public void destroy() {
        mView = null;
    }
}
