package com.lewis.tangramdemo;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.util.IInnerImageSetter;

/**
 * @CreateDate: 2018/12/20 下午3:41
 * @Author: Lewis Weng
 * @Description:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        initTangram();
    }

    /**
     * 应用全局只需要初始化一次，提供一个通用的图片加载器，一个应用内通用的ImageView类型
     */
    private void initTangram() {
        TangramBuilder.init(this, new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view, @Nullable String url) {
                Glide.with(App.this).load(url).into(view);
            }
        }, ImageView.class);
    }
}
