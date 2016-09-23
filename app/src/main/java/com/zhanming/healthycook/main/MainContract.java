package com.zhanming.healthycook.main;

import com.zhanming.healthycook.BasePresenter;
import com.zhanming.healthycook.BaseView;

/**
 * Created by zhanming on 2016/9/22.
 */
public interface MainContract {
    interface View extends BaseView {
        void jumpToPage(int position);
    }

    interface Presenter extends BasePresenter {

    }
}
