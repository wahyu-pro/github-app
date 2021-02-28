package com.wahyu.githubappuser.presenters;

import android.content.Context;

import com.wahyu.githubappuser.apihelpers.ApiCallback;
import com.wahyu.githubappuser.apihelpers.ApiUtils;
import com.wahyu.githubappuser.models.Items;
import com.wahyu.githubappuser.models.ResponseUser;
import com.wahyu.githubappuser.models.UserDetail;
import com.wahyu.githubappuser.view.fragments.HomeView;

public class HomePresenterImpl implements HomePresenter {

    Context context;
    HomeView view;

    public HomePresenterImpl(Context context, HomeView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void requestUser(String name) {
        view.showLoading(true);
        ApiUtils.getUsers(name, new ApiCallback.UsersCallback() {
            @Override
            public void onSuccess(ResponseUser response) {
                view.showLoading(false);
                if (response != null)
                    view.onUsersAvailable(response.getItems());
                else
                    view.onUsersNotAvailable("");

            }

            @Override
            public void onFailure(String error) {
                view.onUsersNotAvailable(error);
                view.showLoading(false);
            }
        });
    }

    @Override
    public void onItemClick(Items data) {
        view.showLoading(true);
        ApiUtils.getUserDetail(data.getLogin(), new ApiCallback.UserCallback() {
            @Override
            public void onSuccess(UserDetail response) {
                view.showLoading(false);
                view.navigateToDetailUser(response);
            }

            @Override
            public void onFailure(String error) {
                view.showLoading(false);
            }
        });
    }
}
