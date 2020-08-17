package com.soerjdev.dicodingbfaasubmission.view.fragment.favorite

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.data.adapter.FavoriteUsersAdapter
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel
import com.soerjdev.dicodingbfaasubmission.data.model.UserDetail
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentFavoriteBinding
import com.soerjdev.dicodingbfaasubmission.view.fragment.home.HomeFragmentDirections

class FavoriteFragment : Fragment(), Toolbar.OnMenuItemClickListener,
    FavoriteUsersAdapter.Listener {

    private lateinit var favoriteViewModel: FavoriteViewModel

    private lateinit var adapter: FavoriteUsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentFavoriteBinding>(inflater,
            R.layout.fragment_favorite, container, false)

        adapter = FavoriteUsersAdapter(requireContext(), this)

        binding.apply {
            tbFragmentFavorite.setNavigationOnClickListener {
                activity?.onBackPressed()
            }

            tbFragmentFavorite.setOnMenuItemClickListener(this@FavoriteFragment)

            rvUserFavorite.layoutManager = LinearLayoutManager(requireContext())
            rvUserFavorite.adapter = adapter

        }

        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        favoriteViewModel.favoriteUsersList.observe(
            viewLifecycleOwner, Observer { favoriteUsers ->
                favoriteUsers.let {
                    if (it.isNotEmpty()){
                        adapter.setFavoritesData(it)
                    } else {
                        adapter.notifyDataSetChanged()
                        Log.d(TAG, "onViewCreated: empty")
                    }
                }
            })
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
    }

    override fun onFavoriteClickListener(view: View, data: FavoriteModel) {
        val tempUserDetail = Gson().toJson(data)
        val userDetail = Gson().fromJson(tempUserDetail, UserDetail::class.java)
        val navigation = FavoriteFragmentDirections.actionFavoriteFragmentToDetailProfileFragment(userDetail.login, userDetail)
        view.findNavController().navigate(navigation)
    }

    companion object {
        var TAG = FavoriteFragment::class.java.simpleName
    }
}