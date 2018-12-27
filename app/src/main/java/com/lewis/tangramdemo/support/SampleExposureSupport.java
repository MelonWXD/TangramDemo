package com.lewis.tangramdemo.support;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.tmall.wireless.tangram.dataparser.concrete.Card;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.support.ExposureSupport;

/**
 * @CreateDate: 2018/12/27 上午11:04
 * @Author: Lewis Weng
 * @Description:
 */
public class SampleExposureSupport extends ExposureSupport {
    private static final String TAG = "SampleExposureSupport";


    /**
     * 布局的整体曝光
     */
    @Override
    public void onExposure(@NonNull Card card, int offset, int position) {
        Log.i(TAG, "布局整体--onExposure: " + card.getClass().getSimpleName()
                + ", offset=" + offset + ", position=" + position);
    }

    /**
     * 组件的整体曝光
     */
    @Override
    public void defaultExposureCell(@NonNull View targetView, @NonNull BaseCell cell, int type) {
        super.defaultExposureCell(targetView, cell, type);
        Log.i(TAG, "组件整体--onExposure: " + targetView.getClass().getSimpleName()
                + ", stringType=" + cell.stringType + ", type=" + type);
    }

    /**
     * 布局整体、组件整体的曝光调用都是框架层调用
     * 组件局部的曝光，则需要业务方在组件逻辑里自行调用：
     * ExposureSupport exposureSupport = serviceManager.getService(ExposureSupport.class);
     * if (exposureSupport != null) {
     * exposureSupport.onTrace(view, cell, type);
     * }
     */
    @Override
    public void defaultTrace(@NonNull View targetView, @NonNull BaseCell cell, int type) {
        super.defaultTrace(targetView, cell, type);
        Log.i(TAG, "组件局部--onExposure: " + targetView.getClass().getSimpleName()
                + ", stringType=" + cell.stringType + ", type=" + type);
    }
}
