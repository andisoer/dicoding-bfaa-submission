package com.soerjdev.dicodingbfaasubmission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentFavoriteBinding>(inflater, R.layout.fragment_favorite, container, false)

        binding.tbFragmentFavorite.setNavigationOnClickListener {
            view!!.findNavController().navigateUp()
        }

        binding.tbFragmentFavorite.setOnMenuItemClickListener(this)

        setHasOptionsMenu(true)

        return binding.root

    }

    companion object {
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
    }
}