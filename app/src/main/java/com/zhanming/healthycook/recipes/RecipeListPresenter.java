package com.zhanming.healthycook.recipes;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.models.RemoteRecipeSource;
import com.zhanming.healthycook.recipes.pagemanager.PageManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhanming on 2016/9/23.
 */
public class RecipeListPresenter implements RecipesContract.Presenter {

    private static final String TAG = "RecipeListPresenter";

    private RecipesContract.PageView mView;
    //记录上一个加载的目录数据
    private Catalogue mCatalogue;
    private Context mContext;
    private List<Integer> pageLoadedList;


    public RecipeListPresenter(Context context, RecipesContract.PageView view, String group, String calogue) {
        mView = view;
        pageLoadedList = new ArrayList<>();
        mContext = context;
        mCatalogue = PageManager.getInstance(context).getCatalogue(group, calogue);
        mView.setPresenter(this);
    }


    private void loadInitialRecipe() {
        Log.d(TAG, "loadInitialRecipe: " + mCatalogue.getPage());
        _getObserable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Recipe>>() {
                    @Override
                    public void call(List<Recipe> recipes) {
                        if (recipes == null || recipes.size() == 0) {
                            mView.showNoDatas();
                        }
                        mView.showRecipeList();
                        pageLoadedList.add(mCatalogue.getPage());
                        mView.updateList(recipes, true);
                    }
                });
    }

    private Observable<List<Recipe>> _getObserable() {
        return Observable.just(mCatalogue).map(new Func1<Catalogue, List<Recipe>>() {
            @Override
            public List<Recipe> call(Catalogue catalogue) {
                List<Recipe> list = null;
                try {
                    list = RemoteRecipeSource.getInstance().getRecipeList(catalogue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return list;
            }
        });
    }


    private void opearateCataloguePage(boolean isUp) {
        if (isUp) {
            mCatalogue.setPage(mCatalogue.getPage() + 1);
        } else {
            if (mCatalogue.getPage() > 1) {
                mCatalogue.setPage(mCatalogue.getPage() - 1);
            }
        }
    }

    private int computeMax(List<Integer> list) {
        return Collections.max(list);
    }

    private int computeMin(List<Integer> list) {
        return Collections.min(list);
    }

    @Override
    public void loadMoreRecipesByPullDown() {
        int preLoad = computeMax(pageLoadedList) + 1;
        mCatalogue.setPage(preLoad);
        //  opearateCataloguePage(true);
        //此页已经加载，无需重复加载
        if (pageLoadedList.contains(mCatalogue.getPage())) {
            return;
        }
        _getObserable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Recipe>>() {
                    @Override
                    public void onCompleted() {
                        Observable.just(true)
                                .observeOn(Schedulers.io())
                                .subscribe(new Action1<Boolean>() {
                                    @Override
                                    public void call(Boolean aBoolean) {
                                        PageManager.getInstance(mContext).updateCataloguePage(mCatalogue);
                                    }
                                });
                        mView.toggleRefreshing();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Recipe> recipes) {
                        if (recipes == null || recipes.size() != 0) {
                            mView.updateList(recipes, true);
                            pageLoadedList.add(mCatalogue.getPage());
                            mView.showTopRecipe();
                        } else {
                            opearateCataloguePage(false);
                            mView.showNoMoreDataView();
                        }

                    }
                });

    }

    @Override
    public void loadMoreRecipesByPullUp() {
        //减1页
        int willLoad = computeMin(pageLoadedList) - 1;
        if (willLoad < 1) {
            return;
        } else {
            mCatalogue.setPage(willLoad);
        }
        mView.togglePullUpLoadingView();
        _getObserable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Recipe>>() {
                    @Override
                    public void call(List<Recipe> recipes) {
                        if (recipes != null && recipes.size() > 0) {
                            mView.updateList(recipes, false);
                            pageLoadedList.add(mCatalogue.getPage());
                            mView.togglePullUpLoadingView();
                            if (mCatalogue.getPage() > 0) {
                                PageManager.getInstance(mContext).updateCataloguePage(mCatalogue);
                            }
                        } else {
                            mView.togglePullUpLoadingView();
                            mView.showNoMoreDataView();
                        }
                    }
                });
    }

    @Override
    public void start() {
        mView.showLoadingView();
        loadInitialRecipe();
    }

    @Override
    public void destroy() {
        mView = null;
        mCatalogue = null;
        pageLoadedList = null;
    }
}
