package com.wahyu.githubappuser.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wahyu.githubappuser.R;
import com.wahyu.githubappuser.presenters.SettingPresenter;
import com.wahyu.githubappuser.presenters.SettingPresenterImpl;

public class SettingFragment extends Fragment implements SettingView, View.OnClickListener {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchState;
    Button btnSetAlarm;
    Button btnSave;
    TextView tvTime;
    SettingPresenter presenter;
    boolean state;

    public SettingFragment() {
        // Required empty public constructor
        presenter = new SettingPresenterImpl(getActivity(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switchState = view.findViewById(R.id.switcher);
        btnSetAlarm = view.findViewById(R.id.btn_set_alarm);
        btnSave = view.findViewById(R.id.btn_save_alarm);
        tvTime = view.findViewById(R.id.tv_time);

        switchState.setOnClickListener(this);
        btnSetAlarm.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onSwitchChanged(boolean state) {
        if (!state) {
            presenter.cancelReminder(getActivity());
            btnSave.setEnabled(false);
        }

        btnSetAlarm.setEnabled(state);
    }

    @Override
    public void displayAlarm(String time) {
        btnSetAlarm.setText(time);
        tvTime.setText(time);

        btnSave.setEnabled(true);
    }

    @Override
    public void onAlarmSaved(String repeatTime) {
        Toast.makeText(getContext(), "Reminder set up", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.switcher:
                state = !state;
                presenter.changeSwitch(state);
                break;
            case R.id.btn_set_alarm:
                presenter.setReminder(getFragmentManager());
                break;
            case R.id.btn_save_alarm:
                presenter.saveReminder(getActivity());
                break;
        }
    }
}