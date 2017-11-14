package com.android.qzs.entertainmenttop.retrofit;


import android.util.SparseArray;

import com.android.qzs.entertainmenttop.utils.LogUtil;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by QZS
 */
public class ApiClient {
    public static Retrofit mRetrofit_new, mRetrofit_photo;
    public static final String BASEURL_NEWSTOP = "https://way.jd.com/jisuapi/";
    public static final String BASEURL_PHOTO = "http://image.baidu.com/channel/";

    /*
    为了动态改变BaseUrl
     */
    public static Retrofit retrofit(@HostType.HostTypeChecker int hostType) {
        if (hostType == HostType.New_Top) {
            if (mRetrofit_new == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                OkHttpClient okHttpClient = builder.build();
                mRetrofit_new = new Retrofit.Builder()
                        .baseUrl(getHost(hostType))
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(okHttpClient)
                        .build();
            }

        } else if (hostType == HostType.GIRL_PHOTO) {
            if (mRetrofit_photo == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                OkHttpClient okHttpClient = builder.build();
                mRetrofit_photo = new Retrofit.Builder()
                        .baseUrl(getHost(hostType))
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
        }
        return hostType == HostType.New_Top ? mRetrofit_new : mRetrofit_photo;
    }


    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.New_Top:
                host = BASEURL_NEWSTOP;
                break;
            case HostType.GIRL_PHOTO:
                host = BASEURL_PHOTO;
                break;

            default:
                host = "";
                break;
        }
        return host;
    }

}
