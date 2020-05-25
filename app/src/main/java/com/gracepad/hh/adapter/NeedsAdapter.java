package com.gracepad.hh.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.gracepad.hh.R;
import com.gracepad.hh.common.MyUtils;
import com.gracepad.hh.object.NeedsObject;

import java.util.ArrayList;

public class NeedsAdapter extends ArrayAdapter<NeedsObject> {

    private Activity act;
    private ArrayList<NeedsObject> arr;

    public NeedsAdapter(Activity act, ArrayList<NeedsObject> arr) {
        super(act, R.layout.row_needs_item, arr);
        this.arr = arr;
        this.act = act;
    }

    private class ViewHolder {
        AppCompatTextView txtTitle, txtCount;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, View v, @NonNull ViewGroup parent) {
        final ViewHolder holder;

        if (v == null) {
            holder = new ViewHolder();
            v = LayoutInflater.from(act).inflate(R.layout.row_needs_item, parent, false);
            holder.txtTitle = v.findViewById(R.id.txt_Row_Needs);
            holder.txtCount = v.findViewById(R.id.txt_Row_Needs_Count);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        try {
            final NeedsObject obj = getItem(position);

            holder.txtTitle.setText(obj.getTitle());

            if (obj.getIsSelected()) {
//                holder.txtCount.setText(String.format("%d", obj.getSelectedId()));
                holder.txtCount.setBackgroundResource(R.drawable.rounded_corner_button);
            } else {
//                holder.txtCount.setText("");
                holder.txtCount.setBackgroundResource(R.drawable.rounded_border_edt);
            }
        } catch (IndexOutOfBoundsException e) {
            MyUtils.Log("IndexOutOfBoundsException : " + e.toString());
            return null;
        }
        return v;
    }
}
