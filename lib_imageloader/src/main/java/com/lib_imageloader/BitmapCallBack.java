package com.lib_imageloader;

import android.graphics.Bitmap;

/**
 * bitmap回调
 */
public interface BitmapCallBack {
    void onBitmapLoaded(Bitmap bitmap);

    void onBitmapFailed(Exception e);

    public static class EmptyCallback implements BitmapCallBack {

        @Override
        public void onBitmapLoaded(Bitmap bitmap) {

        }

        @Override
        public void onBitmapFailed(Exception e) {

        }
    }
}
