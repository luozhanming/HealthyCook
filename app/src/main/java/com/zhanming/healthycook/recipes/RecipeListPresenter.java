package com.zhanming.healthycook.recipes;

/**
 * Created by zhanming on 2016/9/23.
 */
public abstract class RecipeListPresenter implements RecipesContract.Presenter {

    private RecipesContract.PageView mView;


    public abstract void loadInitialRecipe();

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
        mView.showLoadingView();
        loadInitialRecipe();
        mView.hideLoadingView();
        mView.showRecipeList();
    }

    @Override
    public void destroy() {
        mView = null;
    }
}
