package com.wahyu.githubappuser.presenters;

import android.content.Context;

import com.wahyu.githubappuser.apihelpers.ApiCallback;
import com.wahyu.githubappuser.apihelpers.ApiUtils;
import com.wahyu.githubappuser.models.SocialResponse;
import com.wahyu.githubappuser.view.fragments.SocialView;

import java.util.List;

public class SocialPresenterImpl implements SocialPresenter {

    Context mContext;
    SocialView view;

    public SocialPresenterImpl(Context context, SocialView view) {
        this.mContext = context;
        this.view = view;
    }

    @Override
    public void requestFollower(String username) {
        view.showLoading(true);
        ApiUtils.getFollower(username, new ApiCallback.SocialCallback() {
            @Override
            public void onSuccess(List<SocialResponse> response) {
                view.showLoading(false);
                if (response.size() != 0) view.onSocialAvailable(response);
                else view.onSocialNotAvailable("Data not found");
            }

            @Override
            public void onFailure(String error) {
                view.onSocialNotAvailable(error);
            }
        });
    }

    @Override
    public void requestFollowing(String username) {
        view.showLoading(true);
        ApiUtils.getFollowing(username, new ApiCallback.SocialCallback() {
            @Override
            public void onSuccess(List<SocialResponse> response) {
                view.showLoading(false);
                if (response.size() != 0) view.onSocialAvailable(response);
                else view.onSocialNotAvailable("Data not found");
            }

            @Override
            public void onFailure(String error) {
                view.onSocialNotAvailable(error);
            }
        });
    }
}
