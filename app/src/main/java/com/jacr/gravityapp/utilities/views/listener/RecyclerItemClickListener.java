package com.jacr.gravityapp.utilities.views.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Custom implementation in order to support Click events with an straightforward approach for
 * RecyclerView\'s items
 * <p/>
 * http://stackoverflow.com/questions/24471109/recyclerview-onclick
 * <p/>
 * Created by Jesus on 11/24/2015.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    //<editor-fold desc="Variables">

    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    //</editor-fold>

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    //<editor-fold desc="Overrides - Item Touch">

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    //</editor-fold>

}