package com.wahyu.githubappuser.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.wahyu.githubappuser.R;
import com.wahyu.githubappuser.adapters.UserAdapter;
import com.wahyu.githubappuser.models.Items;
import com.wahyu.githubappuser.models.UserDetail;
import com.wahyu.githubappuser.presenters.HomePresenterImpl;
import com.wahyu.githubappuser.view.DetailActivity;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HomeFragment extends Fragment implements HomeView {

    EditText searchInput;
    ImageView imgHint;
    RecyclerView rvUsers;
    UserAdapter adapter;
    HomePresenterImpl presenter;
    ProgressBar loader;

    public HomeFragment() {
        // Required empty public constructor
        presenter = new HomePresenterImpl(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchInput = view.findViewById(R.id.search_input);
        rvUsers = view.findViewById(R.id.users_rv);
        imgHint = view.findViewById(R.id.img_hint);
        loader = view.findViewById(R.id.loader);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                imgHint.setVisibility(GONE);
                presenter.requestUser(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onUsersAvailable(List<Items> users) {
        // Todo for set users to recyclerview
        adapter = new UserAdapter(users, getContext());

        rvUsers.setVisibility(VISIBLE);
        rvUsers.setAdapter(adapter);

        // handle onitemclicked
        adapter.setOnItemClickCallback(data -> presenter.onItemClick(data));
    }

    @Override
    public void onUsersNotAvailable(String reason) {
        rvUsers.setVisibility(GONE);
    }


    @Override
    public void navigateToDetailUser(UserDetail userDetail) {
        startActivity(new Intent(getContext(), DetailActivity.class)
                .putExtra(DetailActivity.EXTRA_KEY, userDetail));
    }

    @Override
    public void showLoading(boolean state) {
        if (state) loader.setVisibility(VISIBLE);
        else loader.setVisibility(GONE);
    }

}