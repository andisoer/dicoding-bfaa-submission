package com.soerjdev.dicodingbfaasubmission

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soerjdev.dicodingbfaasubmission.data.UserSearch
import com.soerjdev.dicodingbfaasubmission.data.apiRequest
import com.soerjdev.dicodingbfaasubmission.data.httpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository {

    val liveData = MutableLiveData<Status<UserSearch>>()
    var queryData = MutableLiveData<String>()

    fun getUserBySearch(query: String): LiveData<Status<UserSearch>> {

        Log.d(TAG, "getUserBySearch: query input $query")
        Log.d(TAG, "getUserBySearch: query repository : ${queryData.value}")

        queryData.postValue(query)

        Log.d(TAG, "getUserBySearch: before check")
        if (liveData.value != null){
            if (liveData.value?.status == Status.Type.SUCCESS) return liveData

            Log.d(TAG, "getUserBySearch: after check")

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

        }else {
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
        }
        return liveData
    }

    companion object {
        var TAG = SearchRepository::class.java.simpleName
    }
}