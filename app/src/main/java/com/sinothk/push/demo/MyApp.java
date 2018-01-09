package com.sinothk.push.demo;

import android.app.Application;

import com.sinothk.push.OPushManager;

/**
 * Created by 梁玉涛 on 2018/1/9.
 */

public class MyApp extends Application {

    public static final String APP_ID = "2882303761517692269";
    public static final String APP_KEY = "5791769242269";

    @Override
    public void onCreate() {
        super.onCreate();

        OPushManager.newPush(this, APP_ID, APP_KEY, true);
    }
}
