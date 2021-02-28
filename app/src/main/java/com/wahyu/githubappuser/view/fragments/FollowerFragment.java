package com.wahyu.githubappuser.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.wahyu.githubappuser.R;
import com.wahyu.githubappuser.adapters.SocialAdapter;
import com.wahyu.githubappuser.models.SocialResponse;
import com.wahyu.githubappuser.presenters.SocialPresenter;
import com.wahyu.githubappuser.presenters.SocialPresenterImpl;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class FollowerFragment extends Fragment implements SocialView{

    SocialPresenter presenter;
    SocialAdapter adapter;
    RecyclerView rvFollower;
    TextView tvNotFound;
    String username;
    ProgressBar progressBar;

    public FollowerFragment(String username) {
        this.username = username;
        presenter = new SocialPresenterImpl(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follower, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvNotFound = view.findViewById(R.id.tv_notFoundFr);
        rvFollower = view.findViewById(R.id.follower_rv);
        progressBar = view.findViewById(R.id.pb_loader);

        presenter.requestFollowing(username);

    }

    @Override
    public void onSocialAvailable(List<SocialResponse> response) {
        adapter = new SocialAdapter(response, getContext());
        rvFollower.setAdapter(adapter);
    }

    @Override
    public void onSocialNotAvailable(String reason) {

        rvFollower.setVisibility(GONE);
        tvNotFound.setVisibility(VISIBLE);

    }

    @Override
    public void showLoading(boolean state) {
        if (state) progressBar.setVisibility(VISIBLE);
        else progressBar.setVisibility(GONE);
    }

}