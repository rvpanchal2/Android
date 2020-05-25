package com.gracepad.hh.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.R;
import com.gracepad.hh.adapter.NeedsAdapter;
import com.gracepad.hh.adapter.RequestRoleAdapter;
import com.gracepad.hh.common.MyUtils;
import com.gracepad.hh.common.NetworkUtil;
import com.gracepad.hh.object.NeedHelpObject;
import com.gracepad.hh.object.NeedsObject;

import java.util.ArrayList;

public class CreateHelpActivity extends AppCompatActivity {

    AppCompatImageView imgBack;
    AppCompatEditText edtTitle, edtDescription;
    AppCompatTextView txtNeeds, txtCategory, txtStartDate, txtEndDate, txtState;
    AppCompatCheckBox chkRequired;
    AppCompatButton btnNext, btnAddVolunteer, btnAddFood;
    RecyclerView rvVolunteer, rvFood;
    CoordinatorLayout lvFood, lvFund, lvVolunteer;

    AppCompatActivity act;

    ArrayList<NeedsObject> arrNeeds = new ArrayList<>();

    RequestRoleAdapter volunteerAdapter;
    ArrayList<NeedHelpObject> arrNeedVolunteer = new ArrayList<>();

    RequestRoleAdapter foodAdapter;
    ArrayList<NeedHelpObject> arrNeedFood = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_help);

        act = this;

        imgBack = findViewById(R.id.img_Create_Help_Back);
        txtNeeds = findViewById(R.id.txt_Create_Help_Needs);
//        spCategory = findViewById(R.id.sp_Create_Help_Needs);
        edtTitle = findViewById(R.id.edt_Create_Help_Title);
        txtCategory = findViewById(R.id.txt_Create_Help_Category);
        edtDescription = findViewById(R.id.edt_Create_Help_Description);
        txtStartDate = findViewById(R.id.txt_Create_Help_StartDate);
        txtEndDate = findViewById(R.id.txt_Create_Help_EndDate);
        txtState = findViewById(R.id.txt_Create_Help_State);
        chkRequired = findViewById(R.id.chk_Create_Help_Urgent);
        btnNext = findViewById(R.id.btn_Create_Help_Next);
        lvFood = findViewById(R.id.layout_Create_Help_Food);
        lvFund = findViewById(R.id.layout_Create_Help_Fund);
        lvVolunteer = findViewById(R.id.layout_Create_Help_Volunteer);

        /*TODO : VOLUNTEER CONTROLS
        *  */
        btnAddVolunteer = findViewById(R.id.btn_Create_Help_Volunteer_AddRole);
        rvVolunteer = findViewById(R.id.rv_Create_Help_Volunteer);

        /*TODO : NEEDS CONTROLS*/
        btnAddFood = findViewById(R.id.btn_Create_Help_Food_AddRole);
        rvFood = findViewById(R.id.rv_Create_Help_Food);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        txtNeeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNeedsDialog();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtil.getConnectivityStatus(act)) {
                    Intent intent = new Intent(act, MainActivity.class);
                    startActivity(intent);
                } else
                    MyUtils.showAlertDialog(act, getString(R.string.network));
            }
        });

        btnAddVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrNeedVolunteer.add(new NeedHelpObject());

                volunteerAdapter.notifyDataSetChanged();
            }
        });

        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrNeedFood.add(new NeedHelpObject());

                foodAdapter.notifyDataSetChanged();
            }
        });

        arrNeeds.add(new NeedsObject(1, false, "Fund"));
        arrNeeds.add(new NeedsObject(2, false, "Volunteers"));
        arrNeeds.add(new NeedsObject(3, false, "Food"));
        arrNeeds.add(new NeedsObject(4, false, "Cloth"));
        arrNeeds.add(new NeedsObject(5, false, "Medical"));
        arrNeeds.add(new NeedsObject(6, false, "Others"));

        arrNeedVolunteer.add(new NeedHelpObject());

        volunteerAdapter = new RequestRoleAdapter(act, arrNeedVolunteer);
        rvVolunteer.setAdapter(volunteerAdapter);

        arrNeedFood.add(new NeedHelpObject());

        foodAdapter = new RequestRoleAdapter(act, arrNeedFood);
        rvFood.setAdapter(foodAdapter);
    }

    private int nSelectedCategory;

    @SuppressLint("DefaultLocale")
    public void showNeedsDialog() {
//        final Dialog dlgTarget = new Dialog(act, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        final Dialog dlgConfirmation = new Dialog(act, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        dlgConfirmation.setCancelable(false);
        dlgConfirmation.setContentView(R.layout.dialog_needs);
        dlgConfirmation.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
        dlgConfirmation.getWindow().setBackgroundDrawableResource(R.color.transparent);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            dlgConfirmation.getWindow().setStatusBarColor(act.getResources().getColor(R.color.white, act.getTheme()));

        ListView lvNeeds = dlgConfirmation.findViewById(R.id.lv_Dlg_Needs);
        TextView txtCancel = dlgConfirmation.findViewById(R.id.txt_Dlg_Needs_Cancel);
        TextView txtSubmit = dlgConfirmation.findViewById(R.id.txt_Dlg_Needs_Submit);

        final NeedsAdapter adapter = new NeedsAdapter(act, arrNeeds);
        lvNeeds.setAdapter(adapter);

        lvNeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NeedsObject obj = arrNeeds.get(position);

//                MyUtils.Log("nSelectedCategory : " + nSelectedCategory);
                if (nSelectedCategory >= 3 && !obj.getIsSelected())
                    return;

                if (obj.getIsSelected())
                    nSelectedCategory--;
                else
                    nSelectedCategory++;

                arrNeeds.get(position).setIsSelected(!obj.getIsSelected());
                adapter.notifyDataSetChanged();
            }
        });

        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvFund.setVisibility(View.GONE);
                lvVolunteer.setVisibility(View.GONE);
                lvFood.setVisibility(View.GONE);

                String str = "";
                for (int i = 0; i < arrNeeds.size(); i++) {
                    NeedsObject obj = arrNeeds.get(i);
                    if (obj.getIsSelected()) {
                        str = String.format("%s%s", str, obj.getTitle());

                        if (i != arrNeeds.size() - 1)
                            str = str + ", ";
                        else
                            str = str + "";

                        if (obj.getId() == 1) {
                            lvFund.setVisibility(View.VISIBLE);
                        } else if (obj.getId() == 2) {
                            lvVolunteer.setVisibility(View.VISIBLE);
                        } else {
                            lvFood.setVisibility(View.VISIBLE);
                        }
                    }
                }

                if (str.endsWith(", "))
                    str = str.substring(0, str.length() - 2);
                txtNeeds.setText(str);
                dlgConfirmation.dismiss();
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlgConfirmation.dismiss();
            }
        });

        dlgConfirmation.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(android.content.DialogInterface dialog, int keyCode, android.
                    view.KeyEvent event) {
                if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                    dlgConfirmation.dismiss();
                    return true; // pretend we've processed it
                } else
                    return false; // pass on to be processed as normal
            }
        });

        dlgConfirmation.show();
    }
}
