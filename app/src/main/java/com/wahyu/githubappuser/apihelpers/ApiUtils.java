package com.wahyu.githubappuser.apihelpers;

import com.wahyu.githubappuser.models.ResponseUser;
import com.wahyu.githubappuser.models.SocialResponse;
import com.wahyu.githubappuser.models.UserDetail;
import com.wahyu.githubappuser.services.ApiServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUtils {

    public static void getUsers(String name, ApiCallback.UsersCallback callback) {
        ApiClient.client(ApiServices.class, "https://api.github.com/search/")
                .getUsers(name)
                .enqueue(new Callback<ResponseUser>() {
                    @Override
                    public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseUser> call, Throwable t) {
                        callback.onFailure(t.getMessage());
                    }
                });
    }

    public static void getUserDetail(String username, ApiCallback.UserCallback callback) {
        ApiClient.client(ApiServices.class, "https://api.github.com/")
                .getUser(username)
                .enqueue(new Callback<UserDetail>() {
                    @Override
                    public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<UserDetail> call, Throwable t) {
                        callback.onFailure(t.getMessage());
                    }
                });
    }

    public static void getFollower(String username, ApiCallback.SocialCallback callback) {
        ApiClient.client(ApiServices.class, "https://api.github.com/")
                .getFollowers(username)
                .enqueue(new Callback<List<SocialResponse>>() {
                    @Override
                    public void onResponse(Call<List<SocialResponse>> call, Response<List<SocialResponse>> response) {
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<SocialResponse>> call, Throwable t) {
                        callback.onFailure(t.getMessage());
                    }
                });
    }

    public static void getFollowing(String username, ApiCallback.SocialCallback callback) {
        ApiClient.client(ApiServices.class, "https://api.github.com/")
                .getFollowing(username)
                .enqueue(new Callback<List<SocialResponse>>() {
                    @Override
                    public void onResponse(Call<List<SocialResponse>> call, Response<List<SocialResponse>> response) {
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<SocialResponse>> call, Throwable t) {
                        callback.onFailure(t.getMessage());
                    }
                });
    }

}
