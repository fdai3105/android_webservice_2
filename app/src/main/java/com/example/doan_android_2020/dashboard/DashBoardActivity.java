package com.example.doan_android_2020.dashboard;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.doan_android_2020.R;
import com.example.doan_android_2020._general.utils.UsersToSharePrefe;
import com.example.doan_android_2020.dashboard.categories.fragments.CategoriesFragment;
import com.example.doan_android_2020.dashboard.home.fragments.HomeFragment;
import com.example.doan_android_2020.dashboard.user.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/**
 * @author Hoàng Phi Đại
 * @version 0.1
 * @desc Hue city, VietNam
 * @link ff3105.github.io
 * @created 15/03/2020 21:05h
 * @Usage
 */
public class DashBoardActivity extends AppCompatActivity {
    //views
    FragmentContainerView nav_host_fragment;
    BottomNavigationView navView;

    UsersToSharePrefe usersToSharePrefe;

    final Fragment HomeFragment = new HomeFragment();
    final Fragment CategoriesFragment = new CategoriesFragment();
    final Fragment UserFragment = new UserFragment();
    final FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment activeActivity = HomeFragment;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check and set dark mode
        usersToSharePrefe = new UsersToSharePrefe(getApplicationContext());
        boolean darkMode = usersToSharePrefe.getSettings().isDARK_MODE();
        if (darkMode) {
            setTheme(R.style.DashboardThemeDark);
        } else {
            setTheme(R.style.DashboardThemeLight);
        }

        setContentView(R.layout.activity_dash_board);
        nav_host_fragment = findViewById(R.id.nav_host_fragment);
        navView = findViewById(R.id.nav_view);

        /*default*/
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        //        R.id.navigation_home, R.id.navigation_home, R.id.navigation_notifications)
        //        .build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(navView, navController);

        // TODO: 04/03/2020 incomplete??????
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, UserFragment, "3").hide(UserFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, CategoriesFragment, "2").hide(CategoriesFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, HomeFragment, "1").commit();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if (navView.getSelectedItemId() == R.id.navigation_home) {

                        }
                        fragmentManager.beginTransaction().hide(activeActivity).show(HomeFragment).commit();
                        activeActivity = HomeFragment;
                        return true;
                    case R.id.navigation_categories:
                        fragmentManager.beginTransaction().hide(activeActivity).show(CategoriesFragment).commit();
                        activeActivity = CategoriesFragment;
                        return true;
                    case R.id.navigation_notifications:
                        fragmentManager.beginTransaction().hide(activeActivity).show(UserFragment).commit();
                        activeActivity = UserFragment;
                        return true;
                }
                return false;
            }
        });
    }

    //change fragment when click back button
    //user > categories > home > finish()
    @Override
    public void onBackPressed() {
        if (navView.getSelectedItemId() == R.id.navigation_home) {
            super.onBackPressed();
            finish();
        } else if (navView.getSelectedItemId() == R.id.navigation_categories) {
            navView.setSelectedItemId(R.id.navigation_home);
        } else {
            navView.setSelectedItemId(R.id.navigation_categories);
        }
    }
}
