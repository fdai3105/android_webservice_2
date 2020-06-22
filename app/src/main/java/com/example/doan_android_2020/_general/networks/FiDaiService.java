package com.example.doan_android_2020._general.networks;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Hoàng Phi Đại
 * @version 0.1
 * @desc Hue city, VietNam
 * @link ff3105.github.io
 * @created 06/03/2020 21:44h
 * @Usage
 */
public class FiDaiService {
    //variable
    static InputStream is = null;
    static String response = null;
    private final static String TAG = "FiDaiService: ";

    //Service Key
    public final static int GET = 1;
    public final static int POST = 2;
    public final static String ip = "http://192.168.1.6/";
    public final static String REGISTER_URL = ip + "DoAn_Android_2020/users/register_user.php";
    public final static String LOGIN_URL = ip + "DoAn_Android_2020/users/login_user.php";
    public final static String EDIT_URL = ip + "DoAn_Android_2020/users/edit_user.php";
    public final static String USER_URL = ip + "DoAn_Android_2020/users/show_user.php";

    public final static String GET_All_ITEMS_URL = ip + "DoAn_Android_2020/items/show_all_items.php";
    public final static String GET_SINGLE_ITEM_URL = ip + "DoAn_Android_2020/items/show_single_item.php";
    public final static String imgUserAva = ip + "DoAn_Android_2020/users/userImage/";

    public final static String KEY_USERNAME = "userName";
    public final static String KEY_PASSWORD = "userPassword";
    public final static String KEY_FULLNAME = "userFullName";
    public final static String KEY_USERIMAGENAME = "userImageName";
    public final static String KEY_USERIMAGE = "userImage";
    public final static String KEY_EMAIL = "userEmail";
    public final static String KEY_PHONE = "userPhone";
    public final static String KEY_ADDRESS = "userAddress";
    public final static String KEY_BIO = "userBio";
    public final static String KEY_GENDER = "userGender";
    public final static String KEY_USER = "user";
    public final static String KEY_FEEDBACK_STATUS = "status";
    public final static String KEY_FEEDBACK_MESSAGE = "message";

    public final static String KEY_ITEM_NAME = "item_name";
    public final static String a = "";

    public String callService(String url, int method) {
        return this.callService(url, method, null);
    }

    public String callService(String url, int method, List<NameValuePair> params) {
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;
            // Kiểm tra loại method là POST hay GET
            if (method == POST) {
                HttpPost httpPost = new HttpPost(url);
                // Thêm tham số
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                }
                httpResponse = httpClient.execute(httpPost);
            } else if (method == GET) {
                // Gắn tham số vào URL
                if (params != null) {
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);
                httpResponse = httpClient.execute(httpGet);
            }
            httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        } catch (Exception ex) {
            Log.e(TAG, "Error 75: " + ex.toString());
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            response = sb.toString();
            Log.e(TAG, "Response: " + response);
        } catch (Exception e) {
            Log.e(TAG, "Buffer Error 89: " + e.toString());
        }
        return response;
    }
}
