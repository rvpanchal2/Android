package com.gracepad.hh.fragment.bottomsheet;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gracepad.hh.R;
import com.gracepad.hh.adapter.FavCategoryAdapter;
import com.gracepad.hh.object.CategoryObject;
import com.gracepad.hh.views.GridViewDecoration;

import java.util.ArrayList;

public class CategoryDialog extends BottomSheetDialog {

    private RecyclerView rvCategoryFav, rvCategoryOther;
    private AppCompatButton btnDone;

    private final Activity act;

    private ArrayList<CategoryObject> arrFavCategory = new ArrayList<>();
    private ArrayList<CategoryObject> arrOtherCategory = new ArrayList<>();
    private FavCategoryAdapter categoryFavAdapter, categoryOtherAdapter;

    public CategoryDialog(@NonNull Activity act) {
        super(act);
        this.act = act;
        create();
    }

    public void create() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_category, null);
        setContentView(bottomSheetView);

        View parent = (View) bottomSheetView.getParent();
        parent.setBackgroundResource(R.color.transparent);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // do something
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // do something
            }
        };

        rvCategoryFav = bottomSheetView.findViewById(R.id.rv_Dlg_Category_FavCategory);
        rvCategoryOther = bottomSheetView.findViewById(R.id.rv_Dlg_Category_OtherCategory);
        btnDone = bottomSheetView.findViewById(R.id.btn_Category_OtherCategory_Done);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        GridViewDecoration itemDecoration = new GridViewDecoration(act, R.dimen.recyclerview_padding_half);
        rvCategoryFav.addItemDecoration(itemDecoration);
        rvCategoryOther.addItemDecoration(itemDecoration);

        categoryFavAdapter = new FavCategoryAdapter(act, arrFavCategory, true);
        rvCategoryFav.setAdapter(categoryFavAdapter);

        categoryOtherAdapter = new FavCategoryAdapter(act, arrOtherCategory, false);
        rvCategoryOther.setAdapter(categoryOtherAdapter);

        setArray();
    }

    private void setArray() {
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());

        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
    }
}


/*public class CategoryDialog extends BottomSheetDialogFragment {

    private RecyclerView rvCategoryFav, rvCategoryOther;

//    private Activity act;

    private ArrayList<CategoryObject> arrFavCategory = new ArrayList<>();
    private ArrayList<CategoryObject> arrOtherCategory = new ArrayList<>();
    private CategoryAdapter categoryAdapter;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        //Set the custom view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_category, null);
        dialog.setContentView(view);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    String state = "";

                    switch (newState) {
                        case BottomSheetBehavior.STATE_DRAGGING: {
                            state = "DRAGGING";
                            break;
                        }
                        case BottomSheetBehavior.STATE_SETTLING: {
                            state = "SETTLING";
                            break;
                        }
                        case BottomSheetBehavior.STATE_EXPANDED: {
                            state = "EXPANDED";
                            break;
                        }
                        case BottomSheetBehavior.STATE_COLLAPSED: {
                            state = "COLLAPSED";
                            break;
                        }
                        case BottomSheetBehavior.STATE_HIDDEN: {
                            dismiss();
                            state = "HIDDEN";
                            break;
                        }
                    }

                    Toast.makeText(getContext(), "Bottom Sheet State Changed to: " + state, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }

        rvCategoryFav = view.findViewById(R.id.rv_Dlg_Category_FavCategory);
        rvCategoryOther = view.findViewById(R.id.rv_Dlg_Category_OtherCategory);

        GridViewDecoration itemDecoration = new GridViewDecoration(getActivity(), R.dimen.recyclerview_padding);
        rvCategoryFav.addItemDecoration(itemDecoration);
        rvCategoryOther.addItemDecoration(itemDecoration);

        categoryAdapter = new CategoryAdapter(getActivity(), arrFavCategory);
        rvCategoryFav.setAdapter(categoryAdapter);

        categoryAdapter = new CategoryAdapter(getActivity(), arrOtherCategory);
        rvCategoryOther.setAdapter(categoryAdapter);
        setArray();
    }

    private void setArray() {
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());
        arrFavCategory.add(new CategoryObject());

        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
        arrOtherCategory.add(new CategoryObject());
    }
}*/