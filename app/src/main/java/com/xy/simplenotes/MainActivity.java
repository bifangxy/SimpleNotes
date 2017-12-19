package com.xy.simplenotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EMClient.getInstance().login("user_509", "xiaopu123", new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
            }
        });

        JPushInterface.setAliasAndTags(getApplicationContext(),
                "xieying",
                null,
                new TagAliasCallback() {
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        Log.d("-----", "---" + i + "---" + s);
                    }
                });
    }
}
