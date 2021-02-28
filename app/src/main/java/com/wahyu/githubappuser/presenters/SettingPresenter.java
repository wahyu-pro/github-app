package com.wahyu.githubappuser.presenters;

import android.app.Activity;

import androidx.fragment.app.FragmentManager;

public interface SettingPresenter {

    void changeSwitch(boolean state);
    void setReminder(FragmentManager manager);
    void saveReminder(Activity activity);
    void cancelReminder(Activity activity);

}
