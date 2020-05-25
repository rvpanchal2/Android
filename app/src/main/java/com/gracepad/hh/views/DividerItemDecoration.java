package com.gracepad.hh.views;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public DividerItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace / 2;
        outRect.right = mSpace / 2;
        outRect.bottom = mSpace;
        outRect.top = 0;

        if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == 1)
            outRect.top = mSpace;
    }
}