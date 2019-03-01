package com.example.imageloader.loader.fresco;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.lib_imageloader.ILoaderStrategy;
import com.lib_imageloader.LoaderOptions;

/**
 * Fresco 支持许多URI格式。见下表：
 * 类型                   Scheme
 * 远程图片                http://,https://
 * 本地文件                file://
 * Content provider:      content://
 * asset目录下的资源:       asset://
 * res目录下的资源:         res://
 * <p>
 * 文档地址：https://www.fresco-cn.org/docs/
 */
public class FrescoLoader implements ILoaderStrategy {
    @Override
    public void loadImage(Context context, LoaderOptions options) {
        if (context == null) {
            throw new NullPointerException("context is not null");
        }

        if (options.targetView == null) {
            throw new NullPointerException("imageView is not null");
        }

        PipelineDraweeControllerBuilder pdc = Fresco.newDraweeControllerBuilder();
        if (options.url != null) {
            pdc.setUri(Uri.parse(options.url));
        } else if (options.file != null) {
            pdc.setUri(Uri.parse("file://" + options.file));
        } else if (options.drawableResId != 0) {
            pdc.setUri(Uri.parse("res://" + options.drawableResId));
        } else if (options.uri != null) {
            pdc.setUri(options.uri);
        }

        if (options.asGif) {
            pdc.setAutoPlayAnimations(true);
        }

        SimpleDraweeView sdv = null;
        if (options.targetView instanceof SimpleDraweeView) {
            sdv = (SimpleDraweeView) options.targetView;
        }

        if (options.isCenterInside) {
            sdv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else if (options.isCenterCrop) {
            sdv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        if (options.isCircle) {
            RoundingParams roundingParams = RoundingParams.asCircle();
            sdv.getHierarchy().setRoundingParams(roundingParams);
        } else if (options.bitmapAngle != 0) {
            RoundingParams roundingParams = RoundingParams
                    .fromCornersRadius(options.bitmapAngle);
//            roundingParams.setBorder()//设置边框宽度
            sdv.getHierarchy().setRoundingParams(roundingParams);
        } else if (options.isBlur) {
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(options.url))
                    //int iterations迭代次数, int blurRadius模糊半径
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(1, 23))
                    .build();
            pdc.setImageRequest(request);
        }

        sdv.setController(pdc.build());
    }

    @Override
    public void clearMemoryCache() {
        Fresco.getImagePipeline().clearMemoryCaches();
    }

    @Override
    public void clearDiskCache() {
        Fresco.getImagePipeline().clearDiskCaches();
    }
}
