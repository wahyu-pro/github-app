package com.wahyu.githubappuser.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wahyu.githubappuser.database.FavoriteHelper;

import static com.wahyu.githubappuser.database.DatabaseContract.AUTHORITY;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.CONTENT_URI;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.TABLE_NAME;

public class FavoriteProvider extends ContentProvider {

    private static final int FAVORITE = 1;
    private static final int FAVORITE_ID = 2;

    private FavoriteHelper favoriteHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVORITE);

        sUriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", FAVORITE_ID);
    }

    @Override
    public boolean onCreate() {
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE:
                cursor = favoriteHelper.queryAll();
                break;
            case FAVORITE_ID:
                cursor = favoriteHelper.queryById(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long added;
        if (sUriMatcher.match(uri) == FAVORITE) {
            added = favoriteHelper.insert(contentValues);
        } else {
            added = 0;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int deleted;
        if (sUriMatcher.match(uri) == FAVORITE_ID) {
            deleted = favoriteHelper.deleteById(uri.getLastPathSegment());
        } else {
            deleted = 0;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
