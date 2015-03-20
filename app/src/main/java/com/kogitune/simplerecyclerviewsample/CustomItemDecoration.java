package com.kogitune.simplerecyclerviewsample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import hugo.weaving.DebugLog;

/**
 * Created by takam on 2015/03/20.
 */
public class CustomItemDecoration extends RecyclerView.ItemDecoration{
    private static final String TAG = "CustomItemDecoration";
    private final RecyclerView mRecyclerView;
    private final RecyclerView.ViewHolder mAnimationItem;

    public CustomItemDecoration(RecyclerView recyclerView, RecyclerView.ViewHolder animationItem){
        mRecyclerView = recyclerView;
        mAnimationItem = animationItem;
    }

    @DebugLog
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final Paint paint = new Paint();
        paint.setColor(Color.argb(55, 255, 0, 0));
        c.drawRect(0,0,100,100,paint);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final Paint paint = new Paint();
        paint.setColor(Color.argb(55, 0, 255, 0));
        c.drawRect(50,0,150,100,paint);
    }

    @DebugLog
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(10,10,200,200);
        Log.d(TAG, "index:" + ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewPosition());
    }
}
