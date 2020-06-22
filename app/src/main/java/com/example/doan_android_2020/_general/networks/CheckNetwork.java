package com.example.doan_android_2020._general.networks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Hoàng Phi Đại
 * @version 0.1
 * @desc Hue city, VietNam
 * @link ff3105.github.io
 * @created 02/03/2020 16:12h
 * @functionDescription kiểm tra kết nối mạng
 */
public class CheckNetwork {
    public boolean checkInternetConnection(Context context) {
        ConnectivityManager connManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        assert connManager != null;
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        if (!networkInfo.isConnected()) {
            return false;
        }
        if (!networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }
}
