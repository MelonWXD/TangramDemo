package com.lewis.tangramdemo.lesson3_exposure;

import com.lewis.tangramdemo.CommonActivity;
import com.lewis.tangramdemo.lesson1.L1Activity;
import com.lewis.tangramdemo.support.SampleExposureSupport;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;

/**
 * @CreateDate: 2018/12/26 下午8:18
 * @Author: Lewis Weng
 * @Description: 曝光处理
 */
public class L3Activity extends L1Activity {

    @Override
    public void doBuilderRegister(TangramBuilder.InnerBuilder builder) {
        //L1中已经注册了
        super.doBuilderRegister(builder);
    }

    @Override
    public void doEngineRegister(TangramEngine engine) {
        super.doEngineRegister(engine);
        engine.addExposureSupport(new SampleExposureSupport());
    }

    @Override
    public String getDataName() {
        return "data_3_exposure.json";
    }
}
