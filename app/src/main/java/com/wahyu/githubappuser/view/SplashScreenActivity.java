package com.wahyu.githubappuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.wahyu.githubappuser.R;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivSplash;
    TextView tvSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ivSplash = findViewById(R.id.iv_splash);
        tvSplash = findViewById(R.id.tv_splash);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
        ivSplash.startAnimation(animation);

        new Handler().postDelayed(() -> {
            tvSplash.setVisibility(View.VISIBLE);
            Animation txt = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            tvSplash.startAnimation(txt);
        }, 700);

        new Handler(Looper.getMainLooper()).postDelayed(()->{
                startActivity(new Intent(this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                );
                finish();
        }, 3500);
    }
}