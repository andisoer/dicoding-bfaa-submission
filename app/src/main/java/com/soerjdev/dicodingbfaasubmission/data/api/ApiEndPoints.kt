package com.soerjdev.dicodingbfaasubmission.data.api

import com.soerjdev.dicodingbfaasubmission.data.model.SearchResponse
import com.soerjdev.dicodingbfaasubmission.data.model.UserDetail
import com.soerjdev.dicodingbfaasubmission.data.model.UserSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoints {

    @GET("search/users")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserSearch>

    @GET("users/{username}")
    fun getSearchByUserName(
        @Path("username") username: String
    ): Call<UserDetail>

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<List<SearchResponse>>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<List<SearchResponse>>
}