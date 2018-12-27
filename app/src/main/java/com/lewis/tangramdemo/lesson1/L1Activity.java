package com.lewis.tangramdemo.lesson1;

import com.lewis.tangramdemo.CommonActivity;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;

/**
 * @CreateDate: 2018/12/26 下午3:26
 * @Author: Lewis Weng
 * @Description:
 */
public class L1Activity extends CommonActivity {
    public static final String TYPE_TEST_VIEW_1 = "TestView1";
    public static final String TYPE_TEST_IMAGE = "TestImage";


    @Override
    public void doBuilderRegister(TangramBuilder.InnerBuilder builder) {
        builder.registerCell(TYPE_TEST_VIEW_1, TestView1.class);
        builder.registerCell(TYPE_TEST_IMAGE, TestImageView.class);

    }

    @Override
    public void doEngineRegister(TangramEngine engine) {

    }

    @Override
    public String getDataName() {
        return "data_1.json";
    }
}
