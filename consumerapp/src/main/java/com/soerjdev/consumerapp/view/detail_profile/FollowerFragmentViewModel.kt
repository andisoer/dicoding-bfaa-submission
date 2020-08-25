package com.soerjdev.consumerapp.view.detail_profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.soerjdev.consumerapp.data.repository.DetailProfileRepository

class FollowerFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val detailProfileRepository : DetailProfileRepository = DetailProfileRepository()

    fun getFollowerUser(username: String) = detailProfileRepository.getFollower(username = username)

}