package com.zhanming.healthycook;

import android.app.Application;
import android.os.Process;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

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
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY-2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheFileCount(25)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
