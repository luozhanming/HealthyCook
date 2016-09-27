package com.zhanming.healthycook;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.recipes.pagemanager.PageManager;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        PageManager manager = PageManager.getInstance(getContext());
        Catalogue catalogue = manager.getCatalogue("MeiRong", "MeiYan");
        catalogue.setPage(1);
        manager.updateCataloguePage(catalogue);
        Catalogue catalogue1 = manager.getCatalogue("MeiRong", "MeiYan");
    }
}