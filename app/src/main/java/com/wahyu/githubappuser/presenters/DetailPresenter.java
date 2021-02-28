package com.wahyu.githubappuser.presenters;

import android.net.Uri;

import com.wahyu.githubappuser.models.UserDetail;

public interface DetailPresenter {

    void onUserDetailAvailable(UserDetail userDetail);
    void addToFavorite(UserDetail userDetail);
    void deleteFromFavorite(Uri uriWithId);

}
