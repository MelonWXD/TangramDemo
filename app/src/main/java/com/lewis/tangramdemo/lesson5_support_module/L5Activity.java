package com.lewis.tangramdemo.lesson5_support_module;

import com.lewis.tangramdemo.CommonActivity;
import com.lewis.tangramdemo.lesson1.TestView1;
import com.lewis.tangramdemo.lesson1_click.TestView1_click;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;
import com.tmall.wireless.tangram.support.CardSupport;
import com.tmall.wireless.tangram.support.CellSupport;

import static com.lewis.tangramdemo.lesson1.L1Activity.TYPE_TEST_VIEW_1;
import static com.lewis.tangramdemo.lesson1_click.L1Activity_click.TYPE_TEST_VIEW_1_CLICK;

/**
 * @CreateDate: 2019/1/3 上午11:50
 * @Author: Lewis Weng
 * @Description:
 */
public class L5Activity extends CommonActivity {

    @Override
    public void doBuilderRegister(TangramBuilder.InnerBuilder builder) {
        builder.registerCell(TYPE_TEST_VIEW_1, TestView1.class);
        builder.registerCell(TYPE_TEST_VIEW_1_CLICK, TestView1_click.class);
    }

    @Override
    public void doEngineRegister(TangramEngine engine) {
        engine.register(CellSupport.class, new CustomCellSupport());
        engine.register(CardSupport.class, new CustomCardSupport());
    }

    @Override
    public String getDataName() {
        return "data_5.json";
    }
}
