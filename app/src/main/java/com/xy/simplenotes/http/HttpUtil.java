package com.xy.simplenotes.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.xy.simplenotes.MyApplication;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/20.
 * Volley请求封装类
 */

public class HttpUtil {

    private static HttpUtil mHttpUtil;

    private Gson mGson;

    public static GsonBuilder gb;

    private static final int REQUEST_TIMEOUT_TIME = 60 * 1000;

    public static RequestQueue mRequestQueue;


    private HttpUtil() {
        gb = new GsonBuilder();
        gb.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        mGson = gb.create();
        //这里使用Application创建全局的请求队列
        mRequestQueue = Volley.newRequestQueue(MyApplication.getInstance());
    }

    public static class DateSerializer implements JsonSerializer<Date> {
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }
    }

    public static class DateDeserializer implements JsonDeserializer<Date> {

        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }

    public static HttpUtil getInstance() {
        if (mHttpUtil == null) {
            synchronized (HttpUtil.class) {
                if (mHttpUtil == null) {
                    mHttpUtil = new HttpUtil();
                }
            }
        }
        return mHttpUtil;
    }

    /**
     * 带请求参数get请求
     *
     * @param url          http地址
     * @param param        参数
     * @param httpCallBack 回调
     */
    public <T> void requestPost(String url, final Map<String, String> param, final HttpCallBack<T> httpCallBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (httpCallBack == null) {
                            return;
                        }
                        Log.d("----", response);
                        Type type = getTType(httpCallBack.getClass());
                        HttpResult httpResult = mGson.fromJson(response, HttpResult.class);
                        if (httpResult != null) {
                            if (httpResult.getCode() != 200) {//失败
                                httpCallBack.onFail(httpResult.getMsg());
                            } else {//成功
                                //获取data对应的json字符串
                                String json = mGson.toJson(httpResult.getData());
                                if (type == String.class) {//泛型是String，返回结果json字符串
                                    httpCallBack.onSuccess((T) json);
                                } else {//泛型是实体或者List<>
                                    T t = mGson.fromJson(json, type);
                                    httpCallBack.onSuccess(t);
                                }
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (httpCallBack == null) {
                    return;
                }
                String msg = error.getMessage();
                httpCallBack.onFail(msg);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //请求参数
                return param;
            }

        };
        //设置请求超时和重试
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT_TIME, 1, 1.0f));
        //加入到请求队列
        if (mRequestQueue != null)
            mRequestQueue.add(stringRequest.setTag(url));
    }


    /**
     * 不带header的get请求
     *
     * @param url          http地址
     * @param httpCallBack 回调
     */
    public <T> void requestGetNoHeader(String url, final HttpCallBack<T> httpCallBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (httpCallBack == null) {
                            return;
                        }
                        Type type = getTType(httpCallBack.getClass());
                        HttpResult httpResult = mGson.fromJson(response, HttpResult.class);
                        if (httpResult != null) {
                            if (httpResult.getCode() != 200) {//失败
                                httpCallBack.onFail(httpResult.getMsg());
                            } else {//成功
                                //获取data对应的json字符串
                                String json = mGson.toJson(httpResult.getData());
                                if (type == String.class) {//泛型是String，返回结果json字符串
                                    httpCallBack.onSuccess((T) json);
                                } else {//泛型是实体或者List<>
                                    T t = mGson.fromJson(json, type);
                                    httpCallBack.onSuccess(t);
                                }
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (httpCallBack == null) {
                    return;
                }
                String msg = error.getMessage();
                httpCallBack.onFail(msg);
            }
        });
        //设置请求超时和重试
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT_TIME, 1, 1.0f));
        //加入到请求队列
        if (mRequestQueue != null)
            mRequestQueue.add(stringRequest.setTag(url));
    }


    /**
     * 带header的get请求
     *
     * @param url          http地址
     * @param param        参数
     * @param httpCallBack 回调
     */
    public <T> void requestGetWithHeader(String url, final Map<String, String> param, final HttpCallBack<T> httpCallBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (httpCallBack == null) {
                            return;
                        }
                        Type type = getTType(httpCallBack.getClass());
                        HttpResult httpResult = mGson.fromJson(response, HttpResult.class);
                        if (httpResult != null) {
                            if (httpResult.getCode() != 200) {//失败
                                httpCallBack.onFail(httpResult.getMsg());
                            } else {//成功
                                //获取data对应的json字符串
                                String json = mGson.toJson(httpResult.getData());
                                if (type == String.class) {//泛型是String，返回结果json字符串
                                    httpCallBack.onSuccess((T) json);
                                } else {//泛型是实体或者List<>
                                    T t = mGson.fromJson(json, type);
                                    httpCallBack.onSuccess(t);
                                }
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (httpCallBack == null) {
                    return;
                }
                String msg = error.getMessage();
                httpCallBack.onFail(msg);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return param;
            }

        };
        //设置请求超时和重试
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT_TIME, 1, 1.0f));
        //加入到请求队列
        if (mRequestQueue != null)
            mRequestQueue.add(stringRequest.setTag(url));
    }

    private Type getTType(Class<?> clazz) {
        Type mySuperClassType = clazz.getGenericSuperclass();
        Type[] types = ((ParameterizedType) mySuperClassType).getActualTypeArguments();
        if (types != null && types.length > 0) {
            return types[0];
        }
        return null;
    }
}

