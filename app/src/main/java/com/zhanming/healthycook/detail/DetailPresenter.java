package com.zhanming.healthycook.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.zhanming.healthycook.beans.Recipe;

import rx.Observable;
import rx.Observer;

/**
 * Created by zhanming on 2016/10/3.
 */
public class DetailPresenter implements DetailContract.Presenter {

    private static final String TAG = "DetailPresenter";

    private Context mContext;
    private Recipe mRecipe;
    private DetailContract.View mView;

    public DetailPresenter(Context context, DetailContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void collectRecipe() {

    }

    @Override
    public void start() {
        Bundle datas = mView.getViewDatas();
        Recipe recipe = datas.getParcelable("recipe");
        mRecipe = recipe;
        String title = recipe.getName();
        String imgUrl = recipe.getImgUrl();
        String message = recipe.getMessage();
        mView.setRecipeTitle(title);
        mView.setRecipeImage(imgUrl);
        mView.setRecipeContent(message);
    }

    @Override
    public void destroy() {
        mContext = null;
        mView = null;
    }
}
