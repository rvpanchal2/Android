package com.gracepad.hh.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.object.CharityObject;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemHolder> {

    private ArrayList<CharityObject> arrPost;
    private Activity act;

    public SearchAdapter(Activity act, ArrayList<CharityObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public SearchAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_search_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ItemHolder holder, int position) {
        CharityObject object = getItem(position);

        Glide.with(act).load(R.drawable.ic_temp)
                .override(Constant.nWidth, Target.SIZE_ORIGINAL)
                .placeholder(R.color.placeholder_color)
                .centerCrop()
                .into(holder.imgPost);
    }

    @Override
    public int getItemCount() {
        return arrPost.size();
    }

    private CharityObject getItem(int position) {
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

        AppCompatImageView imgPost;
        AppCompatTextView txtName, /*txtDays, txtFund,*/ txtProgress;
        RelativeLayout lvItem;
//        DonutProgress donutProgress;

        ItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.layout_Row_Search);
            imgPost = itemView.findViewById(R.id.img_Row_Search_bg);
            txtName = itemView.findViewById(R.id.txt_Row_Search_Name);
//            txtDays = itemView.findViewById(R.id.txt_Row_Search_Days);
//            txtFund = itemView.findViewById(R.id.txt_Row_Search_Price);
            txtProgress = itemView.findViewById(R.id.txt_Row_Search_Progress);
//            donutProgress = itemView.findViewById(R.id.seek_Row_Search_Progress);

            lvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_Row_Search:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}