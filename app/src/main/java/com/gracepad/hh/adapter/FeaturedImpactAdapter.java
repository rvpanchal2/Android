package com.gracepad.hh.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

public class FeaturedImpactAdapter extends RecyclerView.Adapter<FeaturedImpactAdapter.ItemHolder> {

    private ArrayList<CharityObject> arrPost;
    private Activity act;

    public FeaturedImpactAdapter(Activity act, ArrayList<CharityObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public FeaturedImpactAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_featured_impact_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull FeaturedImpactAdapter.ItemHolder holder, int position) {
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
        LinearLayout lvItem;
//        DonutProgress donutProgress;

        ItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.layout_Row_FeaturedImpact);
            imgPost = itemView.findViewById(R.id.img_Row_FeaturedImpact_bg);
            txtName = itemView.findViewById(R.id.txt_Row_FeaturedImpact_Name);
            /*txtDays = itemView.findViewById(R.id.txt_Row_FeaturedImpact_Days);
            txtFund = itemView.findViewById(R.id.txt_Row_FeaturedImpact_Price);*/
            txtProgress = itemView.findViewById(R.id.txt_Row_FeaturedImpact_Progress);
//            donutProgress = itemView.findViewById(R.id.seek_Row_FeaturedImpact_Progress);

            lvItem.getLayoutParams().width = Constant.nWidth - Constant.dipToPx(act, 40);

            lvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_Row_FeaturedImpact:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}