package com.gracepad.hh.fragment.bottomsheet;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gracepad.hh.R;

public class DonationVolunteerDialog extends BottomSheetDialog {

    private AppCompatButton btnDone;

    private final Activity act;

    private BottomSheetBehavior bottomSheetBehavior;

    public DonationVolunteerDialog(@NonNull Activity act) {
        super(act);
        this.act = act;
        create();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void create() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_donation_volunteer, null);
        setContentView(bottomSheetView);

        View parent = (View) bottomSheetView.getParent();
        parent.setBackgroundResource(R.color.transparent);

        bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // do something
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // do something
            }
        });

        btnDone = bottomSheetView.findViewById(R.id.btn_Dlg_Donation_Volunteer_Done);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

                ReviewDialog dialog = new ReviewDialog(act);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });
    }
}