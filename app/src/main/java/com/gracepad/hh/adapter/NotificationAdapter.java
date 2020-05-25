package com.gracepad.hh.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.object.NotificationObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ItemHolder> {

    private ArrayList<NotificationObject> arrPost;
    private Activity act;

    public NotificationAdapter(Activity act, ArrayList<NotificationObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public NotificationAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_notification_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ItemHolder holder, int position) {
        NotificationObject object = getItem(position);

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

    private NotificationObject getItem(int position) {
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

        CircleImageView imgPost;
        AppCompatTextView txtName, txtDate;
        LinearLayout lvItem;

        ItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.layout_Row_Notification);
            imgPost = itemView.findViewById(R.id.img_Row_Notification_UserProfile);
            txtName = itemView.findViewById(R.id.txt_Row_Notification_Name);
            txtDate = itemView.findViewById(R.id.txt_Row_Notification_Date);

            lvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_Row_Notification:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}