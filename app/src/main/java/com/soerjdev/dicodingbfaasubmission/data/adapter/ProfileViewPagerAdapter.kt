package com.soerjdev.dicodingbfaasubmission.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile.FollowerFragment
import com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile.FollowingFragment

class ProfileViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabCount = 2

    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                FollowingFragment()
            }
            1 -> {
                FollowerFragment()
            }
            else -> FollowingFragment()
        }
    }

}