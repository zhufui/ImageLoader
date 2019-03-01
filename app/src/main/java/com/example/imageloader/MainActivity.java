package com.example.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.lib_imageloader.ImageLoader;
import com.lib_imageloader.LoaderOptions;

/**
 * 三大图片加载框架优缺点分析
 * https://www.jianshu.com/p/21b1a55f2bd2
 */
public class MainActivity extends AppCompatActivity {
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);

        load1();
        load2();
        load3();
        load4();
        load5();
    }

    /**
     * 一般加载图片
     */
    private void load1() {
        LoaderOptions options = new LoaderOptions("https://i.imgur.com/CqmBjo5.jpg");
        options.centerCrop()
                .resize(100, 100)
                .into(iv1);
        ImageLoader.getInstance().loadOptions(this, options);
    }

    /**
     * 圆形图片
     */
    private void load2() {
        LoaderOptions options = new LoaderOptions("https://i.imgur.com/zkaAooq.jpg");
        options.centerCrop()
                .circle(true)
                .resize(100, 100)
                .into(iv2);
        ImageLoader.getInstance().loadOptions(this, options);
    }

    /**
     * 圆角图片
     */
    private void load3() {
        LoaderOptions options = new LoaderOptions("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551969436&di=7e7ac8fd61ee9b7f80aded506ee2a169&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201312%2F05%2F20131205172445_4cznX.thumb.700_0.jpeg");
        options.centerCrop()
                .angle(20)
                .resize(100, 100)
                .into(iv3);
        ImageLoader.getInstance().loadOptions(this, options);
    }

    /**
     * 模糊图片,glide加载
     */
    private void load4() {
        LoaderOptions options = new LoaderOptions("https://i.imgur.com/DvpvklR.jpg");
        options.centerCrop()
                .blur(true)
                .resize(100, 100)
                .into(iv4);
        ImageLoader.getInstance().loadOptions(this, options);
    }

    /**
     * 展示gif图片，glide加载
     */
    private void load5() {
        LoaderOptions options = new LoaderOptions("http://h.hiphotos.baidu.com/image/pic/item/78310a55b319ebc4885bf1828926cffc1e17168d.jpg");
        options.centerCrop()
                .asGif(true)
                .resize(100, 100)
                .into(iv5);
        ImageLoader.getInstance().loadOptions(this, options);
    }
}
