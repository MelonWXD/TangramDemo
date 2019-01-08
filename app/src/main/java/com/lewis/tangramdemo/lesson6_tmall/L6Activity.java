package com.lewis.tangramdemo.lesson6_tmall;

import android.os.Bundle;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.lewis.tangramdemo.CommonActivity;
import com.lewis.tangramdemo.R;
import com.lewis.tangramdemo.lesson1.TestImageView;
import com.lewis.tangramdemo.lesson6_tmall.view.TextImageView;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;

import static com.lewis.tangramdemo.lesson6_tmall.Constant.TYPE_IMAGEVIEW;
import static com.lewis.tangramdemo.lesson6_tmall.Constant.TYPE_TEXTIMAGEVIEW;

/**
 * @CreateDate: 2019/1/7 下午4:18
 * @Author: Lewis Weng
 * @Description:
 */
public class L6Activity extends CommonActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_tmall;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ScreenUtils.setFullScreen(this);
        BarUtils.setStatusBarVisibility(this, true);
        BarUtils.setStatusBarLightMode(this, true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void doBuilderRegister(TangramBuilder.InnerBuilder builder) {
        builder.registerCell(TYPE_IMAGEVIEW, TestImageView.class);
        builder.registerCell(TYPE_TEXTIMAGEVIEW, TextImageView.class);

    }

    @Override
    public void doEngineRegister(TangramEngine engine) {

    }

    @Override
    public String getDataName() {
        return "data_tmall.json";
    }
}
