package com.soerjdev.dicodingbfaasubmission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentDetailProfileBinding

class DetailProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDetailProfileBinding>(inflater, R.layout.fragment_detail_profile, container, false)

        binding.apply {
            tbFragmentDetailProfile.setNavigationOnClickListener {

            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {bundle ->
            val username = DetailProfileFragmentArgs.fromBundle(bundle).username
        }
    }

    companion object {

    }
}