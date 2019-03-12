package com.reynaldiwijaya.smartrt.ui.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.reynaldiwijaya.smartrt.Helper.SessionIntro;
import com.reynaldiwijaya.smartrt.Helper.SessionManager;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.ui.Intro.IntroActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {
    @BindView(R.id.imageSplash)
    ImageView imageSplash;
    private SessionManager sm;
    private SessionIntro si;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        sm = new SessionManager(SplashScreen.this);
        si = new SessionIntro(this);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        imageSplash.setAnimation(animation);

        setUpDelay();
    }

    private void setUpDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!si.getIntro()) {
                    Intent intent = new Intent(SplashScreen.this, IntroActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    sm.checkLogin();
                }

                finish();
            }
        }, 3000);

    }
}
