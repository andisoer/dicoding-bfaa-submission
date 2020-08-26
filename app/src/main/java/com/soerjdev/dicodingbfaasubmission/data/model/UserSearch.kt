package com.soerjdev.dicodingbfaasubmission.data.model


import com.google.gson.annotations.SerializedName

data class UserSearch(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<SearchResponse>,
    @SerializedName("total_count")
    val totalCount: Int
)