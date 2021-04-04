package com.torkuds.androidstudy.net;

import com.torkuds.androidstudy.entity.News;
import com.torkuds.module_common.net.HttpResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("boot/news/addAllNews")
    Observable<HttpResult> addAllNews(@Body List<News> newsList);

    @FormUrlEncoded
    @POST("boot/news/getNewsList")
    Observable<HttpResult<List<News>>> getAllNews(@Field("page")int page);

}
