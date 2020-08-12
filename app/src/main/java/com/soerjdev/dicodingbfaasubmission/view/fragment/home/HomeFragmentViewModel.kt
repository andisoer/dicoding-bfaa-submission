package com.soerjdev.dicodingbfaasubmission.view.fragment.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.soerjdev.dicodingbfaasubmission.SearchRepository

class HomeFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val searchRepository: SearchRepository = SearchRepository()

    fun getUserBySearch(query: String) = searchRepository.getUserBySearch(query = query)

}