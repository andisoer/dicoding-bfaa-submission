package com.soerjdev.dicodingbfaasubmission.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soerjdev.dicodingbfaasubmission.data.api.ApiEndPoints
import com.soerjdev.dicodingbfaasubmission.data.api.apiRequest
import com.soerjdev.dicodingbfaasubmission.data.api.httpClient
import com.soerjdev.dicodingbfaasubmission.data.model.Status
import com.soerjdev.dicodingbfaasubmission.data.model.UserSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository {

    var liveData = MutableLiveData<Status<UserSearch>>()
    var queryData = MutableLiveData<String>()

    fun getUserBySearch(query: String): LiveData<Status<UserSearch>> {

        if (query.isNotBlank()){
            queryData.value = query

            liveData = MutableLiveData<Status<UserSearch>>()

            val httpClient = httpClient()
            val apiRequest = apiRequest<ApiEndPoints>(httpClient)

            apiRequest.getSearchUser(query = queryData.value.toString()).enqueue(object : Callback<UserSearch> {
                override fun onResponse(call: Call<UserSearch>, response: Response<UserSearch>) {
                    when {
                        response.isSuccessful -> {
                            when {
                                response.body() != null -> {
                                    liveData.postValue(Status.success(data = response.body()))
                                }
                                else -> {
                                    liveData.postValue(Status.error(message = "Empty data", data = null))
                                }
                            }
                        } else -> {
                            liveData.postValue(Status.error(message = "Failed to search user", data = null))
                        }
                    }
                }

                override fun onFailure(call: Call<UserSearch>, t: Throwable) {
                    liveData.postValue(Status.error(message = "Error, ${t.message}", data = null))
                }
            })

        }
        return liveData
    }

    companion object {
        var TAG = SearchRepository::class.java.simpleName
    }
}