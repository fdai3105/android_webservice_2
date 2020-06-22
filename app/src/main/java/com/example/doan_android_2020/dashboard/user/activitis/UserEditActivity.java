package com.example.doan_android_2020.dashboard.user.activitis;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.doan_android_2020.R;
import com.example.doan_android_2020._general.models.User;
import com.example.doan_android_2020._general.networks.FiDaiService;
import com.example.doan_android_2020._general.utils.UsersToSharePrefe;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Hoàng Phi Đại
 * @version 0.1
 * @desc Hue city, VietNam
 * @link ff3105.github.io
 * @created 03/03/2020 18:23h
 */

public class UserEditActivity extends AppCompatActivity {
    //Views
    View view;
    CircleImageView iv_UserEdit_Image;
    TextInputEditText et_UserEdit_UserName;
    TextInputEditText et_UserEdit_FullName;
    TextInputEditText et_UserEdit_Email;
    TextInputEditText et_UserEdit_Phone;
    TextInputEditText et_UserEdit_Address;
    TextInputEditText et_UserEdit_Bio;
    RadioGroup et_UserEdit_Gender;
    RadioButton et_UserEdit_Gender_Male;
    RadioButton et_UserEdit_Gender_Female;
    LinearLayout llGender;

    //class
    private UsersToSharePrefe usersToSharePrefe;

    //variable
    private static final String TAG = "UserEditActivity: ";
    private Bitmap mBitmap;
    private ByteArrayOutputStream byteArrayOutputStream;
    byte[] byteArray;
    String ConvertImage;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //check and set dark mode
        usersToSharePrefe = new UsersToSharePrefe(getApplicationContext());
        boolean darkMode = usersToSharePrefe.getSettings().isDARK_MODE();
        if (darkMode) {
            setTheme(R.style.UserEditThemeDark);
        } else {
            setTheme(R.style.UserEditThemeLight);
        }

        setContentView(R.layout.activity_user_edit);

        //check dark mode
        view = findViewById(R.id.view_userEdit);
        iv_UserEdit_Image = findViewById(R.id.iv_UserEdit_Image);
        if (darkMode) {
            view.setBackground(getResources().getDrawable(R.drawable.bg_user_edit_dark));
            iv_UserEdit_Image.setBorderColor(Color.parseColor("#0C0C0C"));
        } else {
            view.setBackground(getResources().getDrawable(R.drawable.bg_user_edit));
        }

        //#region findViewById
        et_UserEdit_UserName = findViewById(R.id.et_UserEdit_UserName);
        et_UserEdit_FullName = findViewById(R.id.et_UserEdit_FullName);
        et_UserEdit_Email = findViewById(R.id.et_UserEdit_Email);
        et_UserEdit_Phone = findViewById(R.id.et_UserEdit_Phone);
        et_UserEdit_Address = findViewById(R.id.et_UserEdit_Address);
        et_UserEdit_Bio = findViewById(R.id.et_UserEdit_Bio);
        et_UserEdit_Gender = findViewById(R.id.et_UserEdit_Gender);
        et_UserEdit_Gender_Male = findViewById(R.id.et_UserEdit_Gender_Male);
        et_UserEdit_Gender_Female = findViewById(R.id.et_UserEdit_Gender_Female);
        llGender = findViewById(R.id.llGender);
        //endregion

        byteArrayOutputStream = new ByteArrayOutputStream();
        loadDetailUser();

        iv_UserEdit_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 21);
            }
        });
    }

    private void loadDetailUser() {
        if (new UsersToSharePrefe(getApplicationContext()).checkUserLogged()) {
            User user = new UsersToSharePrefe(getApplicationContext()).getUserDetails();
            new loadUser(user.getUserName()).execute();
        }
    }

    private void saveEditUser() {
        String username = new UsersToSharePrefe(getApplicationContext()).getUserDetails().getUserName();
        String fullname = et_UserEdit_FullName.getText().toString();
        String email = et_UserEdit_Email.getText().toString();
        String phone = et_UserEdit_Phone.getText().toString();
        String address = et_UserEdit_Address.getText().toString();
        String bio = et_UserEdit_Bio.getText().toString();
        String gender = "";

        //#region làm màu: set error cho thằng Radio group
        RadioButton rbTemp = findViewById(et_UserEdit_Gender.getCheckedRadioButtonId());
        if (!(et_UserEdit_Gender.getCheckedRadioButtonId() == -1)) {
            gender = rbTemp.getText().toString();
        }
        et_UserEdit_Gender_Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llGender.setBackground(null);
            }
        });
        et_UserEdit_Gender_Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llGender.setBackground(null);
            }
        });
        //làm màu: set error TẤT CẢ những fiels bị empty
        if (fullname.equals("") || email.equals("") || phone.equals("") || gender.equals("")) {
            if (phone.equals("")) {
                et_UserEdit_Phone.setError("Số điện thoại bạn là gì?");
                et_UserEdit_Phone.requestFocus();
            }
            if (email.equals("")) {
                et_UserEdit_Email.setError("Email bạn là gì?");
                et_UserEdit_Email.requestFocus();
            }
            if (fullname.equals("")) {
                et_UserEdit_FullName.setError("Tên bạn là gì?");
                et_UserEdit_FullName.requestFocus();
            }
            if (gender.equals("")) {
                llGender.setBackgroundResource(R.drawable.border_error);
            }
        } else {
            if (mBitmap != null) {
                Bitmap resize = Bitmap.createScaledBitmap(mBitmap,200, 200,false);
                resize.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
                ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            } else {
                mBitmap = ((BitmapDrawable)iv_UserEdit_Image.getDrawable()).getBitmap();
                Bitmap resize = Bitmap.createScaledBitmap(mBitmap,200, 200,false);
                resize.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
                ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            }
            new saveUser(username, fullname, ConvertImage, email, phone, address, bio, gender).execute();
            onBackPressed();
        }
        //#endregion

        usersToSharePrefe.addUserToPref(new User(username, fullname));
    }


    @SuppressLint("StaticFieldLeak")
    public class saveUser extends AsyncTask<Boolean, Void, Void> {
        String username;
        String fullName;
        String imageName;
        String image;
        String email;
        String phone;
        String address;
        String bio;
        String gender;
        String messageToast = "";

        public saveUser(String username, String fullName, String image, String email, String phone, String address, String bio, String gender) {
            this.username = username;
            this.fullName = fullName;
            imageName = username + "Avatar";
            this.image = image;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.bio = bio;
            this.gender = gender;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Boolean... booleans) {
            FiDaiService fiDaiService = new FiDaiService();
            List<NameValuePair> args = new ArrayList<>();
            args.add(new BasicNameValuePair(FiDaiService.KEY_USERNAME, username));
            args.add(new BasicNameValuePair(FiDaiService.KEY_FULLNAME, fullName));
            if (image != null) {
                args.add(new BasicNameValuePair(FiDaiService.KEY_USERIMAGENAME, imageName));
                args.add(new BasicNameValuePair(FiDaiService.KEY_USERIMAGE, image));
            }
            args.add(new BasicNameValuePair(FiDaiService.KEY_EMAIL, email));
            args.add(new BasicNameValuePair(FiDaiService.KEY_PHONE, phone));
            args.add(new BasicNameValuePair(FiDaiService.KEY_ADDRESS, address));
            args.add(new BasicNameValuePair(FiDaiService.KEY_BIO, bio));
            args.add(new BasicNameValuePair(FiDaiService.KEY_GENDER, gender));
            String json = fiDaiService.callService(FiDaiService.EDIT_URL, FiDaiService.POST, args);
            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String feedback = jsonObject.getString("status");
                    if (!feedback.isEmpty()) {
                        if (feedback.equals(String.valueOf(0))) {
                            messageToast = "Thay đổi thành cônggg.";
                        } else if (feedback.equals(String.valueOf(1))) {
                            messageToast = "Lỗi thay đổi ??";
                        } else {
                            messageToast = "Edit.php error.";
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Error 234:" + e.toString());
                    messageToast = json;
                }
            } else {
                messageToast = "Không nhận được thông tin từ service.";
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(UserEditActivity.this, messageToast, Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class loadUser extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String userName;
        String userFullName;
        String userImage;
        String userEmail;
        String userPhone;
        String usersAddress;
        String usersBio;
        String usersGender = "";

        String messageToast;

        public loadUser(String userName) {
            this.userName = userName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            FiDaiService jsonParser = new FiDaiService();
            List<NameValuePair> args = new ArrayList<>();
            args.add(new BasicNameValuePair(FiDaiService.KEY_USERNAME, userName));

            String json = jsonParser.callService(FiDaiService.USER_URL, FiDaiService.POST, args);
            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString(FiDaiService.KEY_FEEDBACK_STATUS);
                    JSONArray user = jsonObject.getJSONArray(FiDaiService.KEY_USER);
                    JSONObject obj = (JSONObject) user.get(0);
                    if (!status.isEmpty()) {
                        if (Integer.parseInt(status) == 0) {
                            userFullName = obj.getString(FiDaiService.KEY_FULLNAME);
                            userImage = obj.getString(FiDaiService.KEY_USERIMAGE);
                            userEmail = obj.getString(FiDaiService.KEY_EMAIL);
                            userPhone = obj.getString(FiDaiService.KEY_PHONE);
                            usersAddress = obj.getString(FiDaiService.KEY_ADDRESS);
                            usersBio = obj.getString(FiDaiService.KEY_BIO);
                            usersGender = obj.getString(FiDaiService.KEY_GENDER);
                        } else if (status.equals(String.valueOf(1))) {
                            messageToast = "Error $stmt->fetch() at user.php";
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Error 290:" + e.toString());
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
            Glide.with(getApplicationContext()).load(userImage).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).placeholder(R.drawable.placeholder_user).into(iv_UserEdit_Image);
            et_UserEdit_UserName.setText(userName);
            et_UserEdit_UserName.setEnabled(false);
            if (!userFullName.equals("Guest")) {
                et_UserEdit_FullName.setText(userFullName);
            }
            et_UserEdit_Email.setText(userEmail);
            et_UserEdit_Phone.setText(userPhone);
            et_UserEdit_Address.setText(usersAddress);
            et_UserEdit_Bio.setText(usersBio);
            if (usersGender.equals("Male")) {
                et_UserEdit_Gender_Male.setChecked(true);
            } else if (usersGender.equals("Female")) {
                et_UserEdit_Gender_Female.setChecked(true);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    iv_UserEdit_Image.setImageBitmap(mBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_useredit, menu);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_edituser_save:
                saveEditUser();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
