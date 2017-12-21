package com.xy.simplenotes.activity;

import android.os.Bundle;

import com.xy.simplenotes.R;

public class MainActivity extends ActivityBase {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
