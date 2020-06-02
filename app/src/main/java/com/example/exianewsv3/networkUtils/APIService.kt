package com.example.exianewsv3.networkUtils

import com.example.exianewsv3.models.ResponseNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("top-headlines")
    fun getArticles(
        @Query("country") id: String?,
        @Query("apiKey") apiKey: String?
    ): Call<ResponseNews?>?
}