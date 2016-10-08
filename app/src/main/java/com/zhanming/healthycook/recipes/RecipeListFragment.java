package com.zhanming.healthycook.recipes;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhanming.healthycook.R;
import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.beans.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by zhanming on 2016/9/23.
 */
public class RecipeListFragment extends Fragment implements RecipesContract.PageView {

    private static final String TAG = "RecipeListFragment";

    @BindView(R.id.rl_rootList)
    RelativeLayout rl_rootList;
    @BindView(R.id.rv_fragment_recipeList)
    RecyclerView rv_list;
    @BindView(R.id.srl_fragment_recipeList)
    SwipeRefreshLayout srl_refresh;
    @BindView(R.id.rl_loadingLayout)
    RelativeLayout rl_loadingLayout;
    @BindView(R.id.rl_noDataLayout)
    RelativeLayout rl_noDataLayout;
    private Unbinder unbinder;


    //将这个参数传给Presenter，Presenter通过这个参数获取Catalogue
    private Context mContext;
    private String mCatalogueName;
    private RecipesContract.Presenter mPresenter;
    private ListAdapter mAdapter;
    private boolean hasLoaded = false;


    public RecipeListFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipelist, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        rv_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ListAdapter(mContext);
        rv_list.setAdapter(mAdapter);
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadMoreRecipesByPullDown();
            }
        });
        mPresenter.start();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "-----------onCreate------------ ");
        super.onCreate(savedInstanceState);
        Bundle datas = getArguments();
        mCatalogueName = datas.getString("myCatalogue");
        String myClass = datas.getString("myClass");
        new RecipeListPresenter(mContext, this, myClass, mCatalogueName);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "--------------onStart: " + mCatalogueName + "-------------");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "--------------onResume: " + mCatalogueName + "-------------");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + mCatalogueName);
        unbinder.unbind();
        hasLoaded = false;
    }

    @Override
    public void setPresenter(RecipesContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void showLoadingView() {
        if (rl_loadingLayout.getVisibility() == View.VISIBLE) {
            return;
        }
        rl_loadingLayout.setVisibility(View.VISIBLE);
        rl_rootList.setVisibility(View.GONE);
        rl_noDataLayout.setVisibility(View.GONE);

    }

    @Override
    public void showRecipeList() {
        if (rl_rootList.getVisibility() == View.VISIBLE) {
            return;
        }
        rl_rootList.setVisibility(View.VISIBLE);
        rl_loadingLayout.setVisibility(View.GONE);
        rl_noDataLayout.setVisibility(View.GONE);
    }

    @Override
    public void showNoDatas() {
        if (rl_noDataLayout.getVisibility() == View.VISIBLE) {
            return;
        }
        rl_rootList.setVisibility(View.GONE);
        rl_loadingLayout.setVisibility(View.GONE);
        rl_noDataLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void toggleRefreshing() {
        srl_refresh.setRefreshing(!srl_refresh.isRefreshing());
    }

    @Override
    public void showNoMoreDataView() {
        Toast.makeText(mContext, "没有更多菜谱了！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateList(List<Recipe> recipes) {
        mAdapter.addMoreDatasToTop(recipes);
    }

}
