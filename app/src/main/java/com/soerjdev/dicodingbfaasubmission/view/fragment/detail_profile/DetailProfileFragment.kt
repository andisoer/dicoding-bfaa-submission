package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile.DetailProfileFragmentArgs
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.data.model.UserDetail
import com.soerjdev.dicodingbfaasubmission.data.adapter.ProfileViewPagerAdapter
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel
import com.soerjdev.dicodingbfaasubmission.data.model.Status
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentDetailProfileBinding

class DetailProfileFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var detailProfileFragmentViewModel: DetailProfileFragmentViewModel
    private lateinit var binding: FragmentDetailProfileBinding

    private lateinit var profileViewPagerAdapter : ProfileViewPagerAdapter

    private val tabTitle = arrayOf("Following", "Follower")

    private var favoriteUserModel: FavoriteModel? = null
    private var userDetailModel: UserDetail? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentDetailProfileBinding>(inflater,
            R.layout.fragment_detail_profile, container, false)

        detailProfileFragmentViewModel = ViewModelProvider(this).get(DetailProfileFragmentViewModel::class.java)

        arguments?.let { bundle ->
            val username = DetailProfileFragmentArgs.fromBundle(bundle).username
            val data = DetailProfileFragmentArgs.fromBundle(bundle).userDetail

            Log.d(TAG, "onViewCreated: $username")
            Log.d(TAG, "onViewCreated: $data")

            profileViewPagerAdapter = ProfileViewPagerAdapter(fragment = this, username = username)

            if (data == null){
                observeDataDetailProfile(username = username)
            } else {
                val tempFavoriteModel = Gson().toJson(data)
                val favoriteModel = Gson().fromJson(tempFavoriteModel, FavoriteModel::class.java)
                favoriteUserModel = favoriteModel
                setData(userDetail = data)
                isUsersFavorite()
            }
        }

        binding.apply {
            tbFragmentDetailProfile.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            tbFragmentDetailProfile.setOnMenuItemClickListener(this@DetailProfileFragment)

            viewPagerDetailProfile.adapter = profileViewPagerAdapter

            fabFavoriteDetailProfile.setOnClickListener {
                when {
                    favoriteUserModel != null -> {
                        removeFromUsersFavorite()
                    }
                    else -> {
                        addToUsersFavorite()
                    }
                }
            }

            TabLayoutMediator(tabLayoutDetailProfile, viewPagerDetailProfile,
                TabLayoutMediator.TabConfigurationStrategy {
                    tab, position -> tab.text = tabTitle[position]
            }).attach()

        }

        return binding.root
    }

    private fun removeFromUsersFavorite() {
        when {
            favoriteUserModel != null -> {
                detailProfileFragmentViewModel.deleteFavoriteUsers(favoriteUserModel!!.id)
                favoriteUserModel = null
                isUsersFavorite()
            }
        }
    }

    private fun addToUsersFavorite() {
        when {
            userDetailModel != null -> {
                val userString = Gson().toJson(userDetailModel)
                favoriteUserModel = Gson().fromJson(userString, FavoriteModel::class.java)
                when {
                    favoriteUserModel != null -> {
                        detailProfileFragmentViewModel.insertFavoriteUsers(user = favoriteUserModel!!, context = requireContext())
                        isUsersFavorite()
                    }
                }
            }
        }
    }

    private fun isUsersFavorite() {
        binding.apply {
            when {
                favoriteUserModel != null -> {
                    fabFavoriteDetailProfile.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
                else -> {
                    fabFavoriteDetailProfile.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }
    }

    private fun observeDataDetailProfile(username: String) {
        detailProfileFragmentViewModel.getDetailProfile(username = username).observe(
            viewLifecycleOwner, Observer { status ->
                when(status.status){
                    Status.Type.SUCCESS -> {
                        status.data.apply {
                            setData(this!!)
                        }
                    }
                    Status.Type.FAILED -> {
                        Log.d(TAG, "observeDataDetailProfile: failed")
                    }
                }
            }
        )
    }

    private fun setData(userDetail: UserDetail) {
        binding.apply {
            tvNameDetailProfile.text = userDetail.name
            tvUsernameDetailProfile.text = userDetail.login
            tvLocationDetailProfile.text = userDetail.location
            tvFollowerCountDetailProfile.text = "${userDetail.followers} Follower"
            tvFollowingDetailProfile.text = "${userDetail.following} Following"
        }

        userDetailModel = userDetail
    }

    companion object {
        var TAG = DetailProfileFragment::class.java.simpleName
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
    }
}