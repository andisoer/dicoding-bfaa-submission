package com.soerjdev.dicodingbfaasubmission.view.fragment.favorite

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.soerjdev.dicodingbfaasubmission.data.database.AppLocalDatabase
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel
import com.soerjdev.dicodingbfaasubmission.data.repository.FavoriteRepository

class FavoriteFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val favoriteDao = AppLocalDatabase.getDatabase(application).favoriteDao()

    private val repository: FavoriteRepository = FavoriteRepository(favoriteDao = favoriteDao)

    fun favoriteUsersList(context: Context): LiveData<List<FavoriteModel>> = repository.getFavoriteUsersList(context = context)
}