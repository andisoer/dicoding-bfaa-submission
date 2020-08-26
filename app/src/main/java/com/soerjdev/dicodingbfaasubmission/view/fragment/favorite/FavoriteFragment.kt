package com.soerjdev.dicodingbfaasubmission.view.fragment.favorite

import android.os.Bundle
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.data.adapter.FavoriteUsersAdapter
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel
import com.soerjdev.dicodingbfaasubmission.data.model.UserDetail
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentFavoriteBinding
import com.soerjdev.dicodingbfaasubmission.utils.hide
import com.soerjdev.dicodingbfaasubmission.utils.show

class FavoriteFragment : Fragment(), Toolbar.OnMenuItemClickListener,
    FavoriteUsersAdapter.Listener {

    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var favoriteViewModel: FavoriteFragmentViewModel

    private lateinit var adapter: FavoriteUsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_favorite, container, false)

        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        adapter = FavoriteUsersAdapter(requireContext(), this)

        binding.apply {
            tbFragmentFavorite.setNavigationOnClickListener {
                activity?.onBackPressed()
            }

            tbFragmentFavorite.setOnMenuItemClickListener(this@FavoriteFragment)

            rvUserFavorite.layoutManager = LinearLayoutManager(requireContext())
            rvUserFavorite.adapter = adapter

        }

        favoriteViewModel = ViewModelProvider(this).get(FavoriteFragmentViewModel::class.java)

        observeFavoriteUsersList()
    }

    private fun observeFavoriteUsersList() {
        binding.apply {
            favoriteViewModel.favoriteUsersList(context = requireContext()).observe(
                viewLifecycleOwner, Observer { favoriteUsers ->
                    favoriteUsers.let {
                        binding.apply {
                            pbLoadFavorite.hide()
                            if (it.isNotEmpty()){
                                adapter.setFavoritesData(it)
                            } else {
                                adapter.notifyDataSetChanged()
                                layoutEmptyDataFavorite.show()
                            }
                        }
                    }
                })
        }
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
}