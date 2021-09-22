package com.example.nasaapi.ui.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("caption")
    val caption: String?,
    @SerializedName("large")
    val large: String?,
    @SerializedName("original")
    val original: String?,
    @SerializedName("thumb")
    val thumb: String?
)