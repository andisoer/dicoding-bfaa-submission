package com.soerjdev.dicodingbfaasubmission

import com.soerjdev.dicodingbfaasubmission.data.UserSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoints {

    @GET("search/users")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserSearch>

}