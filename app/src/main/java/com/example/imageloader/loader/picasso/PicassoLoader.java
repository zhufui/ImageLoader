package com.example.imageloader.loader.picasso;

import android.content.Context;
import android.widget.ImageView;

import com.example.imageloader.App;
import com.lib_imageloader.ILoaderStrategy;
import com.lib_imageloader.LoaderOptions;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

/**
 * picasso地址：https://github.com/square/picasso
 */
public class PicassoLoader implements ILoaderStrategy {
    private volatile static Picasso sPicassoSingleton;
    private final String PICASSO_CACHE = "picasso-cache";
    private static LruCache sLruCache = new LruCache(App.app);

    private static Picasso getPicasso() {
        if (sPicassoSingleton == null) {
            synchronized (PicassoLoader.class) {
                if (sPicassoSingleton == null) {
                    sPicassoSingleton = new Picasso.Builder(App.app)
                            .memoryCache(sLruCache).build();
                }
            }
        }
        return sPicassoSingleton;
    }

    @Override
    public void loadImage(Context context, LoaderOptions options) {
        RequestCreator requestCreator = null;
        if (options.url != null) {
            requestCreator = getPicasso().load(options.url);
        } else if (options.file != null) {
            requestCreator = getPicasso().load(options.file);
        } else if (options.drawableResId != 0) {
            requestCreator = getPicasso().load(options.drawableResId);
        } else if (options.uri != null) {
            requestCreator = getPicasso().load(options.uri);
        }

        if (requestCreator == null) {
            throw new NullPointerException("requestCreator must not be null");
        }
        if (options.targetHeight > 0 && options.targetWidth > 0) {
            requestCreator.resize(options.targetWidth, options.targetHeight);
        }
        if (options.isCenterInside) {
            requestCreator.centerInside();
        } else if (options.isCenterCrop) {
            requestCreator.centerCrop();
        }
        if (options.config != null) {
            requestCreator.config(options.config);
        }
        if (options.errorResId != 0) {
            requestCreator.error(options.errorResId);
        }
        if (options.placeholderResId != 0) {
            requestCreator.placeholder(options.placeholderResId);
        }

        if (options.isCircle) {
            requestCreator.transform(new CircleTransform());
        } else {
            if (options.bitmapAngle != 0) {
                requestCreator.transform(new RoundTransform(options.bitmapAngle));
            }
        }

        if (options.skipLocalCache) {
            requestCreator.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE);
        }
        if (options.skipNetCache) {
            requestCreator.networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE);
        }
        if (options.degrees != 0) {
            requestCreator.rotate(options.degrees);
        }

        if (options.targetView instanceof ImageView) {
            requestCreator.into(((ImageView) options.targetView));
        } else if (options.callBack != null) {
            requestCreator.into(new PicassoTarget(options.callBack));
        }
    }

    @Override
    public void clearMemoryCache() {
        sLruCache.clear();
    }

    @Override
    public void clearDiskCache() {
        File diskFile = new File(App.app.getCacheDir(), PICASSO_CACHE);
        if (diskFile.exists()) {
            diskFile.delete();
        }
    }
}
