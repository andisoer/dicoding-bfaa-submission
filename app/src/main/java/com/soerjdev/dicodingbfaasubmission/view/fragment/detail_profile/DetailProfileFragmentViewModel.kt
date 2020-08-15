package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.soerjdev.dicodingbfaasubmission.data.repository.DetailProfileRepository

class DetailProfileFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val detailProfileRepository: DetailProfileRepository = DetailProfileRepository()

    fun getDetailProfile(username: String) = detailProfileRepository.getByUsername(username = username)

}