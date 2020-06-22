package com.example.doan_android_2020.dashboard.user.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.doan_android_2020.R;
import com.example.doan_android_2020._general.networks.FiDaiService;
import com.example.doan_android_2020._general.utils.UsersToSharePrefe;
import com.example.doan_android_2020.dashboard.user.activitis.UserEditActivity;
import com.example.doan_android_2020.login.activitis.LoginActivity;

import java.util.Objects;

/**
 * @author Hoàng Phi Đại
 * @version 0.1
 * @desc Hue city, VietNam
 * @link ff3105.github.io
 * @created 03/03/2020 10:56h
 * @Usage xử lí user fragment trong dashboard
 * <p>
 * {@link SettingFragment}
 */

public class UserFragment extends Fragment implements View.OnClickListener {
    //views
    private ImageView ivfragmentUserImage;
    private TextView tvfragmentUserFullname;
    private LinearLayout tvfragmentUser;

    //my own class
    private UsersToSharePrefe usersToSharePrefe;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        usersToSharePrefe = new UsersToSharePrefe(getActivity().getApplicationContext());
        boolean darkMode = usersToSharePrefe.getSettings().isDARK_MODE();
        if (darkMode) {
            getContext().getTheme().applyStyle(R.style.UserPragmentTheme_Dark, true);
        } else {
            getContext().getTheme().applyStyle(R.style.UserPragmentTheme_Light, true);
        }
        View root = inflater.inflate(R.layout.fragment_user, container, false);

        ivfragmentUserImage = root.findViewById(R.id.ivfragmentUserImage);
        tvfragmentUser = root.findViewById(R.id.tvfragmentUser);
        tvfragmentUserFullname = root.findViewById(R.id.tvfragmentUserFullname);

        tvfragmentUser.setOnClickListener(this);

        setAvatar();
        tvfragmentUserFullname.setText(usersToSharePrefe.getUserDetails().getUserFullName());

        //add settingFragment below R.layout.fragment_user
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.viewUser, new SettingFragment())
                .commit();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        tvfragmentUserFullname.setText(usersToSharePrefe.getUserDetails().getUserFullName());
        setAvatar();
    }

    private void setAvatar() {
        String userAvatar = FiDaiService.imgUserAva + usersToSharePrefe.getUserDetails().getUserName() + "Avatar.jpg";
        Glide.with(getContext()).load(userAvatar).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.drawable.placeholder_user).into(ivfragmentUserImage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvfragmentUser:
                loadUserEdit();
                break;
        }
    }

    private void loadUserEdit() {
        Intent intent = new Intent(getContext(), UserEditActivity.class);
        startActivity(intent);
    }

}
