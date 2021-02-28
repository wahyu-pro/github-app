package com.wahyu.consumerapp.helper;

import android.database.Cursor;

import com.wahyu.consumerapp.contract.DatabaseContract;
import com.wahyu.consumerapp.model.Favorite;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<Favorite> mapCursorToArrayList(Cursor favoriteCursor) {
        ArrayList<Favorite> notesList = new ArrayList<>();

        while (favoriteCursor.moveToNext()) {
            int id = favoriteCursor.getInt(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns._ID));
            String name = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.NAME));
            String username = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.USERNAME));
            String avatar = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR));
            String email = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.EMAIL));
            String follower = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.FOLLOWER));
            String following = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.FOLLOWING));
            int favorite = favoriteCursor.getInt(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.USER_ID));

            notesList.add(new Favorite(id, name, username, avatar, email, follower, following, favorite));
        }

        return notesList;
    }

    public static Favorite mapCursorToObject(Cursor favoriteCursor) {
        if(favoriteCursor != null && favoriteCursor.moveToFirst()){
            int id = favoriteCursor.getInt(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns._ID));
            String name = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.NAME));
            String username = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.USERNAME));
            String avatar = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR));
            String email = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.EMAIL));
            String follower = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.FOLLOWER));
            String following = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.FOLLOWING));
            int favorite = favoriteCursor.getInt(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.USER_ID));

            return new Favorite(id, name, username, avatar, email, follower, following, favorite);
        }else return null;
    }
}
