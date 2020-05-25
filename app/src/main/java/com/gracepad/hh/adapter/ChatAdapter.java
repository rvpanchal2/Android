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

import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.object.ChatObject;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ChatObject> arrPost;
    private Activity act;

    public static int LEFT_VIEW = 1;
    public static int RIGHT_VIEW = 2;

    public ChatAdapter(Activity act, ArrayList<ChatObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LEFT_VIEW) {
            View view = LayoutInflater.from(act).inflate(R.layout.row_chat_left_item, parent, false);
            return new LeftItemHolder(view);
        } else {
            View view = LayoutInflater.from(act).inflate(R.layout.row_chat_right_item, parent, false);
            return new RightItemHolder(view);
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatObject object = getItem(position);

    }

    @Override
    public int getItemCount() {
        return arrPost.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? LEFT_VIEW : RIGHT_VIEW;
    }

    private ChatObject getItem(int position) {
        return arrPost.get(position);
    }

    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    class LeftItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatTextView txtMessage, txtTime;
        LinearLayout lvItem;

        LeftItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.layout_Row_Chat_Left);
            txtMessage = itemView.findViewById(R.id.txt_Row_Chat_Left_Message);
            txtTime = itemView.findViewById(R.id.txt_Row_Chat_Left_Time);

            lvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_Row_Chat_Left:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }

    class RightItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatTextView txtMessage, txtTime;
        LinearLayout lvItem;

        RightItemHolder(View itemView) {
            super(itemView);
            lvItem = itemView.findViewById(R.id.layout_Row_Chat_Right);
            txtMessage = itemView.findViewById(R.id.txt_Row_Chat_Right_Message);
            txtTime = itemView.findViewById(R.id.txt_Row_Chat_Right_Time);

            lvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_Row_Chat_Right:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}