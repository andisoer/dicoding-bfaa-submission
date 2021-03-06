package com.soerjdev.consumerapp.view.fragment.detail_profile.following

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.soerjdev.consumerapp.data.repository.DetailProfileRepository

class FollowingFragmentViewModel(application: Application): AndroidViewModel(application){

    private val detailProfileRepository: DetailProfileRepository = DetailProfileRepository()

    fun getFollowingUser(username: String) = detailProfileRepository.getFollowing(username)

}