package com.lewis.tangramdemo.lesson1_scroll;

import com.lewis.tangramdemo.lesson1_click.L1Activity_click;
import com.lewis.tangramdemo.support.SampleScrollSupport;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;

/**
 * @CreateDate: 2018/12/26 下午3:26
 * @Author: Lewis Weng
 * @Description: 让添加的view支持滑动响应
 */
public class L1Activity_scroll extends L1Activity_click {
    public static final String TYPE_TEST_VIEW_1_SCROLL = "TestView1_scroll";

    @Override
    public void initView() {
        super.initView();
//        getRecyclerView().addOnScrollListener(new L1ScrollListener());
    }

    @Override
    public void doBuilderRegister(TangramBuilder.InnerBuilder builder) {
        super.doBuilderRegister(builder);
        builder.registerCell(TYPE_TEST_VIEW_1_SCROLL, TestView1_scroll.class);

    }

    @Override
    public void doEngineRegister(TangramEngine engine) {
        super.doEngineRegister(engine);
        //engine添加滑动监听
        engine.register(SampleScrollSupport.class, new SampleScrollSupport(getRecyclerView()));
    }

    @Override
    public String getDataName() {
        return "data_1_scroll.json";
    }


}
