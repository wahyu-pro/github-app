package com.wahyu.githubappuser.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.wahyu.githubappuser.R;
import com.wahyu.githubappuser.adapters.TabAdapter;
import com.wahyu.githubappuser.models.Favorite;
import com.wahyu.githubappuser.view.fragments.FollowerFragment;
import com.wahyu.githubappuser.view.fragments.FollowingFragment;

public class DetailFavoriteActivity extends AppCompatActivity implements DetailFavoriteView {

    ImageView ivBack, avatar;
    TextView tvName, tvEmail;
    TabAdapter adapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    public static final String EXTRA_DETAIL_FAVORITE = "extra_detail_favorite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);

        Favorite favorite = getIntent().getParcelableExtra(EXTRA_DETAIL_FAVORITE);
        ivBack = findViewById(R.id.iv_back_fav);
        avatar = findViewById(R.id.avatar_fav);
        tvName = findViewById(R.id.tv_name_fav);
        tvEmail = findViewById(R.id.tv_email_fav);

        displayDetailFavorite(favorite);
        ivBack.setOnClickListener(v -> onBackPressed());

    }

    @Override
    public void displayDetailFavorite(Favorite favorite) {

        tabLayout = findViewById(R.id.tabLayoutFav);
        viewPager = findViewById(R.id.viewPagerFav);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new FollowerFragment(favorite.getUsername()), "Follower");
        adapter.addFragment(new FollowingFragment(favorite.getUsername()), "Following");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.notfound);
        Glide.with(getApplicationContext()).load(favorite.getAvatar()).apply(options).into(avatar);
        tvName.setText(favorite.getName());
        tvEmail.setText(favorite.getEmail());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}