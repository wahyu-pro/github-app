package com.wahyu.consumerapp.presenter;

import com.wahyu.consumerapp.model.Favorite;

public interface FavoritePresenter {
    void loadFavorites();
    void onClickItem(Favorite favorite);
}
