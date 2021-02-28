package com.wahyu.githubappuser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wahyu.githubappuser.R;
import com.wahyu.githubappuser.models.SocialResponse;

import java.util.ArrayList;
import java.util.List;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.ViewHolder>{

    private final List<SocialResponse> mData = new ArrayList<>();
    Context mContext;

    public SocialAdapter(List<SocialResponse> items, Context context) {
        mData.clear();
        mData.addAll(items);
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.avatar.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

        SocialResponse items = mData.get(position);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.notfound);
        Glide.with(holder.itemView.getContext()).load(items.getAvatarUrl()).apply(options).into(holder.avatar);
        holder.username.setText(items.getLogin());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView username, description;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.img_user);
            username = itemView.findViewById(R.id.tv_username);
            container = itemView.findViewById(R.id.container);
            description = itemView.findViewById(R.id.tv_description);
        }
    }
}
