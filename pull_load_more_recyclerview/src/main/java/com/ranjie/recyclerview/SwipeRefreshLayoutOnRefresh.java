package com.ranjie.recyclerview;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by WuXiaolong
 * on 2015/7/7.
 * github:https://github.com/WuXiaolong/PullLoadMoreRecyclerView
 */
public class SwipeRefreshLayoutOnRefresh implements SwipeRefreshLayout.OnRefreshListener {
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;

    public SwipeRefreshLayoutOnRefresh(PullLoadMoreRecyclerView pullLoadMoreRecyclerView) {
        this.mPullLoadMoreRecyclerView = pullLoadMoreRecyclerView;
    }

    @Override
    public void onRefresh() {
        if (!mPullLoadMoreRecyclerView.isRefresh()) {
            mPullLoadMoreRecyclerView.setIsRefresh(true);
            mPullLoadMoreRecyclerView.refresh();
        }
    }
}
