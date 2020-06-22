package com.example.doan_android_2020.signup.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.doan_android_2020.dashboard.DashBoardActivity;
import com.example.doan_android_2020._general.models.User;
import com.example.doan_android_2020._general.networks.FiDaiService;
import com.example.doan_android_2020.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.doan_android_2020._general.interfaces.CustomSnackBar;
import com.example.doan_android_2020._general.utils.UsersToSharePrefe;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {
    //views
    View view;
    TextInputLayout tilSignUpEmail;
    TextInputLayout tilSignUpPass;
    TextInputLayout tilSignUpPassAgain;
    EditText etCreaterUsersEmail;
    EditText etCreaterUsersPass;
    EditText etCreaterUsersPassAgain;

    //my own class
    UsersToSharePrefe usersToSharePrefe;

    //variables
    private String username;
    private String password;
    private String confirmPassword;
    private final static String TAG = "SignUpActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        view = findViewById(R.id.llSignUp);

        usersToSharePrefe = new UsersToSharePrefe(getApplicationContext());

        tilSignUpEmail = findViewById(R.id.tilSignUpEmail);
        tilSignUpPass = findViewById(R.id.tilSignUpPass);
        tilSignUpPassAgain = findViewById(R.id.tilSignUpPassAgain);
        etCreaterUsersEmail = findViewById(R.id.etCreaterUsersEmail);
        etCreaterUsersPass = findViewById(R.id.etCreaterUsersPass);
        etCreaterUsersPassAgain = findViewById(R.id.etCreaterUsersPassAgain);

        //clear error
        etCreaterUsersEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilSignUpEmail.setError("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etCreaterUsersPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("ResourceType")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilSignUpPass.setError("");
                tilSignUpPass.setPasswordVisibilityToggleTintList(ColorStateList.valueOf(Color.parseColor(getString(R.color.colorPrimary))));
                tilSignUpPass.setStartIconTintList(ColorStateList.valueOf(Color.parseColor(getString(R.color.colorPrimary))));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etCreaterUsersPassAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("ResourceType")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilSignUpPassAgain.setError("");
                tilSignUpPassAgain.setPasswordVisibilityToggleTintList(ColorStateList.valueOf(Color.parseColor(getString(R.color.colorPrimary))));
                tilSignUpPassAgain.setStartIconTintList(ColorStateList.valueOf(Color.parseColor(getString(R.color.colorPrimary))));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return super.onCreateOptionsMenu(menu);
    }

    public void btnSignUp(View view) {
        username = etCreaterUsersEmail.getText().toString().toLowerCase().trim();
        password = etCreaterUsersPass.getText().toString().trim();
        confirmPassword = etCreaterUsersPassAgain.getText().toString().trim();
        if (validateInputs()) {
            new registerUser(username, password).execute();
        }
    }

    public void btnSignIn(View view) {
        onBackPressed();
    }

    @SuppressLint("StaticFieldLeak")
    private class registerUser extends AsyncTask<Void, Void, Void> {
        String username;
        String password;
        String messageToast = "";

        registerUser(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            FiDaiService fiDaiService = new FiDaiService();
            List<NameValuePair> args = new ArrayList<>();
            args.add(new BasicNameValuePair(FiDaiService.KEY_USERNAME, username));
            args.add(new BasicNameValuePair(FiDaiService.KEY_PASSWORD, password));
            String json = fiDaiService.callService(FiDaiService.REGISTER_URL, FiDaiService.POST, args);
            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString(FiDaiService.KEY_FEEDBACK_STATUS);
                    if (!status.isEmpty()) {
                        if (status.equals(String.valueOf(0))) {
                            usersToSharePrefe.addUserToPref(new User(username, "Guest"));
                            loadDashboard();
                        } else if (status.equals(String.valueOf(1))) {
                            messageToast = "Email đã được sử dụng, hãy thử tên khác.";
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "113: " + e.toString());
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

    private void loadDashboard() {
        Intent i = new Intent(getApplicationContext(), DashBoardActivity.class);
        startActivity(i);
        finish();
    }

    private boolean validateInputs() {
        //làm màu: set error cho TẤT CẢ fields lỗi
        if (username.equals("") || password.equals("") || confirmPassword.equals("")) {
            if (confirmPassword.equals("")) {
                tilSignUpPassAgain.setError("Confirm Password cannot be empty");
                tilSignUpPassAgain.setPasswordVisibilityToggleTintList(ColorStateList.valueOf(Color.parseColor("#cf6679")));
                tilSignUpPassAgain.setStartIconTintList(ColorStateList.valueOf(Color.parseColor("#cf6679")));
                etCreaterUsersPassAgain.requestFocus();
            }
            if (password.equals("")) {
                tilSignUpPass.setError("Password cannot be empty");
                tilSignUpPass.setPasswordVisibilityToggleTintList(ColorStateList.valueOf(Color.parseColor("#cf6679")));
                tilSignUpPass.setStartIconTintList(ColorStateList.valueOf(Color.parseColor("#cf6679")));
                etCreaterUsersPass.requestFocus();
            }
            if (username.equals("")) {
                tilSignUpEmail.setError("Username cannot be empty");
                etCreaterUsersEmail.requestFocus();
            }
            return false;
        }

        if (!password.equals(confirmPassword)) {
            tilSignUpPassAgain.setError("Those passwords didn't match. Try again.");
            etCreaterUsersPassAgain.requestFocus();
            return false;
        }
        return true;
    }
}
