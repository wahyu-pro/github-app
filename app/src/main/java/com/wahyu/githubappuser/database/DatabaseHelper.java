package com.wahyu.githubappuser.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbgithubapp";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_FAVORITE = String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " %s TEXT NOT NULL DEFAULT ''," +
                    " %s TEXT NOT NULL DEFAULT ''," +
                    " %s TEXT NOT NULL DEFAULT ''," +
                    " %s TEXT NOT NULL DEFAULT ''," +
                    " %s TEXT NOT NULL DEFAULT ''," +
                    " %s TEXT NOT NULL DEFAULT ''," +
                    " %s INTEGER NOT NULL DEFAULT 0" +
                    ")", DatabaseContract.FavoriteColumns.TABLE_NAME,
            DatabaseContract.FavoriteColumns._ID,
            DatabaseContract.FavoriteColumns.NAME,
            DatabaseContract.FavoriteColumns.USERNAME,
            DatabaseContract.FavoriteColumns.AVATAR,
            DatabaseContract.FavoriteColumns.EMAIL,
            DatabaseContract.FavoriteColumns.FOLLOWER,
            DatabaseContract.FavoriteColumns.FOLLOWING,
            DatabaseContract.FavoriteColumns.USER_ID);

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.FavoriteColumns.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
