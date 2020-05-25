package com.gracepad.hh.views;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public HorizontalItemDecoration(int space) {
        this.mSpace = space;
    }

    public HorizontalItemDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace / 2;
        outRect.right = mSpace / 2;
        outRect.bottom = 0;
        outRect.top = 0;

        if (parent.getChildAdapterPosition(view) == 0)
            outRect.left = mSpace;
        else if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1)
            outRect.right = mSpace;
    }
}