package net.kinsman.takcompanion.takcompanion;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

public class SettingsFragment extends Fragment implements NumberPicker.OnValueChangeListener, TextWatcher{

    EditText playerOneName, playerTwoName;
    NumberPicker baseTimeMinute, baseTimeSecond, turnTimeMinute, turnTimeSecond;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        playerOneName = (EditText) view.findViewById(R.id.etPlayer1);
        playerTwoName = (EditText) view.findViewById(R.id.etPlayer2);

        baseTimeMinute = (NumberPicker) view.findViewById(R.id.npBaseMinute);
        baseTimeMinute.setMaxValue(60);
        baseTimeMinute.setMinValue(0);
        baseTimeMinute.setWrapSelectorWheel(true);

        baseTimeSecond = (NumberPicker) view.findViewById(R.id.npBaseSecond);
        baseTimeSecond.setMaxValue(60);
        baseTimeSecond.setMinValue(0);
        baseTimeSecond.setWrapSelectorWheel(true);

        turnTimeMinute = (NumberPicker) view.findViewById(R.id.npTurnMinute);
        turnTimeMinute.setMaxValue(60);
        turnTimeMinute.setMinValue(0);
        turnTimeMinute.setWrapSelectorWheel(true);

        turnTimeSecond = (NumberPicker) view.findViewById(R.id.npTurnSecond);
        turnTimeSecond.setMaxValue(60);
        turnTimeSecond.setMinValue(0);
        turnTimeSecond.setWrapSelectorWheel(true);

        SharedPreferences sharedPrefs = getActivity().getSharedPreferences("timerSettings", Context.MODE_PRIVATE);

        playerOneName.setText(sharedPrefs.getString("playerOneName", ""));
        playerTwoName.setText(sharedPrefs.getString("playerTwoName", ""));

        int baseTimeMillis = sharedPrefs.getInt("baseTimeMillis", 900000);

        baseTimeMinute.setValue(baseTimeMillis / 1000 / 60);
        baseTimeSecond.setValue(baseTimeMillis / 1000 % 60);

        int turnTimeMillis = sharedPrefs.getInt("turnTimeMillis", 20000);

        turnTimeMinute.setValue(turnTimeMillis / 1000 / 60);
        turnTimeSecond.setValue(turnTimeMillis / 1000 % 60);

        playerOneName.addTextChangedListener(this);
        playerTwoName.addTextChangedListener(this);
        baseTimeMinute.setOnValueChangedListener(this);
        baseTimeSecond.setOnValueChangedListener(this);
        turnTimeMinute.setOnValueChangedListener(this);
        turnTimeSecond.setOnValueChangedListener(this);

        return view;
    }

    public void saveSettings()
    {
        SharedPreferences sharedPrefs = getActivity().getSharedPreferences("timerSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString("playerOneName", playerOneName.getText().toString());
        editor.putString("playerTwoName", playerTwoName.getText().toString());

        int baseTimeMillis = (baseTimeMinute.getValue() * 60 * 1000) + (baseTimeSecond.getValue() * 1000);
        int turnTimeMillis = (turnTimeMinute.getValue() * 60 * 1000) + (turnTimeSecond.getValue() * 1000);

        editor.putInt("baseTimeMillis", baseTimeMillis);
        editor.putInt("turnTimeMillis", turnTimeMillis);

        editor.apply();
        //comment
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        saveSettings();
    }

    @Override
    public void onTextChanged(CharSequence seq, int start, int before, int count){

    }

    @Override
    public void afterTextChanged(Editable s) {
        saveSettings();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start,
                                  int count, int after) {
    }
}
