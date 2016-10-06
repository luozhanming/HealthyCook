package com.zhanming.healthycook.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.zhanming.healthycook.R;
import com.zhanming.healthycook.BasePresenter;
import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.database.DBHelper;
import com.zhanming.healthycook.recipes.RecipeListActivity;
import com.zhanming.healthycook.recipes.pagemanager.PageManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rv_activity_main)
    RecyclerView mRecyclerView;
    private Unbinder unbinder;

    private String[] topCatalogues;
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initFloatingActionButton();
        initRecyclerView();
        topCatalogues = getResources().getStringArray(R.array.catalogueName);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("健康菜谱");
    }

    private void initFloatingActionButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //搜索功能
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DBHelper helper = new DBHelper(this);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new CatalogueAdapter(this, this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_collection) {

            return true;
        } else if (id == R.id.action_shareApp) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = (MainContract.Presenter) presenter;
    }

    //扩展菜谱时修改这个
    @Override
    public void jumpToPage(int position) {
        Intent intent = new Intent(this, RecipeListActivity.class);
        intent.putExtra("group", topCatalogues[position]);
        this.startActivity(intent);
    }
}
