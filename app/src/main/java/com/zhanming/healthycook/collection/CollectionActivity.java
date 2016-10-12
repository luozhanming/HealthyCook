package com.zhanming.healthycook.collection;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.zhanming.healthycook.R;
import com.zhanming.healthycook.beans.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CollectionActivity extends AppCompatActivity implements CollectionContract.View {

    private static final String TAG = "CollectionActivity";

    @BindView(R.id.rv_activity_collection)
    RecyclerView rv_collections;

    private Unbinder unbinder;
    private CollectionContract.Presenter mPresenter;
    private CollectionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        unbinder = ButterKnife.bind(this);
        initActionBar();
        initRecyclerView();
        new CollectionPresenter(this, this);
        mPresenter.start();
    }

    private void initRecyclerView() {
        rv_collections.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new CollectionAdapter(this);
        rv_collections.setAdapter(mAdapter);
    }

    private void initActionBar() {
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCollection(List<Recipe> datas) {
        mAdapter.setDatas(datas);
    }

    @Override
    public void setPresenter(CollectionContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
