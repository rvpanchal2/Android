package com.gracepad.hh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.adapter.SearchAdapter;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.common.NetworkUtil;
import com.gracepad.hh.object.CharityObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    AppCompatImageView imgBack, imgFilter;
    AppCompatEditText edtSearch;
    RecyclerView rvResult;

    AppCompatActivity act;
    GracePadApplication application;

    private ArrayList<CharityObject> arrImpact = new ArrayList<>();
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        act = this;
        application = GracePadApplication.getInstance();

        imgBack = findViewById(R.id.img_Search_Back);
        imgFilter = findViewById(R.id.img_Search_Filter);
        edtSearch = findViewById(R.id.edt_Search);
        rvResult = findViewById(R.id.rv_Search);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /*VerticalItemDecoration itemDecoration = new VerticalItemDecoration(act, R.dimen.recyclerview_padding);
        rvResult.addItemDecoration(itemDecoration);*/

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Constant.hideKeyboard(act, edtSearch);

                    if (NetworkUtil.getConnectivityStatus(act)) {
//                        searchRestaurant(edtSearch.getText().toString(), true);
                    }
                    return true;
                }

                return false;
            }
        });

        setArray();

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
    }
}
