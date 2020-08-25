package com.soerjdev.consumerapp.view

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
import com.soerjdev.consumerapp.R
import com.soerjdev.consumerapp.data.adapter.UserSearchAdapter
import com.soerjdev.consumerapp.data.model.SearchResponse
import com.soerjdev.consumerapp.data.model.Status
import com.soerjdev.consumerapp.databinding.FragmentFollowingBinding
import com.soerjdev.consumerapp.utils.hide
import com.soerjdev.consumerapp.utils.show

class FollowingFragment : Fragment(), UserSearchAdapter.Listener {

    private lateinit var followingFragmentViewModel: FollowingFragmentViewModel
    private lateinit var binding: FragmentFollowingBinding
    private lateinit var adapter: UserSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentFollowingBinding>(inflater,
            R.layout.fragment_following, container, false)

        adapter = UserSearchAdapter(requireContext(), this@FollowingFragment)

        binding.apply {
            rvUserFollowing.layoutManager = LinearLayoutManager(context)
            rvUserFollowing.adapter = adapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followingFragmentViewModel = ViewModelProvider(this).get(FollowingFragmentViewModel::class.java)

        arguments?.apply {
            getString("username")?.let {
                observerDataUserFollowing(username = it)
            }
        }
    }

    private fun observerDataUserFollowing(username: String) {
        binding.apply {
            pbLoadFollowing.show()
            layoutEmptyDataFollowing.hide()
            layoutFailedFollowing.hide()
        }
        followingFragmentViewModel.getFollowingUser(username = username).observe(
            viewLifecycleOwner, Observer { status ->
                when(status.status){
                    Status.Type.SUCCESS -> {
                        status.data.apply {
                            if (this!!.isNotEmpty()){
                                binding.apply {
                                    pbLoadFollowing.hide()
                                }
                                adapter.setUserSearchData(this)
                            }else {
                                binding.apply {
                                    pbLoadFollowing.hide()
                                    layoutEmptyDataFollowing.show()
                                }
                            }
                        }
                    }
                    Status.Type.FAILED -> {
                        binding.apply {
                            pbLoadFollowing.hide()
                            layoutFailedFollowing.show()
                        }
                    }
                }
            }
        )
    }

    companion object {
        var TAG = FollowingFragment::class.java.simpleName

        fun newInstance(username: String): FollowingFragment {
            val bundle = Bundle().apply {
                putString("username", username)
            }

            return FollowingFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onUserClickListenre(view: View, data: SearchResponse) {
        Log.d(TAG, "onUserClickListenre: $data")
    }
}