package com.example.lab5_pm.model

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("amiibo" ) var amiibo : List<Amiibo> = listOf()

)

