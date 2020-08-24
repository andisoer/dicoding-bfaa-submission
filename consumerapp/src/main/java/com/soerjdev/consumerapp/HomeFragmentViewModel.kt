package com.soerjdev.consumerapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class HomeFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val repository: FavoriteRepository = FavoriteRepository()

    fun favoriteUsersList(context: Context): LiveData<List<FavoriteModel>> = repository.getFavoriteUsersList(context = context)
}