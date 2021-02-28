package com.wahyu.githubappuser.services;

import com.wahyu.githubappuser.models.ResponseUser;
import com.wahyu.githubappuser.models.SocialResponse;
import com.wahyu.githubappuser.models.UserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.wahyu.githubappuser.BuildConfig.API_KEY;

public interface ApiServices {

    @Headers("Authorization: token "+ API_KEY)
    @GET("users")
    Call<ResponseUser> getUsers(@Query("q") String q);

    @Headers("Authorization: token "+ API_KEY)
    @GET("users/{username}")
    Call<UserDetail> getUser(@Path("username") String username);

    @Headers("Authorization: token "+ API_KEY)
    @GET("users/{username}/followers")
    Call<List<SocialResponse>> getFollowers(@Path("username") String username);

    @Headers("Authorization: token "+ API_KEY)
    @GET("users/{username}/following")
    Call<List<SocialResponse>> getFollowing(@Path("username") String username);

}
