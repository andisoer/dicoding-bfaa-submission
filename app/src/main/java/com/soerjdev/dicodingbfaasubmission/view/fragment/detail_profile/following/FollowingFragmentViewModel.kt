package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile.following

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.soerjdev.dicodingbfaasubmission.data.database.AppLocalDatabase
import com.soerjdev.dicodingbfaasubmission.data.repository.DetailProfileRepository

class FollowingFragmentViewModel(application: Application): AndroidViewModel(application){

    private val favoriteDao = AppLocalDatabase.getDatabase(application).favoriteDao()

    private val detailProfileRepository: DetailProfileRepository = DetailProfileRepository(favoriteDao = favoriteDao)

    fun getFollowingUser(username: String) = detailProfileRepository.getFollowing(username)

}