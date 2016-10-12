package com.zhanming.healthycook.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;

import com.umeng.analytics.MobclickAgent;
import com.zhanming.healthycook.R;
import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.customview.EditTextWithDel;
import com.zhanming.healthycook.recipes.ListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchActivity extends AppCompatActivity implements SearchContract.View, TextWatcher {

    private static final String TAG = "SearchActivity";

    @BindView(R.id.ib_activity_search_back)
    ImageButton ib_back;
    @BindView(R.id.et_activity_search)
    EditTextWithDel et_search;
    @BindView(R.id.ib_activity_search_search)
    ImageButton ib_search;
    @BindView(R.id.rl_activity_search)
    RecyclerView rl_list;

    private ListAdapter mAdapter;
    private SearchContract.Presenter mPresenter;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        unbinder = ButterKnife.bind(this);
        initView();
        new SearchPresenter(this, this);
        mPresenter.start();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "---------------onDestroy:----------- ");
        unbinder.unbind();
        mPresenter.destroy();
        mPresenter = null;
        mAdapter = null;
    }

    private void initView() {
        et_search.setOnDelClickCallback(new EditTextWithDel.DelClickCallback() {
            @Override
            public void onDelClick(View view) {
                AlphaAnimation aa1 = new AlphaAnimation(1f, 0f);
                aa1.setDuration(400);
                aa1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        AlphaAnimation aa2 = new AlphaAnimation(0f, 1f);
                        aa2.setDuration(400);
                        ib_search.setVisibility(View.VISIBLE);
                        ib_search.startAnimation(aa2);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        et_search.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                view.startAnimation(aa1);
            }
        });
        //设置RecyclerView视图
        mAdapter = new ListAdapter(this);
        rl_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rl_list.setAdapter(mAdapter);
        //设置EditText视图
        et_search.addTextChangedListener(this);

    }

    @OnClick(R.id.ib_activity_search_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.ib_activity_search_search)
    public void search() {
        AlphaAnimation aa1 = new AlphaAnimation(1f, 0f);
        aa1.setDuration(400);
        aa1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                et_search.setVisibility(View.VISIBLE);
                AlphaAnimation aa2 = new AlphaAnimation(0f, 1f);
                aa2.setDuration(400);
                et_search.startAnimation(aa2);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ib_search.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ib_search.startAnimation(aa1);
    }

    @Override
    public String getSearchText() {
        return et_search.getText().toString();
    }

    @Override
    public void showSearchResult(List<Recipe> result) {
        mAdapter.addMoreDatasToTop(result);
    }

    @Override
    public void showSearchNoResult() {

    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().length() > 0) {
            mAdapter.cleanDatas();
            mPresenter.actionSearchSubscription(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
