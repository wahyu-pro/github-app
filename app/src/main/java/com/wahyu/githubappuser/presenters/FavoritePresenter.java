package com.wahyu.githubappuser.presenters;

import android.net.Uri;

import com.wahyu.githubappuser.models.Favorite;

public interface FavoritePresenter {
    void loadFavorites();
    void onClickItem(Favorite favorite);
    void deleteFavorite(Uri uriWithId);
}
