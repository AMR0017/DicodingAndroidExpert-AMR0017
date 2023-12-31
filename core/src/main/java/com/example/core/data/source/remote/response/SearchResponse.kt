package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @field:SerializedName("error")
    val error: Boolean? = false,

    @field:SerializedName("message")
    val message: String? = "",

    @field:SerializedName("items")
    val items: List<GitDetailResponse>
)