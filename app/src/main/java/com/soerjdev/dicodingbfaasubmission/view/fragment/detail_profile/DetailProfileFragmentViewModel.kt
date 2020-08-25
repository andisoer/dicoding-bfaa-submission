package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile

import android.app.Application
import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.soerjdev.dicodingbfaasubmission.data.database.AppLocalDatabase
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel
import com.soerjdev.dicodingbfaasubmission.data.model.Status
import com.soerjdev.dicodingbfaasubmission.data.repository.DetailProfileRepository
import com.soerjdev.dicodingbfaasubmission.utils.FAVORITE_URI
import com.soerjdev.dicodingbfaasubmission.utils.toFavoriteModel
import com.soerjdev.dicodingbfaasubmission.utils.toListFavoriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailProfileFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val favoriteDao = AppLocalDatabase.getDatabase(application).favoriteDao()

    private val detailProfileRepository: DetailProfileRepository = DetailProfileRepository(favoriteDao = favoriteDao)

    fun getDetailProfile(username: String) = detailProfileRepository.getByUsername(username = username)

    fun insertFavoriteUsers(user: FavoriteModel, context: Context) = viewModelScope.launch(Dispatchers.IO){
        detailProfileRepository.insertFavorite(user = user, context = context)
    }

    fun deleteFavoriteUsers(user_id: Int, context: Context) = viewModelScope.launch(Dispatchers.IO){
        detailProfileRepository.deleteFavorite(user_id = user_id, context = context)
    }

//    fun selectFavoriteUser(user_id: Int): LiveData<List<FavoriteModel>> = detailProfileRepository.getDetailUser(user_id = user_id)

    fun selectFavoriteUser(user_id: Int, context: Context) = detailProfileRepository.getDetailUser(user_id = user_id, context = context)
}