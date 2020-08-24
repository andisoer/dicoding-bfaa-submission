package com.soerjdev.consumerapp.data.repository

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soerjdev.consumerapp.data.model.FavoriteModel
import com.soerjdev.consumerapp.data.model.SearchResponse
import com.soerjdev.consumerapp.data.model.Status
import com.soerjdev.consumerapp.utils.FAVORITE_URI
import com.soerjdev.consumerapp.utils.toContentValues
import com.soerjdev.dicodingbfaasubmission.data.api.ApiEndPoints
import com.soerjdev.consumerapp.data.api.apiRequest
import com.soerjdev.consumerapp.data.api.httpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProfileRepository() {

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

    fun insertFavorite(user: FavoriteModel, context: Context){
        context.contentResolver.insert(FAVORITE_URI.toUri(), user.toContentValues())
    }

    fun deleteFavorite(user_id: Int, context: Context){

        val uri = "$FAVORITE_URI/$user_id".toUri()

        context.contentResolver.delete(uri, null, null)
    }


}