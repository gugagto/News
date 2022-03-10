package com.example.appnews.network

import com.example.appnews.models.NewsResponse
import com.example.appnews.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")countryCode:String="br",
        @Query("page")pageNumber:Int = 1,
        @Query("apiKey")apiKey:String= Constants.API_KEY
    ):Response<NewsResponse>



    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q")searchQuery:String,
        @Query("language")language:String="pt",
        @Query("page")pageNumber:Int = 1,
        @Query("apiKey")apiKey:String= Constants.API_KEY
    ):Response<NewsResponse>


}