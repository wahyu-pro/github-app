package com.wahyu.githubappuser.presenters;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.wahyu.githubappuser.models.UserDetail;
import com.wahyu.githubappuser.view.DetailView;

import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.AVATAR;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.CONTENT_URI;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.EMAIL;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.USER_ID;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.FOLLOWER;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.FOLLOWING;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.NAME;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.USERNAME;

public class DetailPresenterImpl implements DetailPresenter{

    Context context;
    DetailView view;

    public DetailPresenterImpl (Context context, DetailView view){
        this.context = context;
        this.view = view;
    }

    @Override
    public void onUserDetailAvailable(UserDetail userDetail) {
        view.displayUserDetail(userDetail);
    }

    @Override
    public void addToFavorite(UserDetail userDetail) {
        ContentValues values = new ContentValues();
        values.put(NAME, userDetail.getName());
        values.put(USERNAME, userDetail.getLogin());
        values.put(AVATAR, userDetail.getAvatarUrl());
        values.put(EMAIL, userDetail.getEmail());
        values.put(FOLLOWER, userDetail.getFollowers());
        values.put(FOLLOWING, userDetail.getFollowing());
        values.put(USER_ID, userDetail.getId());
        context.getContentResolver().insert(CONTENT_URI, values);
        view.onSuccessAddFavorite();
    }

    @Override
    public void deleteFromFavorite(Uri uriWithId) {
        context.getContentResolver().delete(uriWithId, null, null);
        view.onSuccessDeleteFavorite();
    }
}
