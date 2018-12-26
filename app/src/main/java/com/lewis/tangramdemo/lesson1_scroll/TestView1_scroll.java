package com.lewis.tangramdemo.lesson1_scroll;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.lewis.tangramdemo.lesson1_click.TestView1_click;
import com.lewis.tangramdemo.support.SampleScrollSupport;
import com.tmall.wireless.tangram.structure.BaseCell;

/**
 * @CreateDate: 2018/12/26 下午3:17
 * @Author: Lewis Weng
 * @Description:
 */
public class TestView1_scroll extends TestView1_click implements SampleScrollSupport.IScrollListener {
    private static final String TAG = "L1Activity_scroll";

    public TestView1_scroll(Context context) {
        super(context);
        init();
    }

    public TestView1_scroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView1_scroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    public void cellInited(BaseCell cell) {
        super.cellInited(cell);
        if (cell.serviceManager != null) {
            SampleScrollSupport scrollSupport = cell.serviceManager.getService(SampleScrollSupport.class);
            //拿到之前注册到引擎中的support 来注册观察者
            scrollSupport.register(this);
        }
    }


    //这两个方法会收到SampleScrollSupport中通知的滑动事件
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        Log.i(TAG, "onScrollStateChanged: ");

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        Log.i(TAG, "onScrolled: ");

    }

}
