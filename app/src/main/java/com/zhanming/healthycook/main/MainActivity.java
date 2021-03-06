package com.zhanming.healthycook.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.umeng.analytics.MobclickAgent;
import com.zhanming.healthycook.R;
import com.zhanming.healthycook.BasePresenter;
import com.zhanming.healthycook.collection.CollectionActivity;
import com.zhanming.healthycook.database.DBHelper;
import com.zhanming.healthycook.recipes.RecipeListActivity;
import com.zhanming.healthycook.search.SearchActivity;

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
        DBHelper helper = new DBHelper(this);
        helper.getWritableDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });
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
            Intent intent = new Intent(this, CollectionActivity.class);
            startActivity(intent);
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
