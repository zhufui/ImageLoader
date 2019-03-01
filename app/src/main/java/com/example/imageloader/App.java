package com.example.imageloader;

import android.app.Application;

import com.example.imageloader.loader.fresco.FrescoLoader;
import com.example.imageloader.loader.glide.GlideLoader;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.lib_imageloader.ImageLoader;

public class App extends Application {

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

//        ImageLoader.getInstance().setGlobalImageLoader(new PicassoLoader());
//        ImageLoader.getInstance().setGlobalImageLoader(new GlideLoader());
        ImageLoader.getInstance().setGlobalImageLoader(new FrescoLoader());

        //fresco的初始化
        Fresco.initialize(this);
    }
}
