package com.wahyu.consumerapp.view;

import com.wahyu.consumerapp.model.Favorite;

import java.util.ArrayList;

public interface LoadFavoriteCallback {
    void preExecute();
    void postExecute(ArrayList<Favorite> data);
}
