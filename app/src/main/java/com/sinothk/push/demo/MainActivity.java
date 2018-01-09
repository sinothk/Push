package com.sinothk.push.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sinothk.push.PushManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PushManager.init();
    }
}
