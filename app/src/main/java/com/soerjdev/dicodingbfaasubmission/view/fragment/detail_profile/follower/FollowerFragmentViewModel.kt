package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile.follower

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.soerjdev.dicodingbfaasubmission.data.repository.DetailProfileRepository

class FollowerFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val detailProfileRepository : DetailProfileRepository = DetailProfileRepository()

    fun getFollowerUser(username: String) = detailProfileRepository.getFollower(username = username)

}