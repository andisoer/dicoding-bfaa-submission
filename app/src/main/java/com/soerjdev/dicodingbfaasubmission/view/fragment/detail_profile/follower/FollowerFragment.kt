package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile.follower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.data.adapter.UserSearchAdapter
import com.soerjdev.dicodingbfaasubmission.data.model.SearchResponse
import com.soerjdev.dicodingbfaasubmission.data.model.Status
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentFollowerBinding
import com.soerjdev.dicodingbfaasubmission.utils.hide
import com.soerjdev.dicodingbfaasubmission.utils.show

class FollowerFragment : Fragment(), UserSearchAdapter.Listener {

    private lateinit var followerFragmentViewModel: FollowerFragmentViewModel
    private lateinit var binding: FragmentFollowerBinding
    private lateinit var adapter: UserSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_follower, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        adapter = UserSearchAdapter(requireContext(), this@FollowerFragment)

        binding.apply {
            rvUserFollower.layoutManager = LinearLayoutManager(context)
            rvUserFollower.adapter = adapter
        }

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