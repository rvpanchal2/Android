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
import com.gracepad.hh.object.CommentObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ItemHolder> {

    private ArrayList<CommentObject> arrPost;
    private Activity act;

    public CommentAdapter(Activity act, ArrayList<CommentObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public CommentAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_comment_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ItemHolder holder, int position) {
        CommentObject object = getItem(position);

        Glide.with(act).load(R.drawable.ic_temp_user)
                .override(Constant.nWidth, Target.SIZE_ORIGINAL)
                .placeholder(R.color.placeholder_color)
                .centerCrop()
                .into(holder.imgUserPic);
    }

    @Override
    public int getItemCount() {
        return arrPost.size();
    }

    private CommentObject getItem(int position) {
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

        CircleImageView imgUserPic;
        AppCompatTextView txtUserName, txtComment;
        LinearLayout lvItem;

        ItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.layout_Row_Comment);
            imgUserPic = itemView.findViewById(R.id.img_Row_Comment_User);
            txtUserName = itemView.findViewById(R.id.txt_Row_Comment_UserName);
            txtComment = itemView.findViewById(R.id.txt_Row_Comment);

            lvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_Row_Comment:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}