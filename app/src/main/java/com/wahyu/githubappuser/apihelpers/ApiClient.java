package com.wahyu.githubappuser.apihelpers;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    public static <T> T client(Class<T> service, String baseUrl) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(110, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(
                        GsonConverterFactory.create(
                                new GsonBuilder().setLenient().create()
                        )
                )
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(service);
    }
}
