package com.zhanming.healthycook.recipes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
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
public class RecipeListFragment extends Fragment implements RecipesContract.PageView{

    private static final String TAG = "RecipeListFragment";



    //将这个参数传给Presenter，Presenter通过这个参数获取Catalogue
    private Context mContext;
    private String mCatalogueName;
    private RecipesContract.Presenter mPresenter;


    public RecipeListFragment(){

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipelist,null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "-----------onCreate------------ ");
        super.onCreate(savedInstanceState);
        Bundle datas = getArguments();
        mCatalogueName = datas.getString("myCatalogue");
        String myClass = datas.getString("myClass");
        new RecipeListPresenter(mContext,this,myClass,mCatalogueName);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "--------------onStart: "+mCatalogueName+"-------------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void setPresenter(RecipesContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
