package com.zhanming.healthycook;

import android.app.Application;
import android.os.Process;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zhanming.healthycook.database.DBHelper;
import com.zhanming.healthycook.models.RemoteRecipeSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by zhanming on 2016/9/22.
 */
public class MyApplication extends Application {

    public static MyApplication mApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initImageLoader();
        loadCatalogueDB2Cache();
        try {
            RemoteRecipeSource.getInstance().getRecipeById("10");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCatalogueDB2Cache() {
        File file = new File(getCacheDir(),"catalogue.db");
        if(file.exists()){
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
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
