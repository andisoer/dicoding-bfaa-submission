package com.soerjdev.consumerapp

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FavoriteRepository() {

    fun getFavoriteUsersList(context: Context): LiveData<List<FavoriteModel>>{
        val liveData = MutableLiveData<List<FavoriteModel>>()

        val cursor = context.contentResolver.query(FAVORITE_URI.toUri(), null, null, null, null)
        cursor?.let {

            liveData.postValue(it.toListFavoriteModel())

            cursor.close()
        }

        return liveData
    }

}