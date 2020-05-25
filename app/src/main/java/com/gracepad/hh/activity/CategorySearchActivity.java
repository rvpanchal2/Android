package com.gracepad.hh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.adapter.SearchAdapter;
import com.gracepad.hh.adapter.SearchCategoryAdapter;
import com.gracepad.hh.object.CategoryObject;
import com.gracepad.hh.object.CharityObject;
import com.gracepad.hh.views.HorizontalItemDecoration;

import java.util.ArrayList;

public class CategorySearchActivity extends AppCompatActivity {

    AppCompatImageView imgBack;
    RecyclerView rvCategory, rvResult;

    AppCompatActivity act;
    GracePadApplication application;

    private ArrayList<CategoryObject> arrCategory = new ArrayList<>();
    private SearchCategoryAdapter categoryAdapter;

    private ArrayList<CharityObject> arrImpact = new ArrayList<>();
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);

        act = this;
        application = GracePadApplication.getInstance();

        imgBack = findViewById(R.id.img_CategorySearch_Back);
        rvCategory = findViewById(R.id.rv_CategorySearch_Category);
        rvResult = findViewById(R.id.rv_CategorySearch);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /*VerticalItemDecoration itemDecoration = new VerticalItemDecoration(act, R.dimen.recyclerview_padding);
        rvResult.addItemDecoration(itemDecoration);*/

        setArray();

        HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(act, R.dimen.recyclerview_padding);
        rvCategory.addItemDecoration(itemDecoration);

        categoryAdapter = new SearchCategoryAdapter(act, arrCategory);
        rvCategory.setAdapter(categoryAdapter);

        adapter = new SearchAdapter(act, arrImpact);
        rvResult.setAdapter(adapter);

        adapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(act, CharityDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setArray() {
        arrImpact.add(new CharityObject());
        arrImpact.add(new CharityObject());
        arrImpact.add(new CharityObject());
        arrImpact.add(new CharityObject());
        arrImpact.add(new CharityObject());
        arrImpact.add(new CharityObject());
        arrImpact.add(new CharityObject());
        arrImpact.add(new CharityObject());
        arrImpact.add(new CharityObject());
        arrImpact.add(new CharityObject());

        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
        arrCategory.add(new CategoryObject());
    }
}
