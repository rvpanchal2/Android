package com.gracepad.hh.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.gracepad.hh.R;
import com.gracepad.hh.adapter.TutorialAdapter;
import com.gracepad.hh.object.TutorialObject;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class TutorialActivity extends AppCompatActivity {

    ViewPager vpTutorial;
    PageIndicatorView piTutorial;
    AppCompatButton btnNext;
    LinearLayout lvLogin;

    AppCompatActivity act;

    ArrayList<TutorialObject> arrTut = new ArrayList<>();
    private TutorialAdapter tutorialAdapter;

    private static final int REQUEST_PERMISSION_BOTH = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        act = this;
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        vpTutorial = findViewById(R.id.vp_Tutorial);
        piTutorial = findViewById(R.id.indicator_Tutorial);
        btnNext = findViewById(R.id.btn_Tutorial_Next);
        lvLogin = findViewById(R.id.layout_Tutorial_Login);

        arrTut.add(new TutorialObject(getString(R.string.tutorial_title_1), getString(R.string.tutorial_description_1), R.drawable.ic_tutorial_1));
        arrTut.add(new TutorialObject(getString(R.string.tutorial_title_2), getString(R.string.tutorial_description_2), R.drawable.ic_tutorial_2));
        arrTut.add(new TutorialObject(getString(R.string.tutorial_title_3), getString(R.string.tutorial_description_3), R.drawable.ic_tutorial_3));

        tutorialAdapter = new TutorialAdapter(getSupportFragmentManager(), arrTut);
        vpTutorial.setAdapter(tutorialAdapter);
        vpTutorial.setOffscreenPageLimit(arrTut.size());
        piTutorial.setViewPager(vpTutorial);
        vpTutorial.setPageTransformer(true, new FadeOutTransformation());

        vpTutorial.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    btnNext.setText("Get Started");
                    btnNext.setTextColor(ContextCompat.getColor(act, R.color.white));
                    btnNext.setBackgroundResource(R.drawable.rounded_corner_button);
//                    lvLogin.setVisibility(View.VISIBLE);
                } else {
                    btnNext.setText("Next");
                    btnNext.setTextColor(ContextCompat.getColor(act, R.color.theme_color));
                    btnNext.setBackgroundResource(R.drawable.rounded_corner_button_trans);
//                    lvLogin.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        lvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnNext.getText().toString().toLowerCase().equals("next")) {
                    vpTutorial.setCurrentItem(vpTutorial.getCurrentItem() + 1, true);
                } else {
                    Intent intent = new Intent(act, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        if (ContextCompat.checkSelfPermission(act, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(act, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(act, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(act, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(act, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_BOTH);
            return;
        }
    }

    public class FadeOutTransformation implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View page, float position) {
            page.setTranslationX(-position * page.getWidth());
            page.setAlpha(1 - Math.abs(position));
        }
    }
}
