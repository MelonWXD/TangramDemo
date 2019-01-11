package com.lewis.tangramdemo.lesson6_tmall.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.lewis.tangramdemo.R;
import com.tmall.wireless.tangram.eventbus.BusSupport;
import com.tmall.wireless.tangram.eventbus.Event;
import com.tmall.wireless.tangram.eventbus.EventHandlerWrapper;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lewis.tangramdemo.lesson6_tmall.view.TabHeaderView.ARGS_KEY_POS;
import static com.lewis.tangramdemo.lesson6_tmall.view.TabHeaderView.EVENT_SRC_ID;
import static com.lewis.tangramdemo.lesson6_tmall.view.TabHeaderView.EVENT_TAB_CHANGED;

/**
 * @CreateDate: 2019/1/10 下午5:11
 * @Author: Lewis Weng
 * @Description:
 */
public class TabContentView extends FrameLayout implements ITangramViewLifeCycle {
    EventHandlerWrapper wrapper;
    BusSupport busSupport;
    ListView listView;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<ArrayList<String>> values;
    ArrayAdapter adapter;
    int oldPos = -1;

    public TabContentView(Context context) {
        this(context, null);
    }

    public TabContentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);

    }

    public TabContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View root = inflate(context, R.layout.view_tab_content, this);
        listView = root.findViewById(R.id.lv_content);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }


    @Override
    public void cellInited(BaseCell cell) {

        if (cell.serviceManager != null) {
            busSupport = cell.serviceManager.getService(BusSupport.class);
            wrapper = BusSupport.wrapEventHandler(EVENT_TAB_CHANGED, EVENT_SRC_ID, this, "handleEvent");
            busSupport.register(wrapper);
        }
        //放这里初始化,假装values是从json中解析来的
        values = new ArrayList<>();
        values.add(new ArrayList<String>(Arrays.asList("位置1", "位置1", "位置1", "位置1", "位置1", "位置1", "位置1", "位置1")));
        values.add(new ArrayList<String>(Arrays.asList("位置2", "位置2", "位置2", "位置2", "位置2", "位置2", "位置2", "位置2")));
        values.add(new ArrayList<String>(Arrays.asList("位置3", "位置3", "位置3", "位置3", "位置3", "位置3", "位置3", "位置3")));
        values.add(new ArrayList<String>(Arrays.asList("位置4", "位置4", "位置4", "位置4", "位置4", "位置4", "位置4", "位置4")));
        values.add(new ArrayList<String>(Arrays.asList("位置5", "位置5", "位置5", "位置5", "位置5", "位置5", "位置5", "位置5")));
        values.add(new ArrayList<String>(Arrays.asList("位置6", "位置6", "位置6", "位置6", "位置6", "位置6", "位置6", "位置6")));

    }

    public void handleEvent(Event event) {
        String pos = event.args.get(ARGS_KEY_POS);
        if (pos == null)
            return;
        changTab(Integer.parseInt(pos));

    }

    private void changTab(int newPos) {
        if (oldPos != newPos && newPos < values.size()) {
            oldPos = newPos;
            data.clear();
            data.addAll(values.get(newPos));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void postBindView(BaseCell cell) {
        if (oldPos < 0)
            changTab(0);
    }

    @Override
    public void postUnBindView(BaseCell cell) {
        if (busSupport != null)
            busSupport.unregister(wrapper);
    }
}
