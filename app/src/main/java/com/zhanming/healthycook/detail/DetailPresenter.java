package com.zhanming.healthycook.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.models.LocalRecipeSource;
import com.zhanming.healthycook.models.RemoteRecipeSource;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
        LocalRecipeSource.getInstance(mContext).addRecipe(mRecipe);
    }

    @Override
    public void start() {
        Bundle datas = mView.getViewDatas();
        mRecipe = datas.getParcelable("recipe");
        mView.setRecipeTitle(mRecipe.getName());
        if (TextUtils.isEmpty(mRecipe.getMessage())) {
            _getOberserable().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(_getOberser());
            return;
        }
        mView.setRecipeImage("http://tnfs.tngou.net/img/" + mRecipe.getImgUrl());
        mView.setRecipeContent(mRecipe.getMessage());

    }

    private Observer<Recipe> _getOberser() {
        return new Observer<Recipe>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Recipe recipe) {
                mView.setRecipeContent(recipe.getMessage());
                String imgUrl = "http://tnfs.tngou.net/img/" + recipe.getImgUrl();
                mView.setRecipeImage(imgUrl);
            }
        };
    }

    private Observable<Recipe> _getOberserable() {
        return Observable.just(mRecipe)
                .map(new Func1<Recipe, Recipe>() {
                    @Override
                    public Recipe call(Recipe recipe) {
                        try {
                            mRecipe = RemoteRecipeSource.getInstance().getRecipeById(mRecipe.getId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return mRecipe;
                    }
                });
    }

    @Override
    public void destroy() {
        mContext = null;
        mView = null;
    }
}
