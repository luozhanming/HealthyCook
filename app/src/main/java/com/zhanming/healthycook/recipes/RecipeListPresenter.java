package com.zhanming.healthycook.recipes;

import android.content.Context;
import android.os.SystemClock;

import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.models.RemoteRecipeSource;
import com.zhanming.healthycook.recipes.pagemanager.PageManager;

import java.util.LinkedList;
import java.util.List;

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

    private RecipesContract.PageView mView;
    private Catalogue mCatalogue;
    private Context mContext;


    public RecipeListPresenter(Context context, RecipesContract.PageView view, String group, String calogue) {
        mView = view;

        mCatalogue = PageManager.getInstance(context).getCatalogue(group, calogue);
        mView.setPresenter(this);
    }


    private void loadInitialRecipe() {
        _getObserable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_getObserver());
    }

    private Observer<List<Recipe>> _getObserver() {
        return new Observer<List<Recipe>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Recipe> recipes) {
                if (recipes.size() == 0) {
                    mView.showNoDatas();
                }
                mView.showRecipeList();
                mView.updateList(recipes);
            }
        };
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
            mCatalogue.setPage(mCatalogue.getPage() - 1);
        }
    }

    @Override
    public void loadMoreRecipesByPullDown() {
        opearateCataloguePage(true);
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
                        if (recipes.size() != 0) {
                            mView.updateList(recipes);
                        } else{
                            opearateCataloguePage(false);
                            mView.showNoMoreDataView();
                        }

                    }
                });


    }

    @Override
    public void loadMoreRecipesByPullUp() {

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
    }
}
