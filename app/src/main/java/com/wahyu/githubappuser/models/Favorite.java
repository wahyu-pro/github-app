package com.wahyu.githubappuser.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {
    int id;
    String name;
    String username;
    String avatar;
    String email;
    String follower;
    String following;
    int userId;

    public Favorite(int id, String name, String username, String avatar, String email, String follower, String following, int userId) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.follower = follower;
        this.following = following;
        this.userId = userId;
    }

    protected Favorite(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
        avatar = in.readString();
        email = in.readString();
        follower = in.readString();
        following = in.readString();
        userId = in.readInt();
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(avatar);
        parcel.writeString(email);
        parcel.writeString(follower);
        parcel.writeString(following);
        parcel.writeInt(userId);
    }
}
