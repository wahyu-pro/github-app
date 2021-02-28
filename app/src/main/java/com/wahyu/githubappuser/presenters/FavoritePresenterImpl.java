package com.wahyu.githubappuser.presenters;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;

import com.wahyu.githubappuser.models.Favorite;
import com.wahyu.githubappuser.view.LoadFavoriteCallback;
import com.wahyu.githubappuser.view.fragments.FavoriteView;
import com.wahyu.githubappuser.helpers.MappingHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.CONTENT_URI;

public class FavoritePresenterImpl implements FavoritePresenter{

    FavoriteView view;
    Context context;

    public FavoritePresenterImpl (Context context, FavoriteView view){
        this.view = view;
        this.context = context;
    }

    @Override
    public void loadFavorites() {
        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        DataObserver myObserver = new DataObserver(handler, context);
        context.getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);
        new LoadNoteAsync(context, (LoadFavoriteCallback) view).execute();
    }

    @Override
    public void onClickItem(Favorite favorite) {
        view.displayDetailFavorite(favorite);
    }

    @Override
    public void deleteFavorite(Uri uriWithId) {
        context.getContentResolver().delete(uriWithId, null, null);
        view.onDeleteSuccess();
    }

    private static class LoadNoteAsync extends AsyncTask<Void, Void, ArrayList<Favorite>> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadFavoriteCallback> weakCallback;

        private LoadNoteAsync(Context context, LoadFavoriteCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<Favorite> doInBackground(Void... voids) {
            Context context = weakContext.get();
            Cursor dataCursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);
            return MappingHelper.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<Favorite> notes) {
            super.onPostExecute(notes);
            weakCallback.get().postExecute(notes);
        }
    }

    public static class DataObserver extends ContentObserver {

        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }
    }
}
