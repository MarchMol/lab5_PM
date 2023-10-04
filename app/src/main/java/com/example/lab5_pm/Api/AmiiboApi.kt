package com.example.lab5_pm.Api

import com.example.lab5_pm.model.Amiibo
import com.example.lab5_pm.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AmiiboApi {

    @GET("amiibo")
    suspend fun getPost(
        @Query("name")name:String
    ): Response<Post>

}