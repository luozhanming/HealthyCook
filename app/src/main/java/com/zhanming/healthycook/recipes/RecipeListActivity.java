package com.zhanming.healthycook.recipes;

import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.zhanming.healthycook.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListActivity extends AppCompatActivity implements RecipesContract.ActivityView {

    @BindView(R.id.stl_activity_recipe)
    SmartTabLayout mSmartTabLayout;
    @BindView(R.id.vp_activity_recipe_page)
    ViewPager mViewPage;

    private RecipesContract.ActivityPresenter mPresenter;

    private List<Fragment> fragments;
    private String mClass;  //菜谱种类
    private String[] mCatalogues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        String catalogue = getIntent().getStringExtra("group");
        mClass = catalogue;
        new RecipeMainPresenter(this,this, mClass);
        mPresenter.start();
    }

    @Override
    public void initFragments(String[] catalogues) {
        FragmentPagerItems pages = new FragmentPagerItems(this);
        for (String catalogue : catalogues) {
            pages.add(FragmentPagerItem.of(catalogue, RecipeListFragment.class));
        }
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), pages);
        mViewPage.setAdapter(adapter);
        mSmartTabLayout.setViewPager(mViewPage);
    }

    @Override
    public void setPresenter(RecipesContract.ActivityPresenter presenter) {
        mPresenter = presenter;
    }
}
