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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<String> mDataSet;

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

    public CustomAdapter() {
        mDataSet = new ArrayList<String>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        final View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder Element " + position + " set.");
        //viewHolder.getTextView().setText(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void addAllItem(String[] dataset){
        mDataSet.addAll(Arrays.asList(dataset));
        notifyItemRangeInserted(1,dataset.length-3);
    }

    public void addItem(int position) {
        if (position > mDataSet.size()) return;

        mDataSet.add(position, "new object");
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        if (position >= mDataSet.size()) return;

        mDataSet.remove(position);
        notifyItemRemoved(position);
    }
}
