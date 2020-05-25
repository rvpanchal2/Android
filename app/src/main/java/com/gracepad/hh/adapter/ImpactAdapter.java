package com.gracepad.hh.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.object.CharityObject;

import java.util.ArrayList;

public class ImpactAdapter extends RecyclerView.Adapter<ImpactAdapter.ItemHolder> {

    private ArrayList<CharityObject> arrPost;
    private Activity act;

    public ImpactAdapter(Activity act, ArrayList<CharityObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public ImpactAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_implact_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ImpactAdapter.ItemHolder holder, int position) {
        CharityObject object = getItem(position);

        Glide.with(act).load(R.drawable.ic_temp)
                .override(Constant.nWidth, Target.SIZE_ORIGINAL)
                .placeholder(R.color.placeholder_color)
                .centerCrop()
                .into(holder.imgPost);

        CharityRequestAdapter requestAdapter = new CharityRequestAdapter(act, object.getArrCharityRequest());
        holder.rvGoal.setAdapter(requestAdapter);
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

        RecyclerView rvGoal;
        AppCompatImageView imgPost, imgShare;
        AppCompatTextView txtName, txtDays, txtFund, txtLike, txtComment, txtViews;
        CardView lvItem;

        ItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.card_Row_Impact);
            imgPost = itemView.findViewById(R.id.img_Row_Impact_Bg);
            imgShare = itemView.findViewById(R.id.img_Row_Impact_Share);
            txtName = itemView.findViewById(R.id.txt_Row_Impact_Name);
            txtDays = itemView.findViewById(R.id.txt_Row_Impact_Days);
            txtFund = itemView.findViewById(R.id.txt_Row_Impact_Fund);
            txtLike = itemView.findViewById(R.id.txt_Row_Impact_Like);
            txtComment = itemView.findViewById(R.id.txt_Row_Impact_Comment);
            txtViews = itemView.findViewById(R.id.txt_Row_Impact_Views);
            rvGoal = itemView.findViewById(R.id.rv_Row_Impact_Goal);

            lvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.card_Row_Impact:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}