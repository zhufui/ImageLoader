package com.example.imageloader.loader.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.imageloader.App;
import com.lib_imageloader.ILoaderStrategy;
import com.lib_imageloader.LoaderOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * glide地址：https://github.com/bumptech/glide
 */
public class GlideLoader implements ILoaderStrategy {
    @Override
    public void loadImage(Context context, LoaderOptions options) {
        if (context == null) {
            throw new NullPointerException("context is not null");
        }

        RequestOptions ro = new RequestOptions();
        if (options.targetHeight > 0 && options.targetWidth > 0) {
            ro.override(options.targetWidth, options.targetHeight);
        }

        if (options.isCenterInside) {
            ro.centerInside();
        } else if (options.isCenterCrop) {
            ro.centerCrop();
        }

        if (options.errorResId != 0) {
            ro.error(options.errorResId);
        }

        if (options.placeholderResId != 0) {
            ro.placeholder(options.placeholderResId);
        }

        if (options.skipLocalCache) {
            ro.skipMemoryCache(true);
        }
        if (options.skipNetCache) {
            ro.diskCacheStrategy(DiskCacheStrategy.NONE);
        }

        GlideRequests glideRequests = GlideApp.with(context);//GlideApp是添加注解后编译生成的
        if (options.asGif) {
            glideRequests.asGif();
        }
        GlideRequest<Drawable> glideRequest = null;
        if (options.url != null) {
            glideRequest = glideRequests.load(options.url);
        } else if (options.file != null) {
            glideRequest = glideRequests.load(options.file);
        } else if (options.drawableResId != 0) {
            glideRequest = glideRequests.load(options.drawableResId);
        } else if (options.uri != null) {
            glideRequest = glideRequests.load(options.uri);
        }

        glideRequest.apply(ro);

        if (options.isCircle) {
            glideRequest.transform(new CropCircleTransformation());
        } else if (options.bitmapAngle != 0) {
            glideRequest.transform(new RoundedCornersTransformation(
                    (int) options.bitmapAngle, 0,
                    RoundedCornersTransformation.CornerType.ALL));
        } else if (options.isBlur) {
            glideRequest.transform(new BlurTransformation());
        }

        if (options.targetView instanceof ImageView) {
            glideRequest.into(((ImageView) options.targetView));
        } else if (options.callBack != null) {
//            glideRequest.into(new PicassoTarget(options.callBack));
        }

    }

    @Override
    public void clearMemoryCache() {
        GlideApp.get(App.app).clearMemory();
    }

    @Override
    public void clearDiskCache() {
        GlideApp.get(App.app).clearDiskCache();
    }
}
