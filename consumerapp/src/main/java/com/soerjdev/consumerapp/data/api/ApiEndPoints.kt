package com.soerjdev.dicodingbfaasubmission.data.api

import com.soerjdev.consumerapp.data.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoints {

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<List<SearchResponse>>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<List<SearchResponse>>
}