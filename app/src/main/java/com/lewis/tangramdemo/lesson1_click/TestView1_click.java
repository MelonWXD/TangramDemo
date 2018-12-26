package com.lewis.tangramdemo.lesson1_click;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lewis.tangramdemo.R;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;

/**
 * @CreateDate: 2018/12/26 下午3:17
 * @Author: Lewis Weng
 * @Description:
 */
public class TestView1_click extends FrameLayout implements ITangramViewLifeCycle {
    private TextView textView;

    public TestView1_click(Context context) {
        super(context);
        init();
    }

    public TestView1_click(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView1_click(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item, this);
        textView = findViewById(R.id.title);
    }

    @Override
    public void cellInited(BaseCell cell) {
        //在初始化的时候 添加点击事件
        //在cell的onClick中会通过引擎调用我们注册到引擎中的ClickSupport
        setOnClickListener(cell);
    }

    @Override
    public void postBindView(BaseCell cell) {
        //在绑定数据时，从json中拿具体数据
        textView.setText((String) cell.optParam("msg"));
    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
