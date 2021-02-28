package com.wahyu.consumerapp.contract;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    // Authority yang digunakan
    public static final String AUTHORITY = "com.wahyu.githubappuser";
    private static final String SCHEME = "content";


    public static final class FavoriteColumns implements BaseColumns {
        public static final String TABLE_NAME = "favorites";

        public static final String NAME = "name";
        public static final String USERNAME = "username";
        public static final String AVATAR = "avatar";
        public static final String EMAIL = "email";
        public static final String FOLLOWER = "follower";
        public static final String FOLLOWING = "following";
        public static final String USER_ID = "user_id";


        // Base content yang digunakan untuk akses content provider
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();

    }
}
