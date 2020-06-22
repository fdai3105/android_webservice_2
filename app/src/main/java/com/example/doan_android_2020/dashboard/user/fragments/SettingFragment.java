package com.example.doan_android_2020.dashboard.user.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.doan_android_2020.R;
import com.example.doan_android_2020._general.utils.UsersToSharePrefe;
import com.example.doan_android_2020.dashboard.DashBoardActivity;
import com.example.doan_android_2020.login.activitis.LoginActivity;

public class SettingFragment extends PreferenceFragment {
    SwitchPreference spre;
    EditTextPreference edit;
    Preference logout;

    SharedPreferences sharedPreferences;
    UsersToSharePrefe usersToSharePrefe;

    public SettingFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        spre = (SwitchPreference) findPreference("setting_darkmode");
        edit = (EditTextPreference) findPreference("edit");
        logout = findPreference("setting_logout");

        usersToSharePrefe = new UsersToSharePrefe(getActivity().getApplicationContext());
        //SettingFragment theme follow by HomeFragment theme ???
        if (spre != null) {
            spre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    startActivity(new Intent(getActivity().getApplicationContext(), DashBoardActivity.class));
                    getActivity().finish();
                    return true;
                }
            });
        }

        //set seconds text value
        if (edit != null) {
            edit.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    edit.setSummary(newValue.toString());
                    return true;
                }
            });
        }

        if (logout != null) {
            logout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    usersToSharePrefe.logoutUser();
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    return true;
                }
            });
        }

    }
}
