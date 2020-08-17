package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.soerjdev.dicodingbfaasubmission.data.database.AppLocalDatabase
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel
import com.soerjdev.dicodingbfaasubmission.data.repository.DetailProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailProfileFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val favoriteDao = AppLocalDatabase.getDatabase(application).favoriteDao()

    private val detailProfileRepository: DetailProfileRepository = DetailProfileRepository(favoriteDao = favoriteDao)

    fun getDetailProfile(username: String) = detailProfileRepository.getByUsername(username = username)

    fun insertFavoriteUsers(user: FavoriteModel) = viewModelScope.launch(Dispatchers.IO){
        detailProfileRepository.insertFavorite(user = user)
    }

    fun deleteFavoriteUsers(user_id: Int) = viewModelScope.launch(Dispatchers.IO){
        detailProfileRepository.deleteFavorite(user_id = user_id)
    }

    fun selectFavoriteUser(user_id: Int) = viewModelScope.launch(Dispatchers.IO){
        detailProfileRepository.getDetailUser(user_id = user_id)
    }

}