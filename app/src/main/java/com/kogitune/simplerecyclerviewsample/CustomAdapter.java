/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.kogitune.simplerecyclerviewsample;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private String[] mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final View rootView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Element " + getPosition() + " clicked.", Toast.LENGTH_LONG).show();
                }
            });
            rootView = v;
            textView = (TextView) v.findViewById(R.id.textView);
        }

        public View getRootView() {
            return rootView;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public CustomAdapter(String[] dataSet) {
        mDataSet = dataSet;
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "CreateViewHolder");
        final View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        startAnimation(viewHolder.rootView, position);
        viewHolder.getTextView().setText(mDataSet[position]);
    }

    private void startAnimation(View viewToAnimate,int position) {
        final Context context = viewToAnimate.getContext();
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(animation);


        final View v = viewToAnimate;
        ViewCompat.setElevation(v.findViewById(R.id.textView), 30);
        ValueAnimator anim = ValueAnimator.ofInt(0, 45);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) v.getLayoutParams();
                layoutParams.bottomMargin = val;
                v.setLayoutParams(layoutParams);
            }
        });
        anim.setInterpolator(new AccelerateDecelerateInterpolator());

        anim.setDuration(300);
        anim.setStartDelay(400 + position * 100);
        anim.start();

    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
