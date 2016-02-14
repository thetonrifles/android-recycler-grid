package com.thetonrifles.recyclergrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

public class MyLayoutManager extends RecyclerView.LayoutManager {

    private static final int SCROLL_DISTANCE = 80; // dp

    private int mColumns;
    private int mFirstPosition = 0;
    private final int mScrollDistance;

    public MyLayoutManager(Context c, int columns) {
        final DisplayMetrics dm = c.getResources().getDisplayMetrics();
        mScrollDistance = (int) (SCROLL_DISTANCE * dm.density + 0.5f);
        mColumns = columns;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }

    /**
     * This method is called for rendering initial layout as well as
     * every time a notification method is invoked on adapter.
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        // getting recycler view bottom length
        final int parentBottom = getHeight() - getPaddingBottom();
        // getting previous top rendered view (null in case it's first time
        // we attach views to the recycler view)
        final View oldTopView = getChildCount() > 0 ? getChildAt(0) : null;
        // getting top rendered view top-padding
        // in case there is not top rendered view
        // this value is the recycler view padding top
        int oldTop = getPaddingTop();
        if (oldTopView != null) {
            oldTop = oldTopView.getTop();
        }
        // detach all views making them available
        // for usage in scrap heap
        detachAndScrapAttachedViews(recycler);
        // rendering views
        int top, left, right, bottom;
        top = oldTop;
        final int count = state.getItemCount();
        for (int i = 0; mFirstPosition + i < count && top < parentBottom; i++) {
            // getting view from recycler and render
            View v = recycler.getViewForPosition(mFirstPosition + i);
            addView(v, i);
            measureChildWithMargins(v, 0, 0);
            // evaluating horizontal index
            int k = i % mColumns;
            // evaluating position
            bottom = top + getDecoratedMeasuredHeight(v);
            left = getPaddingLeft() + (k * (getWidth()/mColumns));
            right = left + (getWidth()/mColumns);
            layoutDecorated(v, left, top, right, bottom);
            if (k == mColumns - 1) {
                top = bottom;
            }
        }
    }

    private int getFirstItemSlots() {
        return mColumns / 2;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int scrolled = 0;
        final int left = getPaddingLeft();
        final int right = getWidth() - getPaddingRight();
        if (dy < 0) {
            while (scrolled > dy) {
                final View topView = getChildAt(0);
                final int hangingTop = Math.max(-getDecoratedTop(topView), 0);
                final int scrollBy = Math.min(scrolled - dy, hangingTop);
                scrolled -= scrollBy;
                offsetChildrenVertical(scrollBy);
                if (mFirstPosition > 0 && scrolled > dy) {
                    mFirstPosition--;
                    View v = recycler.getViewForPosition(mFirstPosition);
                    addView(v, 0);
                    measureChildWithMargins(v, 0, 0);
                    final int bottom = getDecoratedTop(topView);
                    final int top = bottom - getDecoratedMeasuredHeight(v);
                    layoutDecorated(v, left, top, right, bottom);
                } else {
                    break;
                }
            }
        } else if (dy > 0) {
            final int parentHeight = getHeight();
            while (scrolled < dy) {
                final View bottomView = getChildAt(getChildCount() - 1);
                final int hangingBottom =
                        Math.max(getDecoratedBottom(bottomView) - parentHeight, 0);
                final int scrollBy = -Math.min(dy - scrolled, hangingBottom);
                scrolled -= scrollBy;
                offsetChildrenVertical(scrollBy);
                if (scrolled < dy && getItemCount() > mFirstPosition + getChildCount()) {
                    View v = recycler.getViewForPosition(mFirstPosition + getChildCount());
                    final int top = getDecoratedBottom(getChildAt(getChildCount() - 1));
                    addView(v);
                    measureChildWithMargins(v, 0, 0);
                    final int bottom = top + getDecoratedMeasuredHeight(v);
                    layoutDecorated(v, left, top, right, bottom);
                } else {
                    break;
                }
            }
        }
        recycleViewsOutOfBounds(recycler);
        return scrolled;
    }


    public void recycleViewsOutOfBounds(RecyclerView.Recycler recycler) {
        final int childCount = getChildCount();
        final int parentWidth = getWidth();
        final int parentHeight = getHeight();
        boolean foundFirst = false;
        int first = 0;
        int last = 0;
        for (int i = 0; i < childCount; i++) {
            final View v = getChildAt(i);
            if (v.hasFocus() || (getDecoratedRight(v) >= 0 &&
                    getDecoratedLeft(v) <= parentWidth &&
                    getDecoratedBottom(v) >= 0 &&
                    getDecoratedTop(v) <= parentHeight)) {
                if (!foundFirst) {
                    first = i;
                    foundFirst = true;
                }
                last = i;
            }
        }
        for (int i = childCount - 1; i > last; i--) {
            removeAndRecycleViewAt(i, recycler);
        }
        for (int i = first - 1; i >= 0; i--) {
            removeAndRecycleViewAt(i, recycler);
        }
        if (getChildCount() == 0) {
            mFirstPosition = 0;
        } else {
            mFirstPosition += first;
        }
    }

    @Override
    public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter) {
        // if adapter is set again, we completely
        // scrap the existing layout
        removeAllViews();
    }

}
