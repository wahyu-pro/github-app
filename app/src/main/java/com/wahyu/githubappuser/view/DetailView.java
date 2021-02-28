package com.wahyu.githubappuser.view;

import com.wahyu.githubappuser.models.UserDetail;

public interface DetailView {
    void displayUserDetail(UserDetail data);
    void onSuccessAddFavorite();
    void onSuccessDeleteFavorite();
}
