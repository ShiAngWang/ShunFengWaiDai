package com.example.shunfengwaidai.untils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by lanjianzhong on 2018/4/13.
 */

public class MyRestClient {
    //private static final String BASE_URL="http://192.168.1.108:8080";
    private static final String BASE_URL="192.168.1.110:8080";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);

    }
    private static String getAbsoluteUrl(String relativeUrl) {
        String url= BASE_URL + relativeUrl;
        //url = url.replaceAll("&", "%26");
        //url = url.replaceAll(" ", "%20");
        System.out.println(url);
        return url;
    }
}
