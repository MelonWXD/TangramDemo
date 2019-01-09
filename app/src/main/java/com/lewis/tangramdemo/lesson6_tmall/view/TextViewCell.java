package com.lewis.tangramdemo.lesson6_tmall.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lewis.tangramdemo.R;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;

/**
 * @CreateDate: 2019/1/8 下午5:05
 * @Author: Lewis Weng
 * @Description:
 */
public class TextViewCell extends FrameLayout implements ITangramViewLifeCycle {
    public TextView textView;
    public double ratio = 1.0;//支持宽高比

    public TextViewCell(Context context) {
        super(context);
        init();
    }

    public TextViewCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.cell_text, this);
        textView = findViewById(R.id.tv);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, (int) (width / ratio));
    }

    @Override
    public void cellInited(BaseCell cell) {
        String ratioStr = cell.optStringParam("ratio");
        if (!"".equals(ratioStr))
            ratio = Double.parseDouble(ratioStr);
    }

    @Override
    public void postBindView(BaseCell cell) {
        //在绑定数据时，从json中拿具体数据
        textView.setText((String) cell.optParam("msg"));
        if (cell.optBoolParam("isBold"))
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        else {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        textView.setTextSize((Integer) cell.optParam("size"));
        textView.setTextColor(Color.parseColor((String) cell.optParam("textColor")));

    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
