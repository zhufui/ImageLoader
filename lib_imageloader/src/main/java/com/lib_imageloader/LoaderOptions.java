package com.lib_imageloader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.view.View;

import java.io.File;

/**
 * 加载图片时的常用配置
 */
public class LoaderOptions {
    public int placeholderResId;    //占位图片的资源id
    public int errorResId;          //错误图片的资源id
    public boolean isCenterCrop;    //显示中间部分图片
    public boolean isCenterInside;  //居中显示
    public boolean skipLocalCache;  //是否缓存到本地
    public boolean skipNetCache;    //网络缓存
    public boolean isCircle;        //是否是圆形
    public boolean isBlur;          //是否模糊图片
    public boolean asGif;           //展示gif图片
    public Bitmap.Config config = Bitmap.Config.RGB_565;
    public int targetWidth;         //目标宽度
    public int targetHeight;        //目标高度
    public float bitmapAngle;       //圆角角度
    public float degrees;           //旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
    public Drawable placeholder;    //占位图片
    public View targetView;         //targetView展示图片
    public BitmapCallBack callBack;
    public String url;
    public File file;
    public int drawableResId;
    public Uri uri;
    public ILoaderStrategy loader;  //实时切换图片加载库

    public LoaderOptions(String url) {
        this.url = url;
    }

    public LoaderOptions(File file) {
        this.file = file;
    }

    public LoaderOptions(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    public LoaderOptions(Uri uri) {
        this.uri = uri;
    }

    public void into(View targetView) {
        this.targetView = targetView;
    }

    public void bitmap(BitmapCallBack callBack) {
        this.callBack = callBack;
    }

    public LoaderOptions loader(ILoaderStrategy imageLoader) {
        this.loader = imageLoader;
        return this;
    }

    public LoaderOptions placeholder(@DrawableRes int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    public LoaderOptions placeholder(Drawable placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public LoaderOptions error(@DrawableRes int errorResId) {
        this.errorResId = errorResId;
        return this;
    }

    public LoaderOptions centerCrop() {
        isCenterCrop = true;
        return this;
    }

    public LoaderOptions centerInside() {
        isCenterInside = true;
        return this;
    }

    public LoaderOptions config(Bitmap.Config config) {
        this.config = config;
        return this;
    }

    public LoaderOptions resize(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        return this;
    }

    /**
     * 圆角
     *
     * @param bitmapAngle 度数
     * @return
     */
    public LoaderOptions angle(float bitmapAngle) {
        this.bitmapAngle = bitmapAngle;
        return this;
    }

    public LoaderOptions skipLocalCache(boolean skipLocalCache) {
        this.skipLocalCache = skipLocalCache;
        return this;
    }

    public LoaderOptions skipNetCache(boolean skipNetCache) {
        this.skipNetCache = skipNetCache;
        return this;
    }

    public LoaderOptions rotate(float degrees) {
        this.degrees = degrees;
        return this;
    }

    /**
     * 是否是圆形图片
     *
     * @param isCircle
     * @return
     */
    public LoaderOptions circle(boolean isCircle) {
        this.isCircle = isCircle;
        return this;
    }

    /**
     * 是否模糊图片
     *
     * @param isBlur
     * @return
     */
    public LoaderOptions blur(boolean isBlur) {
        this.isBlur = isBlur;
        return this;
    }

    /**
     * 是否作为gif图片展示
     *
     * @param asGif
     * @return
     */
    public LoaderOptions asGif(boolean asGif) {
        this.asGif = asGif;
        return this;
    }
}
