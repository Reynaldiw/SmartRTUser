package com.reynaldiwijaya.smartrt.ui.Intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.Helper.SessionIntro;
import com.reynaldiwijaya.smartrt.Helper.SessionManager;
import com.reynaldiwijaya.smartrt.MainActivity;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.ui.Login.LoginActivity;
import com.reynaldiwijaya.smartrt.ui.SplashScreen.SplashScreen;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabIndicator;
    private Button btnNext, btnGetStarted;
    private  int position = 0;
    private Animation btnAnim;
    private SessionIntro si;
    private TextView tvSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make the activity full onScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_intro);

        si = new SessionIntro(this);

        // ini views
        tvSkip = findViewById(R.id.tv_skip);
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        // fill list screen
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Information","Ingin Tau kabar Terhangat Di daerahmu?",R.drawable.berita));
        mList.add(new ScreenItem("KADO","Kabar Indonesia, Informasi Terbaru Di Indonesia",R.drawable.kado));
        mList.add(new ScreenItem("Agenda","Sudah Tau Acara - Acara Di Daerahmu?",R.drawable.agenda));
        mList.add(new ScreenItem("Repost","Ada Masalah Yang Berhubungan Dengan Lingkunganmu?",R.drawable.laporan));
        mList.add(new ScreenItem("Your Neighbour","Tau Siapa Saja Tetanggamu?",R.drawable.warga));
        mList.add(new ScreenItem("Store","Ingin Belanja Tapi Tidak Mau Jauh - Jauh",R.drawable.toko));
        mList.add(new ScreenItem("Hello !","Kami Akan Membantumu Mendapatkannya",R.drawable.smarticon));

        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);

        // Next Button Click Listener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position ++;
                    screenPager.setCurrentItem(position);
                }

                if (position == mList.size() - 1) { // When we reach the last screen

                    //TODO : Show the get Started Button and hide the indicator and the Next Button
                    loadLastScreen();
                }

            }
        });

        // tablayout add chance listener

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size() -1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process
                savePrefsData();
                // Move to the next Activity
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePrefsData();
                // Move to the next Activity
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });


    }

    public void savePrefsData() {
        si.storeIntro();
    }

    // Show the get Started Button and hide the indicator and the Next Button
    private void loadLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        //TODO Add an animation the getStarted Button
        // Setup Animation
        btnGetStarted.setAnimation(btnAnim);
    }
}
