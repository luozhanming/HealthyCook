package com.zhanming.healthycook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.zhanming.healthycook.main.MainActivity;

import net.youmi.android.normal.spot.SplashViewSettings;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashViewSettings settings = new SplashViewSettings();
        settings.setTargetClass(MainActivity.class);
        settings.setSplashViewContainer((ViewGroup) getWindow().getDecorView());
        SpotManager.getInstance(this).showSplash(this, settings, new SpotListener() {
            @Override
            public void onShowSuccess() {

            }

            @Override
            public void onShowFailed(int i) {

            }

            @Override
            public void onSpotClosed() {

            }

            @Override
            public void onSpotClicked(boolean b) {

            }
        });
    }
}
