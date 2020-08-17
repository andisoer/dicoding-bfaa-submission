package com.soerjdev.dicodingbfaasubmission.data.repository

import androidx.lifecycle.LiveData
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteDao
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val favoriteUsersList: LiveData<List<FavoriteModel>> = favoriteDao.selectAllUser()

}