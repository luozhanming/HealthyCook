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

public class RecipeListActivity extends AppCompatActivity {

    @BindView(R.id.stl_activity_recipe)
    SmartTabLayout mSmartTabLayout;
    @BindView(R.id.vp_activity_recipe_page)
    ViewPager mViewPage;

    private BaseRecipePresenter mPresenter;

    private List<Fragment> fragments;
    private String[] meiRongCatalogues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        meiRongCatalogues = getResources().getStringArray(R.array.meiRongCatalogue);
        //获取菜谱类别
        String catalogue = getIntent().getStringExtra("group");
        //获取此菜谱类别的PageManager
        initFragments();
    }

    private void initFragments() {
        FragmentPagerItems pages = new FragmentPagerItems(this);
        for (String catalogue : meiRongCatalogues) {
            pages.add(FragmentPagerItem.of(catalogue, RecipeListFragment.class));

        }
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(),pages);
        mViewPage.setAdapter(adapter);
        mSmartTabLayout.setViewPager(mViewPage);

    }
}
