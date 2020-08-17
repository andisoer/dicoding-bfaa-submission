package com.soerjdev.dicodingbfaasubmission.view.fragment.home

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
import com.soerjdev.dicodingbfaasubmission.*
import com.soerjdev.dicodingbfaasubmission.data.model.SearchResponse
import com.soerjdev.dicodingbfaasubmission.data.adapter.UserSearchAdapter
import com.soerjdev.dicodingbfaasubmission.data.model.Status
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentHomeBinding
import com.soerjdev.dicodingbfaasubmission.utils.hide
import com.soerjdev.dicodingbfaasubmission.utils.show

class HomeFragment : Fragment(), Toolbar.OnMenuItemClickListener, UserSearchAdapter.Listener {

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter : UserSearchAdapter

    companion object {
        var TAG = HomeFragment::class.java.simpleName
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

            ivSearchHome.setOnClickListener {
                searchUser()
            }

            rvUserListHome.layoutManager = LinearLayoutManager(context)
            rvUserListHome.adapter = adapter
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "onActivityCreated()")
        observeDataUserSearch(binding.edtSearchHome.text.toString())
    }

    private fun searchUser() {
        binding.apply {
            if (!edtSearchHome.text.isBlank()){
//                pbLoadHome.show()
                rvUserListHome.hide()
                pbLoadHome.show()
                observeDataUserSearch(edtSearchHome.text.toString())
                Log.d(TAG, "searchUser()")
            }
        }
    }

    private fun observeDataUserSearch(query: String) {
//        Log.d(TAG, "observeDataUserSearch() query : $query")
        Log.d(TAG, "observeDataUserSearch: pb showing ${binding.pbLoadHome.isShown}")
        homeFragmentViewModel.getUserBySearch(query = query).observe(
            viewLifecycleOwner, Observer {  status ->
                when (status.status) {
                    Status.Type.SUCCESS -> {
                        binding.pbLoadHome.hide()
                        status.data?.apply {
                            if (this.totalCount > 0) {
                                adapter.setUserSearchData(this.items)
                                binding.rvUserListHome.show()
                            }else {
                                Log.d(TAG, "observeDataUserSearch: empty")
                            }
                        }
//                        binding.pbLoadHome.hide()
                        Log.d(TAG, "observeDataUserSearch: pb showing ${binding.pbLoadHome.isShown}")
                    }
                    Status.Type.FAILED -> {
                        binding.pbLoadHome.hide()
                        Log.d(TAG, "observeDataUserSearch: ${status.message}")
//                        binding.pbLoadHome.hide()
                        Log.d(TAG, "observeDataUserSearch: pb showing ${binding.pbLoadHome.isShown}")
                    }
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun init() {
        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
    }

    override fun onUserClickListenre(view: View, data: SearchResponse) {
        val navigation = HomeFragmentDirections.actionHomeFragmentToDetailProfileFragment(data.login, null)
        view.findNavController().navigate(navigation)
    }
}