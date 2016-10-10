package com.zhanming.healthycook.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.zhanming.healthycook.R;
import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.customview.EditTextWithDel;
import com.zhanming.healthycook.recipes.ListAdapter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Func1;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.ib_activity_search_back)
    ImageButton ib_back;
    @BindView(R.id.et_activity_search)
    EditTextWithDel et_search;
    @BindView(R.id.ib_activity_search_search)
    ImageButton ib_search;
    @BindView(R.id.rl_activity_search)
    RecyclerView rl_list;

    private ListAdapter mAdapter;
    private Subscription searchSubscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();

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
        mAdapter = new ListAdapter(this);
        rl_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rl_list.setAdapter(mAdapter);

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

}
