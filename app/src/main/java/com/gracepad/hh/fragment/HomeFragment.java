package com.gracepad.hh.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.activity.CategorySearchActivity;
import com.gracepad.hh.activity.CharityDetailActivity;
import com.gracepad.hh.activity.NotificationActivity;
import com.gracepad.hh.activity.SearchActivity;
import com.gracepad.hh.adapter.CategoryAdapter;
import com.gracepad.hh.adapter.FeaturedImpactAdapter;
import com.gracepad.hh.adapter.OrganizationAdapter;
import com.gracepad.hh.fragment.bottomsheet.CategoryDialog;
import com.gracepad.hh.object.CategoryObject;
import com.gracepad.hh.object.CharityObject;
import com.gracepad.hh.object.OrganizationObject;
import com.gracepad.hh.views.GridViewDecoration;
import com.gracepad.hh.views.HorizontalItemDecoration;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private AppCompatTextView txtSearch;
    private AppCompatImageView imgNotification;
    private AppCompatTextView txtFeaturedAll, txtOrganisationAll;
    private RecyclerView rvCategory, rvFeatured, rvOrganisation;

    private Activity act;
    private GracePadApplication application;

    private ArrayList<CategoryObject> arrCategory = new ArrayList<>();
    private CategoryAdapter categoryAdapter;

    private ArrayList<CharityObject> arrCharity = new ArrayList<>();
    private FeaturedImpactAdapter impactAdapter;

    private ArrayList<OrganizationObject> arrOrganization = new ArrayList<>();
    private OrganizationAdapter organizationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        act = getActivity();
        application = GracePadApplication.getInstance();

        txtSearch = v.findViewById(R.id.txt_Home_Search);
        imgNotification = v.findViewById(R.id.img_Home_Notification);
        txtFeaturedAll = v.findViewById(R.id.txt_Home_Featured_ViewAll);
        txtOrganisationAll = v.findViewById(R.id.txt_Home_Organisation_All);
        rvCategory = v.findViewById(R.id.rv_Home_Category);
        rvFeatured = v.findViewById(R.id.rv_Home_Featured);
        rvOrganisation = v.findViewById(R.id.rv_Home_Organisation);

        setArray();

        GridViewDecoration itemDecoration = new GridViewDecoration(act, R.dimen.recyclerview_padding);
        rvCategory.addItemDecoration(itemDecoration);

        categoryAdapter = new CategoryAdapter(act, arrCategory);
        rvCategory.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position >= arrCategory.size()) {
                    CategoryDialog dialog = new CategoryDialog(act);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                } else {
                    Intent intent = new Intent(act, CategorySearchActivity.class);
                    startActivity(intent);
                }
            }
        });

        HorizontalItemDecoration horizontalItemDecoration = new HorizontalItemDecoration(act, R.dimen.recyclerview_padding);
        rvFeatured.addItemDecoration(horizontalItemDecoration);

        impactAdapter = new FeaturedImpactAdapter(act, arrCharity);
        rvFeatured.setAdapter(impactAdapter);

        impactAdapter.setOnItemClickListener(new FeaturedImpactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(act, CharityDetailActivity.class);
                startActivity(intent);
            }
        });

        organizationAdapter = new OrganizationAdapter(act, arrOrganization);
        rvOrganisation.setAdapter(organizationAdapter);

        organizationAdapter.setOnItemClickListener(new OrganizationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                /*CategoryDialog bottomSheetFragment = new CategoryDialog();
                bottomSheetFragment.show(getFragmentManager(), "TAG");*/
            }
        });

        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, SearchActivity.class);
                startActivity(intent);
            }
        });

        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, NotificationActivity.class);
                startActivity(intent);
            }
        });

        /*CategoryDialog dialog = new CategoryDialog(act);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();*/

        /*CategoryDialog bottomSheetFragment = new CategoryDialog();
        bottomSheetFragment.show(getFragmentManager(), "TAG");*/
        return v;
    }

    private void setArray() {
        arrOrganization.add(new OrganizationObject());
        arrOrganization.add(new OrganizationObject());
        arrOrganization.add(new OrganizationObject());
        arrOrganization.add(new OrganizationObject());
        arrOrganization.add(new OrganizationObject());
        arrOrganization.add(new OrganizationObject());

        arrCharity.add(new CharityObject());
        arrCharity.add(new CharityObject());
        arrCharity.add(new CharityObject());
        arrCharity.add(new CharityObject());
        arrCharity.add(new CharityObject());
        arrCharity.add(new CharityObject());
        arrCharity.add(new CharityObject());
        arrCharity.add(new CharityObject());
        arrCharity.add(new CharityObject());
        arrCharity.add(new CharityObject());

        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
//        arrCategory.add(new CategoryObject());
    }
}