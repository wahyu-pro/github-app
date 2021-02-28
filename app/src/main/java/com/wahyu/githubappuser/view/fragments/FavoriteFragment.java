package com.wahyu.githubappuser.view.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.wahyu.githubappuser.R;
import com.wahyu.githubappuser.adapters.FavoriteAdapter;
import com.wahyu.githubappuser.models.Favorite;
import com.wahyu.githubappuser.presenters.FavoritePresenter;
import com.wahyu.githubappuser.presenters.FavoritePresenterImpl;
import com.wahyu.githubappuser.view.DetailFavoriteActivity;
import com.wahyu.githubappuser.view.LoadFavoriteCallback;

import java.util.ArrayList;
import java.util.Objects;

import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.CONTENT_URI;

public class FavoriteFragment extends Fragment implements FavoriteView, LoadFavoriteCallback {

    RecyclerView rvFavorite;
    ProgressBar progressBar;
    FavoritePresenter presenter;
    FavoriteAdapter adapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavorite = view.findViewById(R.id.favorite_rv);
        progressBar = view.findViewById(R.id.progressbar);

        presenter = new FavoritePresenterImpl(getContext(), this);
        presenter.loadFavorites();

    }

    @Override
    public void preExecute() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE));
    }

    @Override
    public void postExecute(ArrayList<Favorite> data) {

        Objects.requireNonNull(getActivity()).runOnUiThread(() -> progressBar.setVisibility(View.GONE));
        if (data.size() > 0) {
            adapter = new FavoriteAdapter(data, getContext());
            rvFavorite.setAdapter(adapter);
            adapter.setOnItemClickCallback(new FavoriteAdapter.OnItemClickCallback() {
                @Override
                public void onItemClicked(Favorite data) {
                    presenter.onClickItem(data);
                }

                @Override
                public void onDeleteFavorite(Favorite data) {
                    Uri uriWithId = Uri.parse(CONTENT_URI + "/" + data.getUserId());
                    presenter.deleteFavorite(uriWithId);
                }
            });
        } else {
            adapter = new FavoriteAdapter(data, getContext());
            rvFavorite.setAdapter(adapter);
            Toast.makeText(getContext(), "Favorite not found", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void displayDetailFavorite(Favorite favorite) {
        startActivity(new Intent(getActivity(), DetailFavoriteActivity.class)
                .putExtra(DetailFavoriteActivity.EXTRA_DETAIL_FAVORITE, favorite));
    }

    @Override
    public void onDeleteSuccess() {
        Toast.makeText(getContext(), "Success Deleted from favorite", Toast.LENGTH_LONG).show();
        presenter.loadFavorites();
    }
}