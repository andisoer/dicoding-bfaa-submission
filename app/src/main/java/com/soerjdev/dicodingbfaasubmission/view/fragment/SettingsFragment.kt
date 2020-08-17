package com.soerjdev.dicodingbfaasubmission.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentSettingsBinding>(inflater,
            R.layout.fragment_settings, container, false)

        binding.apply {
            tbFragmentSettings.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }

        return binding.root
    }

    companion object {

    }
}