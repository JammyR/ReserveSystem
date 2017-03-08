package com.example.jammy.ReserveSystem.base;

import com.example.jammy.ReserveSystem.R;

/**
 * Created by Jammy on 2017/3/7.
 */

public class LoadMoreView extends com.chad.library.adapter.base.loadmore.LoadMoreView {
    @Override public int getLayoutId() {
        return R.layout.view_load_fail;
    }

    /**
     * 如果返回true，数据全部加载完毕后会隐藏加载更多
     * 如果返回false，数据全部加载完毕后会显示getLoadEndViewId()布局
     */
    @Override public boolean isLoadEndGone() {
        return false;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    /**
     * isLoadEndGone()为true，可以返回0
     * isLoadEndGone()为false，不能返回0
     */
    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

    @Override
    public void setLoadMoreStatus(int loadMoreStatus) {
        super.setLoadMoreStatus(loadMoreStatus);
    }
}
