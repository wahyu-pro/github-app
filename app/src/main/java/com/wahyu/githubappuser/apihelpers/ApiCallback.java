package com.wahyu.githubappuser.apihelpers;

import com.wahyu.githubappuser.models.ResponseUser;
import com.wahyu.githubappuser.models.SocialResponse;
import com.wahyu.githubappuser.models.UserDetail;

import java.util.List;

public interface ApiCallback {

    interface UsersCallback{
        void onSuccess(ResponseUser response);
        void onFailure(String error);
    }

    interface UserCallback{
        void onSuccess(UserDetail response);
        void onFailure(String error);
    }

    interface SocialCallback{
        void onSuccess(List<SocialResponse> response);
        void onFailure(String error);
    }
}
