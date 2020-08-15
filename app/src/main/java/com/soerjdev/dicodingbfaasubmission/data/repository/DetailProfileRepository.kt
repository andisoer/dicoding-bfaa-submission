package com.soerjdev.dicodingbfaasubmission.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soerjdev.dicodingbfaasubmission.data.UserDetail
import com.soerjdev.dicodingbfaasubmission.data.model.Status
import com.soerjdev.dicodingbfaasubmission.data.api.ApiEndPoints
import com.soerjdev.dicodingbfaasubmission.data.api.apiRequest
import com.soerjdev.dicodingbfaasubmission.data.api.httpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProfileRepository {

    fun getByUsername(username: String): LiveData<Status<UserDetail>> {

        val detailLiveData = MutableLiveData<Status<UserDetail>>()

        val httpClient =
            httpClient()
        val apiRequest =
            apiRequest<ApiEndPoints>(
                httpClient
            )

        apiRequest.getSearchByUserName(username = username).enqueue(object: Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                when {
                    response.isSuccessful -> {
                        when {
                            response.body() != null -> {
                                detailLiveData.postValue(
                                    Status.success(
                                        data = response.body()
                                    )
                                )
                            } else -> {
                                detailLiveData.postValue(
                                    Status.error(
                                        message = "Empty data",
                                        data = null
                                    )
                                )
                            }
                        }
                    } else -> {
                        detailLiveData.postValue(
                            Status.error(
                                message = "Failed to search user",
                                data = null
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                detailLiveData.postValue(
                    Status.error(
                        message = "Error, ${t.message}",
                        data = null
                    )
                )
            }
        })

        return detailLiveData
    }

}