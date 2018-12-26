package com.lewis.tangramdemo.lesson1;

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
public class TestView1 extends FrameLayout implements ITangramViewLifeCycle {
    private TextView textView;

    public TestView1(Context context) {
        super(context);
        init();
    }

    public TestView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item, this);
        textView = findViewById(R.id.title);
    }

    @Override
    public void cellInited(BaseCell cell) {

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
