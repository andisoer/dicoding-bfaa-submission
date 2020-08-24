package com.soerjdev.consumerapp.data.repository

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soerjdev.consumerapp.data.model.FavoriteModel
import com.soerjdev.consumerapp.utils.FAVORITE_URI
import com.soerjdev.consumerapp.utils.toListFavoriteModel

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