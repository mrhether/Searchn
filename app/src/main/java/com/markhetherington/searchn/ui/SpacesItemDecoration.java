package com.markhetherington.searchn.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by markhetherington on 15-08-17.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int mHalfSpace;

    public SpacesItemDecoration(int space) {
        this.mHalfSpace = space / 2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getPaddingLeft() != mHalfSpace) {
            parent.setPadding(mHalfSpace, mHalfSpace, mHalfSpace, mHalfSpace);
            parent.setClipToPadding(false);
        }

        outRect.top = mHalfSpace;
        outRect.bottom = mHalfSpace;
        outRect.left = mHalfSpace;
        outRect.right = mHalfSpace;
    }
}
