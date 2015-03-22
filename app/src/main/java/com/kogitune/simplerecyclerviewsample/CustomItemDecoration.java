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
    private final Rect mDecorationOffset;
    private final Rect mViewBounds;

    public CustomItemDecoration(RecyclerView recyclerView, View itemView){
        mRecyclerView = recyclerView;

        mDecorationOffset = new Rect();
        getDecorationOffsets(recyclerView.getLayoutManager(), itemView, mDecorationOffset);
        System.out.println("getDecorationOffsets:" + mDecorationOffset);

        mViewBounds = new Rect();
        getViewBounds(itemView, mViewBounds);
        System.out.println("getViewBounds:"+ mViewBounds);
    }

    @DebugLog
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final Paint paint = new Paint();
        paint.setColor(Color.argb(55, 255, 0, 0));
        c.drawRect(mViewBounds,paint);
        paint.setColor(Color.argb(55, 0, 0, 255));
        c.drawRect(mDecorationOffset,paint);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final Paint paint = new Paint();
        paint.setColor(Color.argb(55, 255, 0, 0));
        c.drawRect(mViewBounds,paint);
        paint.setColor(Color.argb(55, 0, 0, 255));
        c.drawRect(mDecorationOffset,paint);
    }

//    @DebugLog
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
//        outRect.set(10,10,200,200);
//        Log.d(TAG, "index:" + ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewPosition());
//    }

    public static Rect getDecorationOffsets(RecyclerView.LayoutManager layoutManager, View view, Rect outDecorations) {
        outDecorations.left = layoutManager.getLeftDecorationWidth(view);
        outDecorations.right = layoutManager.getRightDecorationWidth(view);
        outDecorations.top = layoutManager.getTopDecorationHeight(view);
        outDecorations.bottom = layoutManager.getBottomDecorationHeight(view);

        return outDecorations;
    }

    public static Rect getViewBounds(View v, Rect outBounds) {
        outBounds.left = v.getLeft();
        outBounds.right = v.getRight();
        outBounds.top = v.getTop();
        outBounds.bottom = v.getBottom();
        return outBounds;
    }
}
