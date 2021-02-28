package com.wahyu.githubappuser.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.TABLE_NAME;
import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.USER_ID;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper dataBaseHelper;
    private static FavoriteHelper INSTANCE;

    private static SQLiteDatabase database;

    private FavoriteHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public Cursor queryAll() {
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC");
    }

    public Cursor queryById(String userId) {
        return database.rawQuery("select * from " + DATABASE_TABLE + " where " + USER_ID + "=" + userId, null);
    }

    public long insert(ContentValues values) {
        return database.replace(DATABASE_TABLE, null, values);
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, USER_ID + " = ?", new String[]{id});
    }
}
