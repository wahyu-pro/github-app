package com.wahyu.githubappuser.view.fragments;

import com.wahyu.githubappuser.models.Favorite;

public interface FavoriteView {
    void displayDetailFavorite(Favorite favorite);
    void onDeleteSuccess();
}
