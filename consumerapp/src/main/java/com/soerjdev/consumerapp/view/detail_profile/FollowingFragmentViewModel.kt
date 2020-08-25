package com.soerjdev.consumerapp.view.detail_profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.soerjdev.consumerapp.data.repository.DetailProfileRepository

class FollowingFragmentViewModel(application: Application): AndroidViewModel(application){

    private val detailProfileRepository: DetailProfileRepository = DetailProfileRepository()

    fun getFollowingUser(username: String) = detailProfileRepository.getFollowing(username)

}