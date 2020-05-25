package com.gracepad.hh.fragment.bottomsheet;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gracepad.hh.R;
import com.gracepad.hh.adapter.CommentAdapter;
import com.gracepad.hh.object.CommentObject;

import java.util.ArrayList;

public class CommentDialog extends BottomSheetDialog {

    private RecyclerView rvComment;
    LinearLayout lvComment;

    private final Activity act;

    private ArrayList<CommentObject> arrComments = new ArrayList<>();
    private CommentAdapter adapter;

    BottomSheetBehavior bottomSheetBehavior;

    public CommentDialog(@NonNull Activity act) {
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
        View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_comment, null);
        setContentView(bottomSheetView);

        View parent = (View) bottomSheetView.getParent();
        parent.setBackgroundResource(R.color.transparent);

        bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                /*if (BottomSheetBehavior.STATE_DRAGGING == newState) {
                    lvComment.animate().scaleX(0).scaleY(0).setDuration(300).start();
                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    lvComment.animate().scaleX(1).scaleY(1).setDuration(300).start();
                }*/
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                MyUtils.Log(slideOffset);
//                lvComment.animate().translationX(slideOffset).translationY(slideOffset).setDuration(0).start();
            }
        });

        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

            }
        });

        rvComment = bottomSheetView.findViewById(R.id.rv_Dlg_Comment);
        lvComment = bottomSheetView.findViewById(R.id.layout_Dlg_Comment_Options);

        setArray();

        adapter = new CommentAdapter(act, arrComments);
        rvComment.setAdapter(adapter);
    }

    private void setArray() {
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
        arrComments.add(new CommentObject());
    }
}