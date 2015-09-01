package com.markhetherington.searchn.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by markhetherington on 15-08-17.
 */
public abstract class RecyclerViewInfinteScrollListener extends RecyclerView.OnScrollListener {

    boolean mCanLoadMore = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) return;

        visibleItemCount = layoutManager.getChildCount();
        totalItemCount = layoutManager.getItemCount();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] firstVisibleItems = null;
            firstVisibleItems = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(firstVisibleItems);
            if (firstVisibleItems != null && firstVisibleItems.length > 0) {
                pastVisiblesItems = firstVisibleItems[0];
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            pastVisiblesItems = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else {
            throw new RuntimeException("Infinite LayoutManger " + layoutManager.getClass().getSimpleName() + "not supported");
        }

        if (mCanLoadMore) {
            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                mCanLoadMore = false;
                shouldLoadMore();
            }
        }
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.mCanLoadMore = canLoadMore;
    }

    public abstract void shouldLoadMore();

}
