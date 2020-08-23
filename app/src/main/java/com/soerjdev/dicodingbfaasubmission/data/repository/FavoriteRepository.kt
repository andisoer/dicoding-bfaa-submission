package com.soerjdev.dicodingbfaasubmission.data.repository

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteDao
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel
import com.soerjdev.dicodingbfaasubmission.utils.FAVORITE_URI
import com.soerjdev.dicodingbfaasubmission.utils.toListFavoriteModel

class FavoriteRepository(private val favoriteDao: FavoriteDao) {


//    val favoriteUsersList: LiveData<List<FavoriteModel>> = favoriteDao.selectAllUser()
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