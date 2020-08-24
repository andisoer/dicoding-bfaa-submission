package com.soerjdev.consumerapp.model


import com.google.gson.annotations.SerializedName
import com.soerjdev.consumerapp.model.SearchResponse

data class UserSearch(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<SearchResponse>,
    @SerializedName("total_count")
    val totalCount: Int
)