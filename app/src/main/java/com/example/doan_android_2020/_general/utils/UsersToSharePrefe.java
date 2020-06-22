package com.example.doan_android_2020._general.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.doan_android_2020._general.models.Settings;
import com.example.doan_android_2020._general.models.User;
import com.example.doan_android_2020._general.networks.FiDaiService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class UsersToSharePrefe {
    private Context context;

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    private final String KEY_USERNAME = "userName";
    private final String KEY_USERFULLNAME = "userFullname";
    private final String KEY_USERIMAGE = "userImage";
    private final String KEY_USERMAIL = "userEmail";
    private final String KEY_USERPHONE = "userPhone";
    private final String KEY_USERADDRESS = "usersAddress";
    private final String KEY_USERBIO = "userBio";
    private final String KEY_USERGENDER = "userGender";

    //settings key
    private final String KEY_DARKMODE = "setting_darkmode";

    public UsersToSharePrefe(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();

    }

    /**
     * @return false if users info details empty
     */
    public boolean checkUserLogged() {
        String userName = sharedPreferences.getString(KEY_USERNAME, "");
        if (!userName.equals("")) {
            return true;
        }
        return false;
    }

    public void addUserToPref(User user) {
        editor.putString(KEY_USERNAME, user.getUserName());
        editor.putString(KEY_USERFULLNAME, user.getUserFullName());
        editor.putString(KEY_USERIMAGE, user.getUserAvatar());
        editor.apply();
    }

    public User getUserDetails() {
        String userName = sharedPreferences.getString(KEY_USERNAME, "");
        String userFullname = sharedPreferences.getString(KEY_USERFULLNAME, "");
        if (checkUserLogged()) {
            User user = new User(userName, userFullname);
            return user;
        }
        return null;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public Settings getSettings() {
        boolean DARK_MODE = sharedPreferences.getBoolean(KEY_DARKMODE, true);
        Settings settings = new Settings(DARK_MODE);
        return settings;
    }
}
