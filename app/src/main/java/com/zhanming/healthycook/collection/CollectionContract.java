package com.zhanming.healthycook.collection;

import com.zhanming.healthycook.BasePresenter;
import com.zhanming.healthycook.BaseView;
import com.zhanming.healthycook.beans.Recipe;

import java.util.List;

/**
 * Created by zhanming on 2016/10/10.
 */
public interface CollectionContract {
    interface View extends BaseView<Presenter>{
        void showCollection(List<Recipe> datas);

    }
    interface Presenter extends BasePresenter{

    }
}
