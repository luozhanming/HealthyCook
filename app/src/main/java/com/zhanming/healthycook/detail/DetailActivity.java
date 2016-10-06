package com.zhanming.healthycook.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhanming.healthycook.R;
import com.zhanming.healthycook.beans.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    @BindView(R.id.iv_activity_detail_recipePic)
    ImageView iv_pic;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.fab_activity_detail_collect)
    FloatingActionButton fab_collect;
    @BindView(R.id.tv_activity_detail_content)
    TextView tv_content;
    private Unbinder unbinder;

    private DetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        unbinder = ButterKnife.bind(this);
        initView();
        new DetailPresenter(this, this);
        mPresenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
        unbinder.unbind();
    }

    private void initView() {
        setSupportActionBar(mToolBar);
        fab_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "是否收藏此菜谱", Snackbar.LENGTH_SHORT)
                        .setAction("是", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mPresenter.collectRecipe();
                            }
                        }).show();
            }
        });
    }

    @Override
    public Bundle getViewDatas() {
        return getIntent().getExtras();
    }

    @Override
    public void setRecipeImage(String imgUrl) {
        DisplayImageOptions options = new DisplayImageOptions
                .Builder()
                .showImageOnFail(R.mipmap.wrong_468px)
                .showImageOnLoading(R.mipmap.loading)
                .build();
        ImageLoader.getInstance().displayImage(imgUrl, iv_pic);
    }

    @Override
    public void setRecipeContent(String content) {
        tv_content.setText(Html.fromHtml(content));
    }

    @Override
    public void setRecipeTitle(String title) {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }


}
