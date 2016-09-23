package com.zhanming.healthycook.recipes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhanming.healthycook.R;
import com.zhanming.healthycook.beans.Catalogue;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanming on 2016/9/23.
 */
public class RecipeListFragment extends Fragment {

    @BindView(R.id.tv_page)
    TextView tv_page;

    private String catalogueName;

    public RecipeListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipelist,null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);
    }
}
