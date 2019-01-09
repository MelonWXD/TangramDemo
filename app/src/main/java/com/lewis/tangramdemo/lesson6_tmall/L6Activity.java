package com.lewis.tangramdemo.lesson6_tmall;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.lewis.tangramdemo.CommonActivity;
import com.lewis.tangramdemo.R;
import com.lewis.tangramdemo.lesson1.TestImageView;
import com.lewis.tangramdemo.lesson6_tmall.view.TextImageView;
import com.lewis.tangramdemo.lesson6_tmall.view.TextViewCell;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;

import static com.lewis.tangramdemo.lesson6_tmall.Constant.TYPE_IMAGE;
import static com.lewis.tangramdemo.lesson6_tmall.Constant.TYPE_TEXT;
import static com.lewis.tangramdemo.lesson6_tmall.Constant.TYPE_TEXTIMAGE;

/**
 * @CreateDate: 2019/1/7 下午4:18
 * @Author: Lewis Weng
 * @Description:
 */
public class L6Activity extends CommonActivity {

    View bgHeader;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tmall;
    }


    @Override
    public void initView() {
        super.initView();
        bgHeader = findViewById(R.id.bg_header);
        getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
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
        builder.registerCell(TYPE_TEXT, TextViewCell.class);
        builder.registerCell(TYPE_IMAGE, TestImageView.class);
        builder.registerCell(TYPE_TEXTIMAGE, TextImageView.class);


    }

    @Override
    public void doEngineRegister(TangramEngine engine) {
        engine.setPreLoadNumber(10);
    }

    @Override
    public String getDataName() {
        return "data_tmall.json";
    }
}
