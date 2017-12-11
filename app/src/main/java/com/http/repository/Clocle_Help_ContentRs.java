package com.http.repository;

import com.bean.ClocleResult;
import com.bean.Clocle_help;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 请求帮助内容的retrofit请求接口
 * Created by Administrator on 2017/11/17.
 */

public interface Clocle_Help_ContentRs {
    @GET("getClocleHelpContent" )
    Call<ClocleResult<Clocle_help>> getContent(
            @Query("page") int currentPage
    );
}
