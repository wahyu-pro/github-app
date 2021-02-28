package com.wahyu.githubappuser.presenters;

import android.app.Activity;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;

import com.wahyu.githubappuser.receiver.AlarmReceiver;
import com.wahyu.githubappuser.view.fragments.SettingView;
import com.wahyu.githubappuser.view.fragments.TimePickerFragment;

import java.util.Calendar;
import java.util.Locale;

public class SettingPresenterImpl implements SettingPresenter, TimePickerFragment.DialogTimeListener {

    Context context;
    SettingView view;
    final String TIME_PICKER_REPEAT_TAG = "TimePickerRepeat";
    String repeatTime;
    AlarmReceiver receiver;

    public SettingPresenterImpl(Context context, SettingView view) {
        this.context = context;
        this.view = view;
        receiver = new AlarmReceiver();
    }

    @Override
    public void changeSwitch(boolean state) {
        view.onSwitchChanged(state);
    }

    @Override
    public void setReminder(FragmentManager manager) {
        new TimePickerFragment(this).show(manager, TIME_PICKER_REPEAT_TAG);
    }

    @Override
    public void saveReminder(Activity activity) {
        receiver.setReminder(
                activity, AlarmReceiver.TYPE_REPEATING,
                repeatTime, "Back to application"
        );
        view.onAlarmSaved(repeatTime);
    }

    @Override
    public void cancelReminder(Activity activity) {
        receiver.cancelAlarm(activity);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDialogTimeSet(String tag, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        repeatTime = dateFormat.format(calendar.getTime());
        view.displayAlarm(dateFormat.format(calendar.getTime()));
    }
}
