package com.example.nasaapi.ui.model


import com.google.gson.annotations.SerializedName

data class FBI(
    @SerializedName("items")
    val items: List<Item>?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total")
    val total: Int?
)