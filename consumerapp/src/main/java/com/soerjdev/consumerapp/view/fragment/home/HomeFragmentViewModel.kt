package com.soerjdev.consumerapp.view.fragment.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.soerjdev.consumerapp.data.model.FavoriteModel
import com.soerjdev.consumerapp.data.repository.FavoriteRepository

class HomeFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val repository: FavoriteRepository =
        FavoriteRepository()

    fun favoriteUsersList(context: Context): LiveData<List<FavoriteModel>> = repository.getFavoriteUsersList(context = context)
}