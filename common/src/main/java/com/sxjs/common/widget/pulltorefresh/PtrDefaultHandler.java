package com.sxjs.common.widget.pulltorefresh;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AbsListView;


public abstract class PtrDefaultHandler {

    public static boolean canChildScrollUp(View view) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else if(view instanceof  RecyclerView){
                RecyclerView recyclerView = (RecyclerView)view;
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if(layoutManager instanceof LinearLayoutManager){
                    int position = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
                    return position != 0;
                }else{
                    if(layoutManager instanceof StaggeredGridLayoutManager ){
                        StaggeredGridLayoutManager stagger = (StaggeredGridLayoutManager) layoutManager;
                        int[] positions = stagger.findFirstCompletelyVisibleItemPositions(null);
                        return positions[0] != 0;
                    }else{
                        throw new RuntimeException("can not support this LayoutManager ");
                    }
                }
            }else{
                return view.getScrollY() > 0;
            }
        } else {
            return view.canScrollVertically(-1);
        }
    }

    /**
     * Default implement for check can perform pull to refresh
     *
     * @param frame
     * @param content
     * @param header
     * @return
     */
    public static boolean checkContentCanBePulledDown(PtrFrameLayout frame, View content, View header) {
        return !canChildScrollUp(content);
    }
}