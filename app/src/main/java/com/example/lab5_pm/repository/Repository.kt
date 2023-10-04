package com.example.lab5_pm.repository

import com.example.lab5_pm.Api.RetrofitInstance
import com.example.lab5_pm.model.Amiibo
import com.example.lab5_pm.model.Gameseries
import com.example.lab5_pm.model.Post
import com.example.lab5_pm.model.Post2
import retrofit2.Response

class Repository {

    suspend fun getPost(name:String): Response<Post>{
        return RetrofitInstance.api.getPost(name)
    }

    suspend fun getGameseries(): Response<Post2>{
        return RetrofitInstance.api.getGameseries()
    }
}