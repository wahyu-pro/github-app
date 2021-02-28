package com.wahyu.githubappuser.view.fragments;

import com.wahyu.githubappuser.models.Items;
import com.wahyu.githubappuser.models.UserDetail;

import java.util.List;

public interface HomeView {
    void onUsersAvailable(List<Items> users);
    void onUsersNotAvailable(String reason);
    void navigateToDetailUser(UserDetail userDetail);
    void showLoading(boolean state);
}
