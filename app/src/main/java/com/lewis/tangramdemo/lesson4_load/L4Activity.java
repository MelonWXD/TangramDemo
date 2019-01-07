package com.lewis.tangramdemo.lesson4_load;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.android.vlayout.Range;
import com.lewis.tangramdemo.CommonActivity;
import com.lewis.tangramdemo.lesson1.TestImageView;
import com.lewis.tangramdemo.lesson1.TestView1;
import com.lewis.tangramdemo.lesson1_click.TestView1_click;
import com.lewis.tangramdemo.utils.WeakHandler;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;
import com.tmall.wireless.tangram.core.adapter.GroupBasicAdapter;
import com.tmall.wireless.tangram.dataparser.concrete.Card;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.support.async.AsyncLoader;
import com.tmall.wireless.tangram.support.async.AsyncPageLoader;
import com.tmall.wireless.tangram.support.async.CardLoadSupport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.lewis.tangramdemo.lesson1.L1Activity.TYPE_TEST_IMAGE;
import static com.lewis.tangramdemo.lesson1.L1Activity.TYPE_TEST_VIEW_1;
import static com.lewis.tangramdemo.lesson1_click.L1Activity_click.TYPE_TEST_VIEW_1_CLICK;

/**
 * @CreateDate: 2019/1/2 上午10:10
 * @Author: Lewis Weng
 * @Description: 异步加载
 */
public class L4Activity extends CommonActivity {
    private static final String TAG = "wxdL4Activity";
    private WeakHandler uiHandler = new WeakHandler();


    @Override
    public void doBuilderRegister(TangramBuilder.InnerBuilder builder) {
        builder.registerCell(TYPE_TEST_VIEW_1, TestView1.class);
        builder.registerCell(TYPE_TEST_IMAGE, TestImageView.class);
        builder.registerCell(TYPE_TEST_VIEW_1_CLICK, TestView1_click.class);

    }

    @Override
    public void doEngineRegister(final TangramEngine engine) {
        //json中loadType=1的 调用通过这个loader加载
        AsyncLoader asyncLoader = new AsyncLoader() {
            @Override
            public void loadData(final Card card, @NonNull final LoadedCallback callback) {
                Log.i(TAG, "loadData: " + Thread.currentThread().getName());
                uiHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do loading
                        JSONArray cells = new JSONArray();
                        for (int i = 0; i < 6; i++) {
                            try {
                                //造数据假装请求得到的数据
                                JSONObject obj = new JSONObject();
                                obj.put("type", TYPE_TEST_VIEW_1);
                                obj.put("msg", "async loaded  load=" + card.load);
                                JSONObject style = new JSONObject();
                                style.put("bgColor", "#FF1111");
                                obj.put("style", style.toString());
                                cells.put(obj);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // callback.fail(false);
                        callback.finish(engine.parseComponent(cells));
                    }
                }, 200);
            }
        };
        //json中loadType = -1的 调用通过这个loader加载
        AsyncPageLoader pageLoader = new AsyncPageLoader() {
            @Override
            public void loadData(final int page, @NonNull final Card card, @NonNull final LoadedCallback callback) {
                JSONObject o = (JSONObject) card.getParams();
                Log.i(TAG, "loadPageData: page=" + card.page + " tName=" + Thread.currentThread().getName());
                uiHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //造假数据
                        JSONArray cells = new JSONArray();
                        for (int i = 0; i < 3; i++) {
                            try {
                                JSONObject obj = new JSONObject();
                                obj.put("type", TYPE_TEST_VIEW_1);
                                obj.put("msg", "async page loaded, load=" + card.load + " params: " + card.getParams().toString());
                                cells.put(obj);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        List<BaseCell> cs = engine.parseComponent(cells);

                        if (card.page == 1) {
                            GroupBasicAdapter<Card, ?> adapter = engine.getGroupBasicAdapter();

                            card.setCells(cs);
                            adapter.refreshWithoutNotify();
                            Range<Integer> range = adapter.getCardRange(card);

                            adapter.notifyItemRemoved(range.getLower());
                            adapter.notifyItemRangeInserted(range.getLower(), cs.size());

                        } else
                            card.addCells(cs);

                        //mock load 3 pages  分页加载
                        callback.finish(card.page != 3);
                        card.notifyDataChange();
                    }
                }, 1000);//这里延迟1s 来直观感受异步
            }
        };
        CardLoadSupport loadSupport = new CardLoadSupport(asyncLoader, pageLoader);
        engine.addCardLoadSupport(loadSupport);

        engine.setPreLoadNumber(3);
        engine.enableAutoLoadMore(true);
        //recycleView将滑动监听发送给engine 触发加载逻辑
        getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                engine.onScrolled();
            }
        });
    }

    @Override
    public String getDataName() {
        return "data_4.json";
    }
}
