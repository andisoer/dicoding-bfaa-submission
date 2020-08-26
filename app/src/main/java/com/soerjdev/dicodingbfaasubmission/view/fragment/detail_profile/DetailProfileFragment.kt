package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.data.adapter.ProfileViewPagerAdapter
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel
import com.soerjdev.dicodingbfaasubmission.data.model.Status
import com.soerjdev.dicodingbfaasubmission.data.model.UserDetail
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentDetailProfileBinding
import com.soerjdev.dicodingbfaasubmission.utils.hide
import com.soerjdev.dicodingbfaasubmission.utils.show

class DetailProfileFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var detailProfileFragmentViewModel: DetailProfileFragmentViewModel
    private lateinit var binding: FragmentDetailProfileBinding

    private lateinit var profileViewPagerAdapter : ProfileViewPagerAdapter

    private val tabTitle = arrayOf("Following", "Follower")

    private var favoriteUserModel: FavoriteModel? = null
    private var userDetailModel: UserDetail? = null

    private var isFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
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
                isFavorite = true
                setData(userDetail = data)
                showDetailContainer()
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
                    isFavorite -> {
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
                Log.d(TAG, "removeFromUsersFavorite: removed")
                detailProfileFragmentViewModel.deleteFavoriteUsers(user_id = favoriteUserModel!!.id, context = requireContext())
                binding.fabFavoriteDetailProfile.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                favoriteUserModel = null
                isFavorite = false
            }
        }
    }

    private fun addToUsersFavorite() {
        when {
            userDetailModel != null -> {
                val userString = Gson().toJson(userDetailModel)
                Log.d(TAG, "addToUsersFavorite: $userString")
                favoriteUserModel = Gson().fromJson(userString, FavoriteModel::class.java)
                Log.d(TAG, "addToUsersFavorite: $favoriteUserModel")
                when {
                    favoriteUserModel != null -> {
                        binding.fabFavoriteDetailProfile.setImageResource(R.drawable.ic_baseline_favorite_24)
                        detailProfileFragmentViewModel.insertFavoriteUsers(user = favoriteUserModel!!, context = requireContext())
                        isFavorite = true
                    }
                }
            }
        }
    }

    private fun observeIsUserFavorite() {
        Log.d(TAG, "observeIsUserFavorite()")
        binding.apply {
            userDetailModel?.let {
                detailProfileFragmentViewModel.selectFavoriteUser(user_id = it.id, context = requireContext()).observe(
                    viewLifecycleOwner, Observer { status ->
                        Log.d(TAG, "observeIsUserFavorite: ${status.status}")
                        isFavorite = when(status.status){
                            Status.Type.SUCCESS -> {
                                favoriteUserModel = status.data
                                fabFavoriteDetailProfile.setImageResource(R.drawable.ic_baseline_favorite_24)
                                true
                            }
                            Status.Type.FAILED -> {
                                fabFavoriteDetailProfile.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                                false
                            }
                        }
                    }
                )
            }
        }

//        binding.apply {
//            when {
//                favoriteUserModel != null -> {
//                    fabFavoriteDetailProfile.setImageResource(R.drawable.ic_baseline_favorite_24)
//                }
//                else -> {
//                    fabFavoriteDetailProfile.setImageResource(R.drawable.ic_baseline_favorite_border_24)
//                }
//            }
//        }
    }

    private fun observeDataDetailProfile(username: String) {
        detailProfileFragmentViewModel.getDetailProfile(username = username).observe(
            viewLifecycleOwner, Observer { status ->
                when(status.status){
                    Status.Type.SUCCESS -> {
                        status.data.apply {
                            setData(this!!)
                            showDetailContainer()
                        }
                    }
                    Status.Type.FAILED -> {
                        Log.d(TAG, "observeDataDetailProfile: failed")
                    }
                }
            }
        )
    }

    private fun showDetailContainer() {
        binding.apply {
            containerDetailProfile.show()
            fabFavoriteDetailProfile.show()
            viewPagerDetailProfile.show()
            tabLayoutDetailProfile.show()
            pbLoadDetailProfile.hide()
        }
    }

    private fun setData(userDetail: UserDetail) {
        binding.apply {
            tvNameDetailProfile.text = userDetail.name
            tvUsernameDetailProfile.text = userDetail.login
            tvLocationDetailProfile.text = userDetail.location
            tvFollowerCountDetailProfile.text = getString(R.string.c_follower, userDetail.followers)
            tvFollowingDetailProfile.text = getString(R.string.c_following, userDetail.following)
            tvBioDetailProfile.text = userDetail.bio
            Glide.with(requireContext())
                .load(userDetail.avatarUrl)
                .circleCrop()
                .into(ivUserDetailProfile)
        }

        userDetailModel = userDetail
        observeIsUserFavorite()
        Log.d(TAG, "setData: $userDetail")
    }

    companion object {
        var TAG = DetailProfileFragment::class.java.simpleName
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
    }
}