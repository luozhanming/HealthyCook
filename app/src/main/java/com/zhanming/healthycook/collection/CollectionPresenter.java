package com.zhanming.healthycook.collection;

import android.content.Context;

import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.models.LocalRecipeSource;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhanming on 2016/10/10.
 */
public class CollectionPresenter implements CollectionContract.Presenter {


    private CollectionContract.View mView;
    private Context mContxt;

    public CollectionPresenter(CollectionContract.View view, Context context) {
        this.mView = view;
        this.mContxt = context;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        Observable.just(true).map(new Func1<Boolean, List<Recipe>>() {
            @Override
            public List<Recipe> call(Boolean aBoolean) {
                List<Recipe> recipes = LocalRecipeSource.getInstance(mContxt).getRecipes();
                return recipes;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Recipe>>() {
                    @Override
                    public void call(List<Recipe> recipes) {
                        mView.showCollection(recipes);
                    }
                });
    }

    @Override
    public void destroy() {

    }
}
