package com.lewis.tangramdemo.lesson1;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;
import com.tmall.wireless.tangram.util.ImageUtils;

/**
 * @CreateDate: 2018/12/27 下午2:31
 * @Author: Lewis Weng
 * @Description:
 */
public class TestImageView extends AppCompatImageView implements ITangramViewLifeCycle {
    public TestImageView(Context context) {
        super(context);
    }

    public TestImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void cellInited(BaseCell cell) {
        Log.i("wxddd", "cellInited: " + getWidth() + "," + getHeight());
    }

    @Override
    public void postBindView(BaseCell cell) {
        //从json中取到url 通过我们设置的工具App中设置的是Glide来加载图片
        ImageUtils.doLoadImageUrl(this, cell.optStringParam("imgUrl"));
        Log.i("wxddd", "postBindView: " + getWidth() + "," + getHeight());

    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
