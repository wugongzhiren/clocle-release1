package com.http.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/6/21.
 */

public interface DynamicManage  {
    //动态发表,用户ID，内容，图片路径
    @GET("dynamic_publish")
    Call<String> publish(@Query("userID") long userID,@Query("content") String content,@Query("imageUrls")List<String> imageUrls);
}
