package com.http.repository;

import com.bean.Dynamic;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 动态网络请求Retrofit接口
 * Created by Administrator on 2017/6/21.
 */

public interface DynamicManage  {
    //动态发表,用户ID，内容，图片路径
    @GET("dynamic_publish")
    Call<String> publish(@Query("userID") long userID, @Query("content") String content, @Query("imageUrls")List<String> imageUrls);

    @GET("getDynamic")
    Call<List<Dynamic>> getDynamic(@Query("page") int page);

}
