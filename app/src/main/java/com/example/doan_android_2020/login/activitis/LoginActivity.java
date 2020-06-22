package com.example.doan_android_2020.login.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_android_2020.R;
import com.example.doan_android_2020._general.interfaces.CustomSnackBar;
import com.example.doan_android_2020._general.models.User;
import com.example.doan_android_2020._general.networks.CheckNetwork;
import com.example.doan_android_2020._general.networks.FiDaiService;
import com.example.doan_android_2020._general.utils.UsersToSharePrefe;
import com.example.doan_android_2020.dashboard.DashBoardActivity;
import com.example.doan_android_2020.signup.activitis.SignupActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Hoàng Phi Đại
 * @version 0.1
 * @desc Hue city, VietNam
 * @link ff3105.github.io
 * @created 02/03/2020 12:41h
 * @Usage login activity
 */
public class LoginActivity extends AppCompatActivity {
    //views
    View view;
    TextInputLayout tilLoginEmail;
    TextInputLayout tilLoginPass;
    TextInputEditText etLoginPass;
    TextInputEditText etLoginEmail;

    //variables
    private String userName;
    private String passWord;
    private final static String TAG = "LoginActivity: ";

    //class
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //my own class
    UsersToSharePrefe usersToSharePrefe;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        view = findViewById(R.id.loginView);
        tilLoginEmail = findViewById(R.id.tilLoginEmail);
        tilLoginPass = findViewById(R.id.tilLoginPass);
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPass = findViewById(R.id.etLoginPasss);

        //remove error on edit text
        etLoginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilLoginEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etLoginPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilLoginPass.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        usersToSharePrefe = new UsersToSharePrefe(getApplicationContext());
        if (!new CheckNetwork().checkInternetConnection(getApplicationContext())) {
            final Dialog dialog = new Dialog(this, R.style.CustomDialogTheme);
            // Include dialog.xml file
            dialog.setContentView(R.layout.dialog_alert);
            dialog.setCancelable(false);
            dialog.show();

            Button btnDialogAlertSetting = dialog.findViewById(R.id.btnDialogAlertSetting);
            // if decline bg_button_default is clicked, close the custom dialog
            btnDialogAlertSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), 0);
                }
            });
        }

        if (usersToSharePrefe.checkUserLogged()) {
            loadDashboard();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return super.onCreateOptionsMenu(menu);
    }

    public void btnLogin(View view) {
        userName = Objects.requireNonNull(etLoginEmail.getText()).toString().toLowerCase().trim();
        passWord = Objects.requireNonNull(etLoginPass.getText()).toString().trim();
        if (validateInputs()) {
            new loginUsers(userName, passWord).execute();
        }
    }

    private void loadDashboard() {
        Intent i = new Intent(getApplicationContext(), DashBoardActivity.class);
        startActivity(i);
        finish();
    }

    public void btnSignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class loginUsers extends AsyncTask<Void, Void, Void> {
        String username;
        String password;
        String messageToast = "";

        loginUsers(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            FiDaiService jsonParser = new FiDaiService();
            List<NameValuePair> args = new ArrayList<>();
            args.add(new BasicNameValuePair(FiDaiService.KEY_USERNAME, username));
            args.add(new BasicNameValuePair(FiDaiService.KEY_PASSWORD, passWord));

            String json = jsonParser.callService(FiDaiService.LOGIN_URL, FiDaiService.POST, args);
            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString("status");
                    if (!status.isEmpty()) {
                        if (Integer.parseInt(status) == 0) {
                            String fullName = jsonObject.getString("fullName");
                            usersToSharePrefe.addUserToPref(new User(username, fullName));
                            loadDashboard();
                        } else if (Integer.parseInt(status) == 1) {
                            messageToast = "Sai tên tài khoản hoặc mật khẩu!";
                        } else {
                            messageToast = "Login.php error.";
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Error 166: " + e.toString());
                    messageToast = e.toString();
                }
            } else {
                messageToast = "Không nhận được thông tin từ service.";
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new CustomSnackBar(view, messageToast);
        }
    }

    /**
     * check input box
     *
     * @return false if empty
     */
    private boolean validateInputs() {
        if (userName.equals("") || passWord.equals("")) {
            if (passWord.equals("")) {
                tilLoginPass.setError("Password cannot be empty");
                etLoginPass.requestFocus();
            }
            if (userName.equals("")) {
                tilLoginEmail.setError("Username cannot be empty");
                etLoginEmail.requestFocus();
            }
            return false;
        }

        return true;
    }
}
