package com.example.doan_android_2020._general.interfaces;

import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/**
 * @author Hoàng Phi Đại
 * @version 0.1
 * @desc Hue city, VietNam
 * @link ff3105.github.io
 * @created 02/03/2020 16:12h
 * @functionDescription tạo snack bar thông báo kêt quả
 */


public class CustomSnackBar {
    View view;
    String message;

    public CustomSnackBar(View view, String message) {
        this.view = view;
        this.message = message;
        show();
    }

    public void show() {
        final Snackbar snackbar = Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_INDEFINITE);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        }).show();
    }
}
