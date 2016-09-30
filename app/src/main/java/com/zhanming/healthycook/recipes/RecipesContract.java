package com.zhanming.healthycook.recipes;

import com.zhanming.healthycook.BasePresenter;
import com.zhanming.healthycook.BaseView;

/**
 * Created by zhanming on 2016/9/23.
 */
public class RecipesContract {

    interface ActivityPresenter extends BasePresenter{

    }
    interface ActivityView extends BaseView<ActivityPresenter>{
        void initFragments(String[] catalogues);

        void showLoadingView();

        void hideLoadingView();

        void showRecipeList();

        void jumpToDetail();
    }

    interface PageView extends BaseView<Presenter> {

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
