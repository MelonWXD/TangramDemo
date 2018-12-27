package com.lewis.tangramdemo.lesson2;

import android.widget.TextView;

import com.lewis.tangramdemo.CommonActivity;
import com.lewis.tangramdemo.R;
import com.lewis.tangramdemo.view.TestView;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;
import com.tmall.wireless.tangram.structure.viewcreator.ViewHolderCreator;

/**
 * @CreateDate: 2018/12/26 下午8:18
 * @Author: Lewis Weng
 * @Description:
 */
public class L2Activity extends CommonActivity {
    public static final String TYPE_TEST_HOLDER = "TestViewHolder";

    @Override
    public void doBuilderRegister(TangramBuilder.InnerBuilder builder) {
        builder.registerCell(TYPE_TEST_HOLDER, TestViewHolderCell.class,
                new ViewHolderCreator<>(
                        R.layout.item_holder
                        , TestViewHolder.class
                        , TextView.class));

    }

    @Override
    public void doEngineRegister(TangramEngine engine) {
    }

    @Override
    public String getDataName() {
        return "data_2.json";
    }
}
