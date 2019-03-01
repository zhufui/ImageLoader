package com.example.imageloader.loader.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.lib_imageloader.BitmapCallBack;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PicassoTarget implements Target {
    BitmapCallBack callBack;

    protected PicassoTarget(BitmapCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        if (this.callBack != null) {
            this.callBack.onBitmapLoaded(bitmap);
        }
    }

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
        if (this.callBack != null) {
            this.callBack.onBitmapFailed(e);
        }
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
