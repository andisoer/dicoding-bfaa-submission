package com.soerjdev.consumerapp

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.soerjdev.consumerapp.databinding.FragmentHomeBinding
import com.soerjdev.consumerapp.model.SearchResponse

class HomeFragment : Fragment(), UserSearchAdapter.Listener, Toolbar.OnMenuItemClickListener {

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter : UserSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)

        adapter = UserSearchAdapter(requireContext(), this@HomeFragment)

        binding.apply {
            tbFragmentHome.setOnMenuItemClickListener(this@HomeFragment)

            rvUserListHome.layoutManager = LinearLayoutManager(context)
            rvUserListHome.adapter = adapter
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        observeFavoriteUsersList()
    }

    private fun observeFavoriteUsersList() {
        binding.apply {
            pbLoadHome.show()
            layoutEmptyDataHome.hide()
            homeFragmentViewModel.favoriteUsersList(context = requireContext()).observe(
                viewLifecycleOwner, Observer { favoriteUsers ->
                    favoriteUsers.let {
                        binding.apply {
                            pbLoadHome.hide()
                            if (it.isNotEmpty()){
                                adapter.setUserSearchData(it)
                            } else {
                                adapter.notifyDataSetChanged()
                                layoutEmptyDataHome.show()
                                Log.d(TAG, "onViewCreated: empty")
                            }
                        }
                    }
                })
        }
    }

    companion object {
        var TAG = HomeFragment::class.java.simpleName
    }

    override fun onUserClickListenre(view: View, data: FavoriteModel) {
        Log.d(TAG, "onUserClickListenre: $data")
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        Log.d(TAG, "onMenuItemClick: ")
        return false
    }
}