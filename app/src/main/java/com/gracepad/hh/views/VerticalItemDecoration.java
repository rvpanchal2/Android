package com.gracepad.hh.views;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public VerticalItemDecoration(int space) {
        this.mSpace = space;
    }

    public VerticalItemDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace / 2;
        outRect.top = mSpace / 2;

        if (parent.getChildAdapterPosition(view) == 0)
            outRect.left = mSpace;
        else if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1)
            outRect.right = mSpace;
    }
}