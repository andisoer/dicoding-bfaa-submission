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
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.Status
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentHomeBinding
import com.soerjdev.dicodingbfaasubmission.hide
import com.soerjdev.dicodingbfaasubmission.show

class HomeFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel
    private lateinit var binding: FragmentHomeBinding


    companion object {
        var TAG = HomeFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home, container, false)

        binding.apply {
            tbFragmentHome.setOnMenuItemClickListener(this@HomeFragment)

            ivSearchHome.setOnClickListener {
                searchUser()
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun searchUser() {
        binding.apply {
            if (!edtSearchHome.text.isBlank()){
                pbLoadHome.show()
                observeDataUserSearch(edtSearchHome.text.toString())
            }
        }
    }

    private fun observeDataUserSearch(query: String) {
        homeFragmentViewModel.getUserBySearch(query = query).observe(
            viewLifecycleOwner, Observer {  status ->
                when (status.status) {
                    Status.Type.SUCCESS -> {
                        status.data?.apply {
                            if (this.totalCount > 0) {
                                Log.d(TAG, "observeDataUserSearch: ${this.items} ")
                            }else {
                                Log.d(TAG, "observeDataUserSearch: empty")
                            }
                        }
                    }
                    Status.Type.FAILED -> {
                        Log.d(TAG, "observeDataUserSearch: ${status.message}")
                    }
                }
                binding.pbLoadHome.hide()
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
}