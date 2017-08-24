package com.android.qzs.entertainmenttop.retrofit;



import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_news.model.NewsModel;
import com.android.qzs.entertainmenttop.mvp.mvp_photo.model.PhotoModel;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by QZS
 */

public interface ApiService {

    //baseUrl
    String API_SERVER_URL = "https://way.jd.com/jisuapi/";

    //加载天气
//    @GET("weather")
//    Observable<WeatherModel> loadDataByRetrofitRxjava(@Query("citypinyin") String cityId);

//    @FormUrlEncoded
//    @POST("user/login")
//    Observable<WeatherModel> getlogin(@Field("oper_name") String page, @Field("oper_pwds") String rows);
//头条
    @GET("get")
Observable<TopModel> LoadTopData_RxJava(@Query("channel") String type, @Query("num") String num, @Query("start") int startposition, @Query("appkey") String key);
//新闻
    @GET("get")
    Observable<NewsModel> LoadNewData_RxJava(@Query("channel") String type, @Query("num") String num, @Query("start") int startposition, @Query("appkey") String key);

    //图片
    @GET("listjson")
    Observable<PhotoModel> LoadPhoto_RxJava(@Query("pn") int page, @Query("rn") int number, @Query("tag1") String type1,@Query("tag2") String type2);
}
