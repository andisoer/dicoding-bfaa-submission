package com.soerjdev.dicodingbfaasubmission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFollowerBinding>(inflater, R.layout.fragment_follower, container, false)

        return binding.root
    }

    companion object {

    }
}