package com.example.lab5_pm.model

import com.google.gson.annotations.SerializedName

data class Amiibo (

    @SerializedName("amiiboSeries" ) var amiiboSeries : String?  = null,
    @SerializedName("character"    ) var character    : String?  = null,
    @SerializedName("gameSeries"   ) var gameSeries   : String?  = null,
    @SerializedName("head"         ) var head         : String?  = null,
    @SerializedName("image"        ) var image        : String?  = null,
    @SerializedName("name"         ) var name         : String?  = null,
    @SerializedName("release"      ) var release      : Release? = Release(),
    @SerializedName("tail"         ) var tail         : String?  = null,
    @SerializedName("type"         ) var type         : String?  = null

)