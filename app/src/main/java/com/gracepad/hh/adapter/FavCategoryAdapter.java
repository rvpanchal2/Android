package com.gracepad.hh.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.object.CategoryObject;

import java.util.ArrayList;

public class FavCategoryAdapter extends RecyclerView.Adapter<FavCategoryAdapter.ItemHolder> {

    private ArrayList<CategoryObject> arrPost;
    private Activity act;
    private boolean isFav;

    public FavCategoryAdapter(Activity act, ArrayList<CategoryObject> arrPost, boolean isFav) {
        this.act = act;
        this.arrPost = arrPost;
        this.isFav = isFav;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public FavCategoryAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_fav_category_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull FavCategoryAdapter.ItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrPost.size();
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

        AppCompatImageView imgProfile, imgAddRemove;
        RelativeLayout lvItem;

        ItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.layout_Row_FavCategory);
            imgProfile = itemView.findViewById(R.id.img_Row_FavCategory_Item);
            imgAddRemove = itemView.findViewById(R.id.img_Row_FavCategory_Remove);

            imgAddRemove.setImageResource(isFav ? R.drawable.ic_remove : R.drawable.ic_add);
            imgAddRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_Row_FavCategory_Remove:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}