package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile.follower

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.soerjdev.dicodingbfaasubmission.data.database.AppLocalDatabase
import com.soerjdev.dicodingbfaasubmission.data.repository.DetailProfileRepository

class FollowerFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val favoriteDao = AppLocalDatabase.getDatabase(application).favoriteDao()

    private val detailProfileRepository : DetailProfileRepository = DetailProfileRepository(favoriteDao = favoriteDao)

    fun getFollowerUser(username: String) = detailProfileRepository.getFollower(username = username)

}