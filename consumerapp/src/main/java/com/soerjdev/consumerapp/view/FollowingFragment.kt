package com.soerjdev.consumerapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soerjdev.consumerapp.R

class FollowingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    companion object {
        fun newInstance(username: String): FollowingFragment {
            val bundle = Bundle().apply {
                putString("username", username)
            }

            return FollowingFragment().apply {
                arguments = bundle
            }
        }
    }
}