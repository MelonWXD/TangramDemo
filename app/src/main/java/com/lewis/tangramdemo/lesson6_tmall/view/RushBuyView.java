package com.lewis.tangramdemo.lesson6_tmall.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lewis.tangramdemo.R;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;
import com.tmall.wireless.tangram.util.ImageUtils;

/**
 * @CreateDate: 2019/1/9 下午7:52
 * @Author: Lewis Weng
 * @Description:
 */
public class RushBuyView extends FrameLayout implements ITangramViewLifeCycle {

    private TextView titleTv;
    private TextView descTv1;
    private TextView descTv2;
    private ImageView descImg1;//descIv1 容易跟descv混了
    private ImageView descImg2;

    private double ratio = 1.0;//支持宽高比

    public RushBuyView(Context context) {
        this(context, null);
    }

    public RushBuyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RushBuyView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);

    }

    public RushBuyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LinearLayout root = (LinearLayout) inflate(context, R.layout.view_rush_buy, this);
        titleTv = root.findViewById(R.id.tv_title);
        descTv1 = root.findViewById(R.id.tv_desc1);
        descTv2 = root.findViewById(R.id.tv_desc2);
        descImg1 = root.findViewById(R.id.iv_desc1);
        descImg2 = root.findViewById(R.id.iv_desc2);
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

    /**
     * 没做非空判断的都是必须参数
     */
    @Override
    public void postBindView(BaseCell cell) {
        ImageUtils.doLoadImageUrl(descImg1, cell.optStringParam("imgUrl1"));
        ImageUtils.doLoadImageUrl(descImg2, cell.optStringParam("imgUrl2"));

        GradientDrawable gd1 = (GradientDrawable) descTv1.getBackground();
        gd1.setColor(Color.parseColor(cell.optStringParam("tv1BgColor")));
        gd1.setStroke(cell.optIntParam("tv1StrokeWidth"), Color.parseColor(cell.optStringParam("tv1StrokeColor")));

        GradientDrawable gd2 = (GradientDrawable) descTv2.getBackground();
        gd2.setColor(Color.parseColor(cell.optStringParam("tv2BgColor")));
        gd2.setStroke(cell.optIntParam("tv2StrokeWidth"), Color.parseColor(cell.optStringParam("tv2StrokeColor")));
    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
