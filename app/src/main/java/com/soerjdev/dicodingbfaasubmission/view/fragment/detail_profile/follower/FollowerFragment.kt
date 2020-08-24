package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile.follower

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.data.model.SearchResponse
import com.soerjdev.dicodingbfaasubmission.data.adapter.UserSearchAdapter
import com.soerjdev.dicodingbfaasubmission.data.model.Status
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentFollowerBinding
import com.soerjdev.dicodingbfaasubmission.utils.hide
import com.soerjdev.dicodingbfaasubmission.utils.show
import com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile.following.FollowingFragment

class FollowerFragment : Fragment(), UserSearchAdapter.Listener {

    private lateinit var followerFragmentViewModel: FollowerFragmentViewModel
    private lateinit var binding: FragmentFollowerBinding
    private lateinit var adapter: UserSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentFollowerBinding>(inflater,
            R.layout.fragment_follower, container, false)

        adapter = UserSearchAdapter(requireContext(), this@FollowerFragment)

        binding.apply {
            rvUserFollower.layoutManager = LinearLayoutManager(context)
            rvUserFollower.adapter = adapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followerFragmentViewModel = ViewModelProvider(this).get(FollowerFragmentViewModel::class.java)

        arguments?.apply {

            getString("username")?.let {
                observeDataUserFollower(username = it)
            }

        }
    }

    private fun observeDataUserFollower(username: String) {
        binding.apply {
            pbLoadFollower.show()
            layoutFailedFollower.hide()
            layoutEmptyDataFollower.hide()
        }
        followerFragmentViewModel.getFollowerUser(username = username).observe(
            viewLifecycleOwner, Observer { status ->
                when(status.status){
                    Status.Type.SUCCESS -> {
                        status.data.apply {
                            if (this!!.isNotEmpty()){
                                binding.pbLoadFollower.hide()
                                adapter.setUserSearchData(this)
                            }else {
                                binding.apply {
                                    pbLoadFollower.hide()
                                    layoutEmptyDataFollower.show()
                                }
                            }
                        }
                    }
                    Status.Type.FAILED -> {
                        binding.apply {
                            pbLoadFollower.hide()
                            layoutFailedFollower.show()
                        }
                    }
                }
            }
        )
    }

    companion object {
        var TAG = FollowerFragment::class.java.name
        
        fun newInstance(username: String): FollowerFragment {
            val bundle = Bundle().apply {
                putString("username", username)
            }

            return FollowerFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onUserClickListenre(view: View, data: SearchResponse) {
        Log.d(TAG, "onUserClickListenre: $data")
    }
}