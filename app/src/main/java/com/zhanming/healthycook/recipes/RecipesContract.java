package com.zhanming.healthycook.recipes;

import com.zhanming.healthycook.BasePresenter;
import com.zhanming.healthycook.BaseView;
import com.zhanming.healthycook.beans.Recipe;

import java.util.List;

/**
 * Created by zhanming on 2016/9/23.
 */
public class RecipesContract {

    interface ActivityPresenter extends BasePresenter{

    }
    interface ActivityView extends BaseView<ActivityPresenter>{
        void initFragments(String[] catalogues);

    }

    interface PageView extends BaseView<Presenter> {
        void showLoadingView();

        void showRecipeList();

        void updateList(List<Recipe> recipes);
    }

    interface Presenter extends BasePresenter {
        //初始化时加载出事页面数据10个数据

        //下拉加载更多数据
        void loadMoreRecipesByPullDown();

        //上拉加载更多数据
        void loadMoreRecipesByPullUp();

        //换页
        void changePage(int page);
    }
}
