package com.wahyu.githubappuser.view.fragments;

import com.wahyu.githubappuser.models.SocialResponse;

import java.util.List;

public interface SocialView {
    void onSocialAvailable(List<SocialResponse> response);
    void onSocialNotAvailable(String reason);
    void showLoading(boolean state);
}
