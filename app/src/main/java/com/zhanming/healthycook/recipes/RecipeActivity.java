package com.zhanming.healthycook.recipes;

import android.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.zhanming.healthycook.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zhanming on 2016/9/23.
 */
public class RecipeActivity extends AppCompatActivity {
    
    private static final String TAG = "RecipeActivity";

    private BaseRecipePresenter mPresenter;

    private List<Fragment> fragments;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        //获取菜谱类别
        String catalogue = getIntent().getStringExtra("what-recipe");
        //根据catalogue的不同装载不同的pageManager

    }
}
