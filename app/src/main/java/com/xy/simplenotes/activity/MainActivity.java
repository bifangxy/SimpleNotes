package com.xy.simplenotes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xy.simplenotes.R;
import com.xy.simplenotes.http.HttpUtil;
import com.xy.simplenotes.http.HttpCallBack;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpUtil.getInstance().requestGetNoHeader("https://api.heweather.com/v5/now?city=深圳&key=d89694ee59f14e51a1aa2143725e6f07", new HttpCallBack<String>() {

            @Override
            public void onSuccess(String data) {
                Log.d(LOG_TAG, data);
            }

            @Override
            public void onFail(String msg) {
                Log.d(LOG_TAG, "msg = " + msg);
            }
        });

    }


}
