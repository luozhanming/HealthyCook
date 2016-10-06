package com.zhanming.healthycook;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.database.DBHelper;
import com.zhanming.healthycook.models.RemoteRecipeSource;
import com.zhanming.healthycook.recipes.pagemanager.PageManager;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}