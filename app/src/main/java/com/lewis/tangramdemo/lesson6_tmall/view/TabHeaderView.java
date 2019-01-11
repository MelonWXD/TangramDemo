package com.lewis.tangramdemo.lesson6_tmall.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.GsonUtils;
import com.lewis.tangramdemo.R;
import com.tmall.wireless.tangram.core.service.ServiceManager;
import com.tmall.wireless.tangram.eventbus.BusSupport;
import com.tmall.wireless.tangram.eventbus.Event;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;
import com.tmall.wireless.tangram.support.TimerSupport;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @CreateDate: 2019/1/10 下午12:05
 * @Author: Lewis Weng
 * @Description:
 */
public class TabHeaderView extends FrameLayout implements ITangramViewLifeCycle, TabLayout.OnTabSelectedListener {
    public static final String EVENT_TAB_CHANGED = "tab_chg";
    public static final String EVENT_SRC_ID = "tab_header";
    public static final String ARGS_KEY_POS = "pos";
    private TabLayout tabLayout;
    private ArrayList<String> titleList;
    private BusSupport busSupport;

    public TabHeaderView(Context context) {
        this(context, null);
    }

    public TabHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);

    }

    public TabHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initTabLayout(context);
    }

    private void initTabLayout(Context context) {
        View root = inflate(context, R.layout.view_tab_header, this);
        tabLayout = root.findViewById(R.id.tl_header);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.design_default_color_primary));
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void cellInited(BaseCell cell) {
        titleList = GsonUtils.fromJson(cell.optJsonArrayParam("title").toString(), ArrayList.class);
        if (cell.serviceManager != null) {
            busSupport = cell.serviceManager.getService(BusSupport.class);
        }
    }

    @Override
    public void postBindView(BaseCell cell) {
        for (String text : titleList) {
            tabLayout.addTab(tabLayout.newTab().setText(text));
        }

    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        ConcurrentHashMap<String, String> args = new ConcurrentHashMap<>();
        args.put(ARGS_KEY_POS, String.valueOf(tab.getPosition()));
        Event event = BusSupport.obtainEvent(EVENT_TAB_CHANGED, EVENT_SRC_ID, args, null);
        busSupport.post(event);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


}
