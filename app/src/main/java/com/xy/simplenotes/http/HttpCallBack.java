package com.xy.simplenotes.http;

/**
 * Created by Administrator on 2017/12/20.
 */

public abstract class HttpCallBack<T> {
    public abstract void onSuccess(T data);

    public abstract void onFail(String msg);
}
