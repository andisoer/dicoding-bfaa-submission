package com.soerjdev.dicodingbfaasubmission

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentDetailProfileBinding
import kotlin.math.log

class DetailProfileFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDetailProfileBinding>(inflater, R.layout.fragment_detail_profile, container, false)

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

        arguments?.let {bundle ->
            val username = DetailProfileFragmentArgs.fromBundle(bundle).username
            val data = DetailProfileFragmentArgs.fromBundle(bundle).userDetail

            Log.d(TAG, "onViewCreated: $username")
        }
    }

    companion object {
        var TAG = DetailProfileFragment::class.java.simpleName
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
    }
}