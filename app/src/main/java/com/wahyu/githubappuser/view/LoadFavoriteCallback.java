package com.wahyu.githubappuser.view;

import com.wahyu.githubappuser.models.Favorite;

import java.util.ArrayList;

public interface LoadFavoriteCallback{
    void preExecute();
    void postExecute(ArrayList<Favorite> data);
}
