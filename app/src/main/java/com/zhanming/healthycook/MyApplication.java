package com.zhanming.healthycook;

import android.app.Application;
import android.os.Process;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.analytics.MobclickAgent;
import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.database.DBHelper;
import com.zhanming.healthycook.models.LocalRecipeSource;
import com.zhanming.healthycook.models.RemoteRecipeSource;

import net.youmi.android.AdManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by zhanming on 2016/9/22.
 */
public class MyApplication extends Application {

    public static MyApplication mApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        initImageLoader();
        AdManager.getInstance(this).init("e41a31db7493b24a","3eb2915113b92e46",true,true);
        loadCatalogueDB2Cache();

    }

    private void loadCatalogueDB2Cache() {
        File file = new File(getCacheDir(), "catalogue.db");
        if (file.exists()) {
            return;
        }
        try {
            InputStream is = getAssets().open("catalogue.db");
            File cacheDir = getCacheDir();
            FileOutputStream fos = new FileOutputStream(new File(cacheDir, "catalogue.db"));
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = is.read(b)) != -1) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheFileCount(25)
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize((int) (Runtime.getRuntime().maxMemory()/8))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
