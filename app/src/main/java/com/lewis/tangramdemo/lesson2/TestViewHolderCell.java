package com.lewis.tangramdemo.lesson2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.tmall.wireless.tangram.MVHelper;
import com.tmall.wireless.tangram.structure.BaseCell;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @CreateDate: 2018/12/26 下午8:14
 * @Author: Lewis Weng
 * @Description: 采用自定义 model 和自定义 View
 * 提供了注册自定义 model 的兼容模式开发组件。这个时候就需要写自定义 model 和自定义 View 两部分了。
 * 避免重复解析 json
 */
public class TestViewHolderCell extends BaseCell<TextView> {

    String msg;

    /**
     * 解析数据业务数据，可以将解析值缓存到成员变量里
     */
    @Override
    public void parseWith(@NonNull JSONObject data, @NonNull MVHelper resolver) {
        try {
            msg = data.getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.parseWith(data, resolver);
    }

    /**
     * 解析数据样式数据，可以将解析值缓存到成员变量里
     */
    @Override
    public void parseStyle(@Nullable JSONObject data) {
        super.parseStyle(data);
    }

    @Override
    public void bindView(@NonNull TextView view) {
        super.bindView(view);
        view.setText("我是自定义model中的view,我的msg是:" + msg);
    }

    @Override
    public void postBindView(@NonNull TextView view) {
        super.postBindView(view);
    }

    @Override
    public void unbindView(@NonNull TextView view) {
        super.unbindView(view);
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }
}
