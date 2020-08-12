package com.soerjdev.dicodingbfaasubmission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soerjdev.dicodingbfaasubmission.data.UserSearch
import com.soerjdev.dicodingbfaasubmission.data.apiRequest
import com.soerjdev.dicodingbfaasubmission.data.httpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository {

    fun getUserBySearch(query: String): LiveData<Status<UserSearch>> {

        val liveData = MutableLiveData<Status<UserSearch>>()

        val httpClient = httpClient()
        val apiRequest = apiRequest<ApiEndPoints>(httpClient)
        apiRequest.getSearchUser(query).enqueue(object : Callback<UserSearch> {
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
                    }else -> {
                        liveData.postValue(Status.error(message = "Failed to search user", data = null))
                    }
                }
            }

            override fun onFailure(call: Call<UserSearch>, t: Throwable) {
                liveData.postValue(Status.error(message = "Error, ${t.message}", data = null))
            }
        })

        return liveData
    }
}