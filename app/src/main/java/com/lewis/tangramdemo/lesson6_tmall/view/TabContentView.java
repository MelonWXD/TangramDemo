package com.lewis.tangramdemo.lesson6_tmall.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
import java.util.concurrent.ConcurrentHashMap;

import static com.lewis.tangramdemo.lesson6_tmall.view.TabHeaderView.ARGS_KEY_POS;
import static com.lewis.tangramdemo.lesson6_tmall.view.TabHeaderView.EVENT_SRC_ID;
import static com.lewis.tangramdemo.lesson6_tmall.view.TabHeaderView.EVENT_TAB_CHANGED;

/**
 * @CreateDate: 2019/1/10 下午5:11
 * @Author: Lewis Weng
 * @Description:
 */
public class TabContentView extends FrameLayout implements ITangramViewLifeCycle {
    public static final String EVENT_SRC_ID_CONTENT = "tab_content";

    EventHandlerWrapper wrapper;
    BusSupport busSupport;
    ListView listView;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<ArrayList<String>> values;
    ArrayAdapter adapter;
    int oldPos = -1;
    float x, y;

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


    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {
        View root = inflate(context, R.layout.view_tab_content, this);
        listView = root.findViewById(R.id.lv_content);
        adapter = new ArrayAdapter<String>(context, R.layout.simple_list_item, data);
        listView.setAdapter(adapter);
        listView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        return true;  //需要接收到up事件
                    case MotionEvent.ACTION_MOVE:
                        return true; //需要接收到up事件
                    case MotionEvent.ACTION_UP:
                        if (event.getX() - x > 50)
                            changTab(oldPos - 1);
                        else if (x - event.getX() > 50)
                            changTab(oldPos + 1);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;

                }
                //因为tangram外层用了recyclerView 所以向上滑动的操作是被rV给吃掉了
                //所以横向滑动如果Y轴差过大，被rV吃掉，发给contentView的就是ACTION_CANCEL了
                //需要在rV中对Y轴做一定的距离判断，再下发给contentView
                return false;

            }
        });
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
        values.add(new ArrayList<String>(Arrays.asList("位置7", "位置7", "位置7", "位置7", "位置7", "位置7", "位置7", "位置7")));

    }

    public void handleEvent(Event event) {
        String pos = event.args.get(ARGS_KEY_POS);
        if (pos == null)
            return;
        changTab(Integer.parseInt(pos));

    }

    private void changTab(int newPos) {
        if (oldPos != newPos && newPos < values.size() && newPos >= 0) {
            oldPos = newPos;
            data.clear();
            data.addAll(values.get(newPos));
            adapter.notifyDataSetChanged();
            postTabChangedEvent(newPos);
        }
    }

    private ConcurrentHashMap<String, String> args = new ConcurrentHashMap<>();

    private void postTabChangedEvent(int newPos) {
        args.put(ARGS_KEY_POS, String.valueOf(newPos));
        Event e = BusSupport.obtainEvent(EVENT_TAB_CHANGED, EVENT_SRC_ID_CONTENT, args, null);
        busSupport.post(e);
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
