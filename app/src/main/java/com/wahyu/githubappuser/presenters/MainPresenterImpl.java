package com.wahyu.githubappuser.presenters;

import android.content.Context;

import com.wahyu.githubappuser.view.MainView;

public class MainPresenterImpl implements MainPresenter{

    Context mContext;
    MainView view;

    public MainPresenterImpl(Context context, MainView view){
        this.mContext = context;
        this.view = view;
    }

    @Override
    public void navigateToHome() {
        view.displayHome();
    }

    @Override
    public void navigateToFavorite() {
        view.displayFavorite();
    }

    @Override
    public void navigateToSetting() {
        view.displaySetting();
    }
}
