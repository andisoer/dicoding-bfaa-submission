package com.soerjdev.dicodingbfaasubmission.view.fragment.detail_profile

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
import com.soerjdev.dicodingbfaasubmission.DetailProfileFragmentArgs
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.data.model.Status
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentDetailProfileBinding

class DetailProfileFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var detailProfileFragmentViewModel: DetailProfileFragmentViewModel
    private lateinit var binding: FragmentDetailProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentDetailProfileBinding>(inflater,
            R.layout.fragment_detail_profile, container, false)

        binding.apply {
            tbFragmentDetailProfile.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            tbFragmentDetailProfile.setOnMenuItemClickListener(this@DetailProfileFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        detailProfileFragmentViewModel = ViewModelProvider(this).get(DetailProfileFragmentViewModel::class.java)

        arguments?.let {bundle ->
            val username = DetailProfileFragmentArgs.fromBundle(bundle).username
            val data = DetailProfileFragmentArgs.fromBundle(bundle).userDetail

            Log.d(TAG, "onViewCreated: $username")
            Log.d(TAG, "onViewCreated: $data")

            if (data == null){
                observeDataDetailProfile(username = username)
            }
        }
    }

    private fun observeDataDetailProfile(username: String) {
        detailProfileFragmentViewModel.getDetailProfile(username = username).observe(
            viewLifecycleOwner, Observer { status ->
                when(status.status){
                    Status.Type.SUCCESS -> {
                        status.data.apply {
                            Log.d(TAG, "observeDataDetailProfile: $this")
                        }
                    }
                    Status.Type.FAILED -> {
                        Log.d(TAG, "observeDataDetailProfile: failed")
                    }
                }
            }
        )
    }

    companion object {
        var TAG = DetailProfileFragment::class.java.simpleName
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
    }
}