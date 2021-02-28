package com.wahyu.githubappuser.presenters;

import com.wahyu.githubappuser.models.Items;

public interface HomePresenter {

    void requestUser(String name);

    void onItemClick(Items data);

}
