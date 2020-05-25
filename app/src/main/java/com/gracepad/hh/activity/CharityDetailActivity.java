package com.gracepad.hh.activity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.adapter.CharityRequestAdapter;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.common.ConstantStatusBar;
import com.gracepad.hh.common.ConstantUrl;
import com.gracepad.hh.common.StringUtils;
import com.gracepad.hh.fragment.bottomsheet.CommentDialog;
import com.gracepad.hh.fragment.bottomsheet.DonationAmountDialog;
import com.gracepad.hh.fragment.bottomsheet.DonationDialog;
import com.gracepad.hh.fragment.bottomsheet.DonationVolunteerDialog;
import com.gracepad.hh.object.CharityRequestObject;

import java.util.ArrayList;

public class CharityDetailActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView imgBack, imgCover, imgBookmark, imgGallery1, imgGallery2, imgGallery3, imgGallery4;
    AppCompatTextView txtTitle, txtDescription, txtDaysLeft, txtUserName;
    LinearLayout lvActionBar, lvReview, lvParticipation, lvLike, lvComment, lvShare, lvDonate;
    RecyclerView rvCharityItem;

    AppCompatActivity act;
    GracePadApplication application;

    private ArrayList<CharityRequestObject> arrCharityRequest = new ArrayList<>();
    CharityRequestAdapter requestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_detail);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        act = this;
        application = GracePadApplication.getInstance();

        imgBack = findViewById(R.id.img_CharityDetail_Back);
        imgCover = findViewById(R.id.img_CharityDetail_Cover);
        imgBookmark = findViewById(R.id.img_CharityDetail_Bookmark);
        imgGallery1 = findViewById(R.id.img_CharityDetail_Gallery1);
        imgGallery2 = findViewById(R.id.img_CharityDetail_Gallery2);
        imgGallery3 = findViewById(R.id.img_CharityDetail_Gallery3);
        imgGallery4 = findViewById(R.id.img_CharityDetail_Gallery4);
        rvCharityItem = findViewById(R.id.rv_CharityDetail_Goal);
        txtTitle = findViewById(R.id.txt_CharityDetail_Name);
        txtDescription = findViewById(R.id.txt_CharityDetail_Description);
        txtDaysLeft = findViewById(R.id.txt_CharityDetail_DaysLeft);
        txtUserName = findViewById(R.id.txt_CharityDetail_PostUserName);
        lvActionBar = findViewById(R.id.layout_CharityDetail_ActionBar);
        lvReview = findViewById(R.id.layout_CharityDetail_Review);
        lvParticipation = findViewById(R.id.layout_CharityDetail_Participation);
        lvLike = findViewById(R.id.layout_CharityDetail_Like);
        lvComment = findViewById(R.id.layout_CharityDetail_Comment);
        lvShare = findViewById(R.id.layout_CharityDetail_Share);
        lvDonate = findViewById(R.id.layout_CharityDetail_Donate);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, ConstantStatusBar.getStatusBarHeight(this), 0, 0);
        lvActionBar.setLayoutParams(params);

        imgBack.setOnClickListener(this);
        lvReview.setOnClickListener(this);
        imgCover.setOnClickListener(this);
        lvParticipation.setOnClickListener(this);
        lvLike.setOnClickListener(this);
        lvComment.setOnClickListener(this);
        lvShare.setOnClickListener(this);
        lvDonate.setOnClickListener(this);

        setArray();

        requestAdapter = new CharityRequestAdapter(act, arrCharityRequest);
        rvCharityItem.setAdapter(requestAdapter);
    }

    private void setArray() {
        arrCharityRequest.add(new CharityRequestObject());
        arrCharityRequest.add(new CharityRequestObject());
        arrCharityRequest.add(new CharityRequestObject());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_CharityDetail_Back:
                onBackPressed();
                break;

            case R.id.layout_CharityDetail_Comment:
                CommentDialog dialog = new CommentDialog(act);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                break;

            case R.id.layout_CharityDetail_Share:
                String str = txtTitle.getText().toString() + "\n\n";

                if (!StringUtils.isNullOrEmpty(txtDescription.getText().toString()))
                    str = str + txtDescription.getText().toString() + "\n\n";

                str = str + ConstantUrl.strAppLink;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                sharingIntent.setType("image/*");
                sharingIntent.setType("text/plain");
//                sharingIntent.putExtra(Intent.EXTRA_STREAM, objPost.getImageUrl());
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Refer " + Constant.strAppName);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, str);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;

            case R.id.layout_CharityDetail_Donate:
                selectDonationOption();
                break;

            case R.id.layout_CharityDetail_Review:
                Intent intent = new Intent(act, ReviewActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void selectDonationOption() {
        final CharSequence[] items = {"Donate For Food", "Work As Volunteer", "Donate For Fund"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Donation");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                try {
                    if (items[item].equals("Donate For Food")) {
                        DonationDialog donationDialog = new DonationDialog(act);
                        donationDialog.setCanceledOnTouchOutside(true);
                        donationDialog.show();
                    } else if (items[item].equals("Work As Volunteer")) {
                        DonationVolunteerDialog donationVolunteerDialog = new DonationVolunteerDialog(act);
                        donationVolunteerDialog.setCanceledOnTouchOutside(true);
                        donationVolunteerDialog.show();
                    } else if (items[item].equals("Donate For Fund")) {
                        DonationAmountDialog donationAmountDialog = new DonationAmountDialog(act);
                        donationAmountDialog.setCanceledOnTouchOutside(true);
                        donationAmountDialog.show();
                    }
                } catch (ActivityNotFoundException e) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}
