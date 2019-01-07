package com.lewis.tangramdemo.lesson5_support_module;

import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.support.CellSupport;

/**
 * @CreateDate: 2019/1/3 上午11:47
 * @Author: Lewis Weng
 * @Description: 自定义组件CellSupport模块 暴露接口
 * 每个组件里可能会有一些重复逻辑，特别是采用通用 model 开发组件的时候
 */
public class CustomCellSupport extends CellSupport {


    /**
     * 这里模拟的通用业务就是每个view都有deprecated来判断是否显示
     * 没啥意义。。。
     */
    @Override
    public boolean isValid(BaseCell cell) {
        return !cell.optBoolParam("deprecated");
    }
}
