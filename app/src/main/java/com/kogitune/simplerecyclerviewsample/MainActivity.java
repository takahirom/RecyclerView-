package com.kogitune.simplerecyclerviewsample;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;


public class MainActivity extends Activity {

    private static final int DATASET_COUNT = 60;
    private static final String TAG = "MainActivity";
    private String[] mDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataset();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                Log.d(TAG, "onLayoutChildren1");
                final boolean statePreLayout1 = state.isPreLayout();
                super.onLayoutChildren(recycler, state);

                final boolean statePreLayout2 = state.isPreLayout();
                Log.d(TAG, "onLayoutChildren2 state1:" + statePreLayout1 + " state2" + statePreLayout2);
                if (statePreLayout2 == true) {
                    Log.d(TAG, "onLayoutChildren3:" + getChildCount());
                    for (int i = 0; i < getChildCount(); i++) {
                        startAnimation(getChildAt(i), i);
                    }
                }

            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CustomAdapter adapter = new CustomAdapter(mDataset);
        recyclerView.setAdapter(adapter);

    }

    private void startAnimation(View viewToAnimate, int position) {
        final Context context = viewToAnimate.getContext();


        final View v = viewToAnimate;
        ViewCompat.setElevation(v.findViewById(R.id.textView), 30);
        ValueAnimator anim = ValueAnimator.ofInt(0, 45);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                v.setPadding(0, 0, 0, val);
            }
        });
        anim.setInterpolator(new AccelerateDecelerateInterpolator());

        anim.setDuration(300);
        anim.setStartDelay(400 + position * 100);
        anim.start();
    }

    private void initDataset() {
        mDataset = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }

}
