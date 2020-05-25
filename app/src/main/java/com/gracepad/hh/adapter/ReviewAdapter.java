package com.gracepad.hh.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.object.ReviewObject;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ItemHolder> {

    private ArrayList<ReviewObject> arrPost;
    Activity act;


    public ReviewAdapter(Activity act, ArrayList<ReviewObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public ReviewAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_review_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ItemHolder holder, int position) {
        final ReviewObject object = getItem(position);

    }

    @Override
    public int getItemCount() {
        return arrPost.size();
    }

    private ReviewObject getItem(int position) {
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

        TextView txtName, txtDetail;
        CircleImageView imgUserProfile;
        LinearLayout lvItem;
        SimpleRatingBar rbRating;

        ItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.layout_Row_Review);
            imgUserProfile = itemView.findViewById(R.id.img_Row_Review_UserProfile);
            txtName = itemView.findViewById(R.id.txt_Row_Review_Username);
            txtDetail = itemView.findViewById(R.id.txt_Row_Review_Message);
            rbRating = itemView.findViewById(R.id.rb_Row_Review);

            lvItem.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_Row_Review:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}