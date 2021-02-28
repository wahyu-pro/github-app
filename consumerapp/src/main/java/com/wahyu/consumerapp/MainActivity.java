package com.wahyu.consumerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.wahyu.consumerapp.adapter.FavoriteAdapter;
import com.wahyu.consumerapp.model.Favorite;
import com.wahyu.consumerapp.presenter.FavoritePresenter;
import com.wahyu.consumerapp.presenter.FavoritePresenterImpl;
import com.wahyu.consumerapp.view.FavoriteView;
import com.wahyu.consumerapp.view.LoadFavoriteCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FavoriteView, LoadFavoriteCallback {

    RecyclerView rvFavorite;
    ProgressBar progressBar;
    FavoritePresenter presenter;
    FavoriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFavorite = findViewById(R.id.favorite_rv);
        progressBar = findViewById(R.id.progressbar);

        presenter = new FavoritePresenterImpl(getApplicationContext(), this);
        presenter.loadFavorites();
    }

    @Override
    public void displayDetailFavorite(Favorite favorite) {

    }

    @Override
    public void preExecute() {
        runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE));
    }

    @Override
    public void postExecute(ArrayList<Favorite> data) {
        runOnUiThread(() -> progressBar.setVisibility(View.GONE));
        if (data.size() > 0) {
            adapter = new FavoriteAdapter(data, getApplicationContext());
            rvFavorite.setAdapter(adapter);
            adapter.setOnItemClickCallback(favorite ->
                    presenter.onClickItem(favorite));
        } else
            Toast.makeText(getApplicationContext(), "Favorite not found", Toast.LENGTH_LONG).show();
    }
}