package com.lewis.tangramdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.lewis.tangramdemo.utils.AssetUtil;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * @CreateDate: 2018/12/26 下午3:30
 * @Author: Lewis Weng
 * @Description: 封装的base类
 */
public abstract class CommonActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TangramBuilder.InnerBuilder builder;
    TangramEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        recyclerView = findViewById(R.id.rv_main);

        //初始化 TangramBuilder
        builder = TangramBuilder.newInnerBuilder(this);
        doBuilderRegister(builder);

        //构造engine
        engine = builder.build();
        doEngineRegister(engine);

        //绑定
        engine.bindView(recyclerView);

        String json = new String(AssetUtil.getAssertsFile(this, getDataName()));
        JSONArray data = null;
        try {
            data = new JSONArray(json);
            engine.setData(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public abstract void doBuilderRegister(TangramBuilder.InnerBuilder builder);

    public abstract void doEngineRegister(TangramEngine engine);

    public abstract String getDataName();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (engine != null) {
            engine.destroy();
        }
    }
}
