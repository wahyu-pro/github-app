package com.wahyu.consumerapp.adapter;

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
import com.wahyu.consumerapp.model.Favorite;
import com.wahyu.consumerapp.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>{
    private final List<Favorite> mData = new ArrayList<>();
    Context mContext;

    public FavoriteAdapter(List<Favorite> items, Context context) {
        mData.clear();
        mData.addAll(items);
        mContext = context;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items, parent, false);
        return new FavoriteViewHolder(mView);
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {

        // Set Animation
        holder.avatar.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

        Favorite items = mData.get(position);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.notfound);
        Glide.with(holder.itemView.getContext()).load(items.getAvatar()).apply(options).into(holder.avatar);
        holder.username.setText(items.getUsername());
        holder.email.setText(items.getEmail());

        holder.itemView.setOnClickListener(v ->
                onItemClickCallback.onItemClicked(mData.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView username;
        RelativeLayout container;
        TextView email;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.img_user);
            username = itemView.findViewById(R.id.tv_username);
            container = itemView.findViewById(R.id.container);
            email = itemView.findViewById(R.id.tv_email);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Favorite data);
    }
}
