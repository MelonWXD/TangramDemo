package com.lewis.tangramdemo.lesson6_tmall.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.lewis.tangramdemo.R;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;
import com.tmall.wireless.tangram.support.TimerSupport;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @CreateDate: 2019/1/9 下午5:10
 * @Author: Lewis Weng
 * @Description:
 */
public class TextTimerCell extends TextViewCell implements TimerSupport.OnTickListener {
    private ArrayList<String> countTimeList;
    boolean hasCountDown;
    Date nowCount;
    Date nowTime;
    String h, m, s;

    public TextTimerCell(Context context) {
        super(context);
    }

    public TextTimerCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextTimerCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void cellInited(BaseCell cell) {
        super.cellInited(cell);
        hasCountDown = cell.optBoolParam("hasCountDown");
        if (hasCountDown) {
            JSONArray countTimes = cell.optJsonArrayParam("countTimes");
            countTimeList = GsonUtils.fromJson(countTimes.toString(), ArrayList.class);
            if (countTimeList.size() > 0)
                nowCount = new Date(Long.parseLong(countTimeList.get(0)));
            if (cell.serviceManager != null) {
                TimerSupport timerSupport = cell.serviceManager.getService(TimerSupport.class);
                if (timerSupport != null && !timerSupport.isRegistered(this)) {
                    //第一个参数4是单位秒，第二个参数是接口回调，第三个参数是立即执行
                    timerSupport.register(1, this, true);
                }
            }
        }
    }


    @Override
    public void onTick() {
        if (nowCount == null || countTimeList.size() == 0) {
            textTv.setVisibility(GONE);
            return;
        }
        nowTime = new Date();

        while (nowTime.getTime() > nowCount.getTime()) {
            countTimeList.remove(0);
            if (countTimeList.size() == 0) {
                textTv.setVisibility(GONE);
                return;
            }
            nowCount = new Date(Long.parseLong(countTimeList.get(0)));
        }

        int nh = nowCount.getHours() - nowTime.getHours();
        h = nh < 10 ? String.format("0%d", nh) : String.valueOf(nh);
        int nm = nowCount.getMinutes() - nowTime.getMinutes();
        m = nm < 10 ? String.format("0%d", nm) : String.valueOf(nm);
        int ns = nowCount.getSeconds() - nowTime.getSeconds();
        s = ns < 10 ? String.format("0%d", ns) : String.valueOf(ns);

        textTv.setText(String.format(getResources().getString(R.string.countdown_time)
                , h
                , m
                , s));
    }
}