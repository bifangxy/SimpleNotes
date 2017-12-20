package com.xy.simplenotes;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2017/11/30.
 */

public class MyApplication extends Application {
    private static final String LOG_TAG = MyApplication.class.getSimpleName();

    private static MyApplication mApplication;

    private static Context applicationContext;

    public static RequestQueue mQueue;

    public static MyApplication getInstance() {
        if (mApplication == null) {
            synchronized (MyApplication.class) {
                if (mApplication == null) {
                    mApplication = new MyApplication();
                }
            }
        }
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        applicationContext = this;
        initData();
    }

    private void initData() {
//        initVolley();
        initEase();
        initJPush();
    }

   /* private void initVolley() {
        mQueue = Volley.newRequestQueue(getApplicationContext());
    }*/

    private void initEase() {
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);
        EaseUI.getInstance().init(this, options);
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static RequestQueue getRequestQueue() {
        return mQueue;
    }
}
