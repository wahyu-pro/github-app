package com.wahyu.githubappuser.view.fragments;

public interface SettingView {

    void onSwitchChanged(boolean state);
    void displayAlarm(String time);
    void onAlarmSaved(String repeatTime);

}
