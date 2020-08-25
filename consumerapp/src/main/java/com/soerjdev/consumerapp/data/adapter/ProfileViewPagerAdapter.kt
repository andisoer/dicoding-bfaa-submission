package com.soerjdev.consumerapp.data.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.soerjdev.consumerapp.view.fragment.detail_profile.follower.FollowerFragment
import com.soerjdev.consumerapp.view.fragment.detail_profile.following.FollowingFragment

class ProfileViewPagerAdapter(fragment: Fragment, private var username: String) : FragmentStateAdapter(fragment) {

    private val tabCount = 2

    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                FollowingFragment.newInstance(username = username)
            }
            1 -> {
                FollowerFragment.newInstance(username = username)
            }
            else -> FollowingFragment.newInstance(username = username)
        }
    }

}