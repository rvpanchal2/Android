package com.gracepad.hh.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.common.MyUtils;
import com.gracepad.hh.object.CategoryObject;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemHolder> {

    private ArrayList<CategoryObject> arrPost;
    private Activity act;

    public CategoryAdapter(Activity act, ArrayList<CategoryObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public CategoryAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_category_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ItemHolder holder, int position) {
        MyUtils.Log(position + " : " + (arrPost.size() - 1));
        if (position <= arrPost.size() - 1) {
            holder.imgCategory.setImageResource(R.drawable.ic_temp_category);
            holder.lvItem.setBackgroundResource(R.drawable.rounded_corner_category);
        } else {
            holder.imgCategory.setImageResource(R.drawable.ic_category_all);
            holder.lvItem.setBackgroundResource(R.drawable.rounded_border_edt);
        }
    }

    @Override
    public int getItemCount() {
        return arrPost.size() + 1;
    }

    private CategoryObject getItem(int position) {
        return arrPost.get(position);
    }

    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatImageView imgCategory;
        LinearLayout lvItem;

        ItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.layout_Row_Category);
            imgCategory = itemView.findViewById(R.id.img_Row_Category_Item);

            lvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_Row_Category:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}