package com.gracepad.hh.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.object.CharityRequestObject;

import java.util.ArrayList;

public class CharityRequestAdapter extends RecyclerView.Adapter<CharityRequestAdapter.ItemHolder> {

    private ArrayList<CharityRequestObject> arrPost;
    private Activity act;

    public CharityRequestAdapter(Activity act, ArrayList<CharityRequestObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public CharityRequestAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_charity_request_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CharityRequestAdapter.ItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrPost.size();
    }

    private CharityRequestObject getItem(int position) {
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

//        RecyclerView rvGoal;
//        AppCompatImageView imgProfile, imgShare;
//        AppCompatTextView txtName, txtDays, txtFund, txtLike, txtComment, txtViews;
//        LinearLayout lvItem;

        ItemHolder(View itemView) {
            super(itemView);
//            lvItem = itemView.findViewById(R.id.layout_Row_Impact);
//            imgProfile = itemView.findViewById(R.id.img_Row_Impact_Bg);
//            imgShare = itemView.findViewById(R.id.img_Row_Impact_Share);
//            txtName = itemView.findViewById(R.id.txt_Row_Impact_Name);
//            txtDays = itemView.findViewById(R.id.txt_Row_Impact_Days);
//            txtFund = itemView.findViewById(R.id.txt_Row_Impact_Fund);
//            txtLike = itemView.findViewById(R.id.txt_Row_Impact_Like);
//            txtComment = itemView.findViewById(R.id.txt_Row_Impact_Comment);
//            txtViews = itemView.findViewById(R.id.txt_Row_Impact_Views);
//            rvGoal = itemView.findViewById(R.id.rv_Row_Impact_Goal);
//
//            lvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_Row_Impact:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}