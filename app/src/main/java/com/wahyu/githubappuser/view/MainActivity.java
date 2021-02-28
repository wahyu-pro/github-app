package com.wahyu.githubappuser.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wahyu.githubappuser.R;
import com.wahyu.githubappuser.presenters.MainPresenterImpl;
import com.wahyu.githubappuser.view.fragments.FavoriteFragment;
import com.wahyu.githubappuser.view.fragments.HomeFragment;
import com.wahyu.githubappuser.view.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity implements MainView {

    BottomNavigationView navigationView;
    MainPresenterImpl presenter;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        presenter = new MainPresenterImpl(getApplicationContext(), this);
        presenter.navigateToHome();
        // Add navigation
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    presenter.navigateToHome();
                    break;
                case R.id.favorite:
                    presenter.navigateToFavorite();
                    break;
                case R.id.setting:
                    presenter.navigateToSetting();
                    break;
            }
            item.setChecked(true);
            return false;
        });

    }

    @Override
    public void initView() {
        navigationView = findViewById(R.id.main_bottom_navigation);
    }

    @Override
    public void displayHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new HomeFragment(), "HOME").commit();
    }

    @Override
    public void displayFavorite() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new FavoriteFragment(), "USER_ID").commit();
    }

    @Override
    public void displaySetting() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new SettingFragment(), "SETTING").commit();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
    }
}