package com.soerjdev.dicodingbfaasubmission

import com.soerjdev.dicodingbfaasubmission.data.UserDetail
import com.soerjdev.dicodingbfaasubmission.data.UserSearch
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
    ): Call<UserDetail>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<UserDetail>
}