package com.zhanming.healthycook.detail;

import android.content.Intent;
import android.os.Bundle;

import com.zhanming.healthycook.BasePresenter;
import com.zhanming.healthycook.BaseView;

/**
 * Created by zhanming on 2016/10/3.
 */
public interface DetailContract {
    interface View extends BaseView<Presenter> {
        Bundle getViewDatas();
        void setRecipeImage(String imgUrl);
        void setRecipeContent(String content);
        void setRecipeTitle(String title);
    }

    interface Presenter extends BasePresenter {
        void collectRecipe();
    }
}
