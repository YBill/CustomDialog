package com.bill.customdialog;

import android.app.Application;

/**
 * Created by Bill on 2017/4/8.
 */

public class MyAppLication extends Application {

    public static MyAppLication appLication;

    @Override
    public void onCreate() {
        super.onCreate();
        appLication = this;
    }
}

