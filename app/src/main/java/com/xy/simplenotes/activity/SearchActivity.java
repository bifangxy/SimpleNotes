package com.xy.simplenotes.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.Toolbar;

import com.xy.simplenotes.R;

/**
 * Created by Xieying on 2017/12/21.
 */

public class SearchActivity extends ActivityBase {
    private static final String LOG_TAG = SearchActivity.class.getSimpleName();

    private android.support.v7.widget.Toolbar mToolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("这里是Title");
        mToolbar.setSubtitle("这里是子标题");
        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);

        //CollapsingToolbarLayout



    }
}
