package com.lib_imageloader;

import android.content.Context;

/**
 * 导入图片的策略接口
 */
public interface ILoaderStrategy {
    void loadImage(Context context, LoaderOptions options);

    /**
     * 清理内存缓存
     */
    void clearMemoryCache();

    /**
     * 清理磁盘缓存
     */
    void clearDiskCache();
}
