package com.gracepad.hh.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.object.NeedHelpObject;

import java.util.ArrayList;

public class RequestRoleAdapter extends RecyclerView.Adapter<RequestRoleAdapter.ItemHolder> {

    private ArrayList<NeedHelpObject> arrPost;
    private Activity act;
    private String[] arrType;
    private ArrayAdapter<String> adapterType;

    public RequestRoleAdapter(Activity act, ArrayList<NeedHelpObject> arrPost) {
        this.act = act;
        this.arrPost = arrPost;
        this.arrType = act.getResources().getStringArray(R.array.type_spinner);
        this.adapterType = new ArrayAdapter<>(act, R.layout.row_spinner, arrType);
        if (Constant.nWidth <= 0 || Constant.nHeight <= 0) {
            Constant.setHeightWidth(act);
        }
    }

    @NonNull
    @Override
    public RequestRoleAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_request_role_item, parent, false);
        return new ItemHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull final RequestRoleAdapter.ItemHolder holder, int position) {
        ArrayAdapter<String> adapterReason = new ArrayAdapter<>(act, R.layout.row_spinner, arrType);
        adapterReason.setDropDownViewResource(R.layout.row_spinner);
        holder.spType.setAdapter(adapterReason);

        holder.spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                holder.txtPersonType.setText(holder.spType.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrPost.size();
    }

    private NeedHelpObject getItem(int position) {
        return arrPost.get(position);
    }

    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        AppCompatEditText edtRole, edtPerson;
        AppCompatTextView txtPersonType;
        AppCompatSpinner spType;

        ItemHolder(View itemView) {
            super(itemView);
            edtRole = itemView.findViewById(R.id.edt_Row_Request_Role);
            edtPerson = itemView.findViewById(R.id.edt_Row_Request_Role_Person);
            txtPersonType = itemView.findViewById(R.id.txt_Row_Request_Role_Type);
            spType = itemView.findViewById(R.id.sp_Row_Request_Role_Type);

            try {
                spType.setDropDownWidth(txtPersonType.getLayoutParams().width);
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
            }

            txtPersonType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spType.performClick();
                }
            });
        }
    }
}