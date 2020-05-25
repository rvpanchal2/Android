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
import com.gracepad.hh.object.ProfileObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ItemHolder> {

    private ArrayList<ProfileObject> arrPost;
    private Activity act;

    public MessageAdapter(Activity act, ArrayList<ProfileObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public MessageAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_message_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ItemHolder holder, int position) {
        ProfileObject object = getItem(position);

        Glide.with(act).load(R.drawable.ic_temp)
                .override(Constant.nWidth, Target.SIZE_ORIGINAL)
                .placeholder(R.color.placeholder_color)
                .centerCrop()
                .into(holder.imgProfile);
    }

    @Override
    public int getItemCount() {
        return arrPost.size();
    }

    private ProfileObject getItem(int position) {
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

        CircleImageView imgProfile;
        AppCompatTextView txtName, txtMessage, txtCount, txtTime;
        LinearLayout lvItem;

        ItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.layout_Row_Message);
            imgProfile = itemView.findViewById(R.id.img_Row_Message_Profile);
            txtName = itemView.findViewById(R.id.txt_Row_Message_UserName);
            txtMessage = itemView.findViewById(R.id.txt_Row_Message_UserMessage);
            txtCount = itemView.findViewById(R.id.txt_Row_Message_Count);
            txtTime = itemView.findViewById(R.id.txt_Row_Message_Time);
//            donutProgress = itemView.findViewById(R.id.seek_Row_Message_Progress);

            lvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_Row_Message:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}