package com.lewis.tangramdemo.lesson5_support_module;

import android.util.Log;
import android.view.View;

import com.alibaba.android.vlayout.layout.FixAreaLayoutHelper;
import com.tmall.wireless.tangram.dataparser.concrete.Card;
import com.tmall.wireless.tangram.support.CardSupport;

/**
 * @CreateDate: 2019/1/7 上午11:11
 * @Author: Lewis Weng
 * @Description: 目前暂时没发现有啥用处
 * 如果card的style含有"bgImgUrl"字段就会回调到这里来
 * 布局辅助模块，主要处理布局背景加载的回调，让业务方有能力去控制相关逻辑
 */
public class CustomCardSupport extends CardSupport {
    @Override
    public void onBindBackgroundView(View layoutView, Card card) {
        Log.i("wxddd", "onBindBackgroundView: " + card);
    }

    @Override
    public void onUnbindBackgroundView(View layoutView, Card card) {
        super.onUnbindBackgroundView(layoutView, card);
    }

    @Override
    public FixAreaLayoutHelper.FixViewAnimatorHelper onGetFixViewAppearAnimator(Card card) {
        return super.onGetFixViewAppearAnimator(card);
    }
}
