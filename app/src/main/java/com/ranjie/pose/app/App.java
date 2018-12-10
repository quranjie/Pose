package com.ranjie.pose.app;

import android.app.Application;

import com.ranjie.http.RxRetrofitApp;
import com.ranjie.pose.BuildConfig;

/**
 * Created by quzhiyong on 2018/11/9
 */
public class App extends Application {

    private static App application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        RxRetrofitApp.init(this, BuildConfig.DEBUG);
    }

    public static App getApplication() {
        return application;
    }
}
