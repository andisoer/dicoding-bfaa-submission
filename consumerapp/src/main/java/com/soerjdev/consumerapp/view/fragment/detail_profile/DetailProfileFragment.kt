package com.soerjdev.consumerapp.view.fragment.detail_profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.soerjdev.consumerapp.R
import com.soerjdev.consumerapp.data.adapter.ProfileViewPagerAdapter
import com.soerjdev.consumerapp.data.model.FavoriteModel
import com.soerjdev.consumerapp.databinding.FragmentDetailProfileBinding
import com.soerjdev.consumerapp.utils.hide
import com.soerjdev.consumerapp.utils.show
import com.soerjdev.consumerapp.view.fragment.detail_profile.DetailProfileFragmentArgs

class DetailProfileFragment : Fragment() {

    private lateinit var detailProfileFragmentViewModel: DetailProfileFragmentViewModel
    private lateinit var binding: FragmentDetailProfileBinding

    private lateinit var profileViewPagerAdapter : ProfileViewPagerAdapter

    private val tabTitle = arrayOf("Following", "Follower")

    private var favoriteUserModel: FavoriteModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentDetailProfileBinding>(inflater, R.layout.fragment_detail_profile, container, false)

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detail_profile, container, false)

        detailProfileFragmentViewModel = ViewModelProvider(this).get(DetailProfileFragmentViewModel::class.java)

        arguments?.let { bundle ->
            val username = DetailProfileFragmentArgs.fromBundle(
                bundle
            ).username
            val data = DetailProfileFragmentArgs.fromBundle(
                bundle
            ).userDetail

            Log.d(TAG, "onViewCreated: $username")
            Log.d(TAG, "onViewCreated: $data")

            profileViewPagerAdapter = ProfileViewPagerAdapter(fragment = this, username = username!!)

            val tempFavoriteModel = Gson().toJson(data)
            val favoriteModel = Gson().fromJson(tempFavoriteModel, FavoriteModel::class.java)
            favoriteUserModel = favoriteModel
            setData(favoriteModel = data!!)
            showDetailContainer()
            isUsersFavorite()
        }

        binding.apply {
            tbFragmentDetailProfile.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
//            tbFragmentDetailProfile.setOnMenuItemClickListener(this@DetailProfileFragment)

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
                detailProfileFragmentViewModel.deleteFavoriteUsers(user_id = favoriteUserModel!!.id, context = requireContext())
                favoriteUserModel = null
                isUsersFavorite()
            }
        }
    }

    private fun addToUsersFavorite() {
        when {
            favoriteUserModel != null -> {
                detailProfileFragmentViewModel.insertFavoriteUsers(user = favoriteUserModel!!, context = requireContext())
                isUsersFavorite()
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

    private fun showDetailContainer() {
        binding.apply {
            containerDetailProfile.show()
            fabFavoriteDetailProfile.show()
            viewPagerDetailProfile.show()
            tabLayoutDetailProfile.show()
            pbLoadDetailProfile.hide()
        }
    }

    private fun setData(favoriteModel: FavoriteModel) {
        binding.apply {
            tvNameDetailProfile.text = favoriteModel.name
            tvUsernameDetailProfile.text = favoriteModel.login
            tvLocationDetailProfile.text = favoriteModel.location
            tvFollowerCountDetailProfile.text = getString(R.string.c_follower, favoriteModel.followers)
            tvFollowingDetailProfile.text = getString(R.string.c_following, favoriteModel.following)
            tvBioDetailProfile.text = favoriteModel.bio
            Glide.with(requireContext())
                .load(favoriteModel.avatarUrl)
                .circleCrop()
                .into(ivUserDetailProfile)
        }

    }

    companion object {
        var TAG = DetailProfileFragment::class.java.simpleName
    }

}