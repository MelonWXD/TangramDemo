package com.lewis.tangramdemo.lesson1_click;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lewis.tangramdemo.R;
import com.lewis.tangramdemo.lesson1.TestView1;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;

/**
 * @CreateDate: 2018/12/26 下午3:17
 * @Author: Lewis Weng
 * @Description: 通过继承的方式更容易让读者看出来新增的内容
 */
public class TestView1_click extends TestView1 {

    public TestView1_click(Context context) {
        super(context);
    }

    public TestView1_click(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView1_click(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public void cellInited(BaseCell cell) {
        //在初始化的时候 添加点击事件
        //在cell的onClick中会通过引擎调用我们注册到引擎中的ClickSupport
        setOnClickListener(cell);
    }

}
