package com.lewis.tangramdemo.lesson6_tmall.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lewis.tangramdemo.R;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;
import com.tmall.wireless.tangram.util.ImageUtils;

/**
 * @CreateDate: 2019/1/8 上午10:22
 * @Author: Lewis Weng
 * @Description:
 */
public class TextImageView extends LinearLayout implements ITangramViewLifeCycle {
    private TextView textView;
    private ImageView imageView;
    private TextView textView2;
    private ImageView imageView2;

    private final int DEFAULT_WIDTH_DP = 50;
    private final int DEFAULT_HEIGHT_DP = 50;


    public TextImageView(Context context) {
        this(context, null);
    }

    public TextImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);

    }

    public TextImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LinearLayout root = (LinearLayout) inflate(getContext(), R.layout.view_text_image, this);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setOrientation(VERTICAL);
        textView = root.findViewById(R.id.tv_text);
        imageView = root.findViewById(R.id.iv_img);
        textView2 = root.findViewById(R.id.tv_text2);
        imageView2 = root.findViewById(R.id.iv_img2);
    }

    @Override
    public void cellInited(BaseCell cell) {
        int w = DEFAULT_WIDTH_DP;
        int h = DEFAULT_HEIGHT_DP;
        DisplayMetrics dm = new DisplayMetrics();
        if (!"".equals(cell.optStringParam("width"))) {
            w = Integer.valueOf(cell.optStringParam("width"));
        }
        if (!"".equals(cell.optStringParam("width"))) {
            h = Integer.valueOf(cell.optStringParam("height"));
        }

//        LinearLayout.LayoutParams params = (LayoutParams) imageView.getLayoutParams();
//        params.width = (int) (w * dm.density + 0.5f);
//        params.height = (int) (h * dm.density + 0.5f);
//        imageView.setLayoutParams(params);
//        imageView2.setLayoutParams(params);
    }

    @Override
    public void postBindView(BaseCell cell) {
        ImageUtils.doLoadImageUrl(imageView, cell.optStringParam("imgUrl"));
        textView.setText(cell.optStringParam("title"));
        ImageUtils.doLoadImageUrl(imageView2, cell.optStringParam("imgUrl2"));
        textView2.setText(cell.optStringParam("title2"));

    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
