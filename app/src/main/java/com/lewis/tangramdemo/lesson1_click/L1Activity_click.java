package com.lewis.tangramdemo.lesson1_click;

import com.lewis.tangramdemo.CommonActivity;
import com.lewis.tangramdemo.lesson1.TestView1;
import com.lewis.tangramdemo.support.SampleClickSupport;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;

/**
 * @CreateDate: 2018/12/26 下午3:26
 * @Author: Lewis Weng
 * @Description: 让添加的view支持点击事件
 */
public class L1Activity_click extends CommonActivity {
    public static final String TYPE_TEST_VIEW_1_CLICK = "TestView1_click";


    @Override
    public void doBuilderRegister(TangramBuilder.InnerBuilder builder) {
        builder.registerCell(TYPE_TEST_VIEW_1_CLICK, TestView1_click.class);

    }

    @Override
    public void doEngineRegister(TangramEngine engine) {
        //engine添加点击支持
        engine.addSimpleClickSupport(new SampleClickSupport());
    }

    @Override
    public String getDataName() {
        return "data_1_click.json";
    }
}