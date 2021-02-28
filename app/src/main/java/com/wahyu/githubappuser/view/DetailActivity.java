package com.wahyu.githubappuser.view;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.wahyu.githubappuser.R;
import com.wahyu.githubappuser.adapters.TabAdapter;
import com.wahyu.githubappuser.helpers.MappingHelper;
import com.wahyu.githubappuser.models.Favorite;
import com.wahyu.githubappuser.models.UserDetail;
import com.wahyu.githubappuser.presenters.DetailPresenterImpl;
import com.wahyu.githubappuser.view.fragments.FollowerFragment;
import com.wahyu.githubappuser.view.fragments.FollowingFragment;

import static com.wahyu.githubappuser.database.DatabaseContract.FavoriteColumns.CONTENT_URI;

public class DetailActivity extends AppCompatActivity implements DetailView {

    public static final String EXTRA_KEY = "extra_key";
    DetailPresenterImpl presenter;

    TabAdapter adapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView avatar, ivBack;
    FloatingActionButton fabBtn;
    TextView tvUsername, tvEmail;
    Uri uriWithId;
    Favorite favorite;
    boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        presenter = new DetailPresenterImpl(getApplicationContext(), this);

        Bundle bundle = getIntent().getExtras();
        UserDetail userDetail = (UserDetail) bundle.getSerializable(EXTRA_KEY);

        uriWithId = Uri.parse(CONTENT_URI + "/" + userDetail.getId());
        if (uriWithId != null) {
            Cursor cursor = getContentResolver().query(uriWithId, null, null, null, null);
            if (cursor != null) {
                favorite = MappingHelper.mapCursorToObject(cursor);
                cursor.close();
            }
        }

        // initview
        avatar = findViewById(R.id.avatar);
        tvUsername = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        ivBack = findViewById(R.id.iv_back);
        fabBtn = findViewById(R.id.fab_btn);

        presenter.onUserDetailAvailable(userDetail);
        ivBack.setOnClickListener(v -> onBackPressed());
        fabBtn.setOnClickListener(v -> {
            if (favorite == null && !state)
                presenter.addToFavorite(userDetail);
            else
                presenter.deleteFromFavorite(uriWithId);
        });

    }

    @Override
    public void displayUserDetail(UserDetail data) {

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new FollowerFragment(data.getLogin()), "Follower");
        adapter.addFragment(new FollowingFragment(data.getLogin()), "Following");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.notfound);
        Glide.with(getApplicationContext()).load(data.getAvatarUrl()).apply(options).into(avatar);

        tvUsername.setText(data.getName());
        tvEmail.setText(data.getEmail());
        if (favorite != null)
            fabBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));


    }

    @Override
    public void onSuccessAddFavorite() {
        state = true;
        runOnUiThread(() -> fabBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24)));
        Toast.makeText(getApplicationContext(), "Success add to favorite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessDeleteFavorite() {
        state = false;
        runOnUiThread(() -> fabBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24)));
        Toast.makeText(getApplicationContext(), "Success deleted from favorite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}