package me.ziuo.dialer.data.net;

import android.support.annotation.Nullable;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by ziyuo on 2016/1/24.
 */
public class ApiConnection  implements Callable<String>{


    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

    private URL url;
    private String response;

    public ApiConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public static ApiConnection createGET(String url) throws MalformedURLException {
        return  new ApiConnection(url);
    }

    /**
     * 这个方法 去做一个同步的请求
     * 一定不能再主线程中执行该方法
     * @return 请求的响应
     */
    @Nullable
    public String requestSyncCall(){
        connectToApi();
        return response;
    }

    private  void connectToApi(){
        OkHttpClient okHttpClient=this.createClient();
        final Request request=new Request.Builder()
                .url(this.url)
                .addHeader(CONTENT_TYPE_LABEL,CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();
        try {
            this.response=okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient createClient(){
        final OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.setReadTimeout(10*1000, TimeUnit.MILLISECONDS);
        okHttpClient.setConnectTimeout(15*1000,TimeUnit.MILLISECONDS);

        return okHttpClient;
    }

    @Override
    public String call() throws Exception {
        return requestSyncCall();
    }
}
