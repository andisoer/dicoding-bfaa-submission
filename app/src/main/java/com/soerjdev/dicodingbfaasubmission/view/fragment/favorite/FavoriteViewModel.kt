package com.soerjdev.dicodingbfaasubmission.view.fragment.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.soerjdev.dicodingbfaasubmission.data.database.AppLocalDatabase
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel
import com.soerjdev.dicodingbfaasubmission.data.repository.FavoriteRepository

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private val favoriteDao = AppLocalDatabase.getDatabase(application).favoriteDao()

    private val repository: FavoriteRepository = FavoriteRepository(favoriteDao)

    val favoriteUsersList: LiveData<List<FavoriteModel>> = repository.favoriteUsersList


}