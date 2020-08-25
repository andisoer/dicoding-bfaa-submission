package com.soerjdev.consumerapp.view.detail_profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.soerjdev.consumerapp.data.model.FavoriteModel
import com.soerjdev.consumerapp.data.repository.DetailProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailProfileFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val detailProfileRepository: DetailProfileRepository = DetailProfileRepository()

    fun insertFavoriteUsers(user: FavoriteModel, context: Context) = viewModelScope.launch(Dispatchers.IO){
        detailProfileRepository.insertFavorite(user = user, context = context)
    }

    fun deleteFavoriteUsers(user_id: Int, context: Context) = viewModelScope.launch(Dispatchers.IO){
        detailProfileRepository.deleteFavorite(user_id = user_id, context = context)
    }

}