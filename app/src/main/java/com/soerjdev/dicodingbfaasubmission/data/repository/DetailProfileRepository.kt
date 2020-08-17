package com.soerjdev.dicodingbfaasubmission.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soerjdev.dicodingbfaasubmission.data.model.SearchResponse
import com.soerjdev.dicodingbfaasubmission.data.model.UserDetail
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

    fun getFollower(username: String): LiveData<Status<List<SearchResponse>>> {
        val followerLiveData = MutableLiveData<Status<List<SearchResponse>>>()

        val httpClient = httpClient()
        val apiRequest = apiRequest<ApiEndPoints>(httpClient)

        apiRequest.getUserFollowers(username = username).enqueue(object : Callback<List<SearchResponse>> {
            override fun onResponse(
                call: Call<List<SearchResponse>>,
                response: Response<List<SearchResponse>>
            ) {
                when {
                    response.isSuccessful -> {
                        when {
                            response.body() != null -> {
                                followerLiveData.postValue(Status.success(data = response.body()))
                            }
                            else -> {
                                followerLiveData.postValue(Status.error(message = "Empty data", data = null))
                            }
                        }
                    }
                    else -> {
                        followerLiveData.postValue(Status.error(message = "Failed to search user", data = null))
                    }
                }
            }

            override fun onFailure(call: Call<List<SearchResponse>>, t: Throwable) {
                followerLiveData.postValue(Status.error(message = "Error ${t.message}", data = null))
            }
        })

        return followerLiveData
    }

    fun getFollowing(username: String): LiveData<Status<List<SearchResponse>>> {
        val followingLiveData = MutableLiveData<Status<List<SearchResponse>>>()

        val httpClient = httpClient()
        val apiRequest = apiRequest<ApiEndPoints>(httpClient)

        apiRequest.getUserFollowing(username = username).enqueue(object : Callback<List<SearchResponse>> {
            override fun onResponse(
                call: Call<List<SearchResponse>>,
                response: Response<List<SearchResponse>>
            ) {
                when {
                    response.isSuccessful -> {
                        when {
                            response.body() != null -> {
                                followingLiveData.postValue(Status.success(data = response.body()))
                            }
                            else -> {
                                followingLiveData.postValue(Status.error(message = "Empty data", data = null))
                            }
                        }
                    }
                    else -> {
                        followingLiveData.postValue(Status.error(message = "Failed to search user", data = null))
                    }
                }
            }

            override fun onFailure(call: Call<List<SearchResponse>>, t: Throwable) {
                followingLiveData.postValue(Status.error(message = "Error ${t.message}", data = null))
            }
        })

        return followingLiveData
    }
}