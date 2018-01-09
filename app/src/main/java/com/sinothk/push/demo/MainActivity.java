package com.sinothk.push.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sinothk.push.OPushManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String mRegId = OPushManager.Cache.getRegId();
//        if (mRegId == null) {
//        }

//        OPushManager.newPush(this, APP_ID, APP_KEY, true);
//        OPushManager.delPush(getApplicationContext());

//        OPushManager.setAlias(getApplicationContext(), "381518188@qq.com");
//        OPushManager.unsetAlias(getApplicationContext(), "381518188@qq.com");
        // 设置推送
//        OPushManager.setUserAccount(getApplicationContext(), "381518188");
//        OPushManager.unsetUserAccount(getApplicationContext(), "381518188");

//        OPushManager.subscribe(getApplicationContext(), "宠物ID");
//        OPushManager.unsubscribe(getApplicationContext(), "宠物ID");


    }
}
