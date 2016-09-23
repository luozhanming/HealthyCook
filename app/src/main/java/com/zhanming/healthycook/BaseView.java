package com.zhanming.healthycook;

/**
 * Created by zhanming on 2016/9/22.
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
