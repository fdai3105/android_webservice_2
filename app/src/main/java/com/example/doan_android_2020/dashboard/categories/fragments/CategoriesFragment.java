package com.example.doan_android_2020.dashboard.categories.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.doan_android_2020.R;
import com.example.doan_android_2020._general.utils.UsersToSharePrefe;

public class CategoriesFragment extends Fragment {
    private UsersToSharePrefe usersToSharePrefe;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //usersToSharePrefe = new UsersToSharePrefe(getActivity().getApplicationContext());
        //boolean darkMode = usersToSharePrefe.getSettings().isDARK_MODE();
        //if (darkMode) {
        //    getContext().getTheme().applyStyle(R.style.CategoriesFragmentTheme_Dark, true);
        //} else {
        //    getContext().getTheme().applyStyle(R.style.CategoriesFragmentTheme_Light, true);
        //}
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        return root;
    }
}
