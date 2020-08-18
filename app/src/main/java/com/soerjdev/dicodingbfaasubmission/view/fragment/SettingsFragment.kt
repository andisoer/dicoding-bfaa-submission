package com.soerjdev.dicodingbfaasubmission.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.data.AlarmUtil
import com.soerjdev.dicodingbfaasubmission.databinding.FragmentSettingsBinding
import com.soerjdev.dicodingbfaasubmission.utils.SharedPrefHelper
import java.util.*


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var sharedPrefHelper: SharedPrefHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentSettingsBinding>(inflater,
            R.layout.fragment_settings, container, false)

        sharedPrefHelper = SharedPrefHelper(context = requireContext())

        binding.apply {
            tbFragmentSettings.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            switchAlarmSettings.setOnCheckedChangeListener { buttonView, isChecked ->
                setAlarm(isChecked)
            }
            switchAlarmSettings.isChecked = sharedPrefHelper.getAlarmState()
        }

        return binding.root
    }

    private fun setAlarm(isChecked: Boolean) {
        if (isChecked){
            AlarmUtil.enabledAlarm(
                context = requireContext(),
                title = "Test Title",
                message = "Test Message",
                requestCode = 0,
                time = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 11)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }
            )
            sharedPrefHelper.enabledAlarm()
        }else {
            AlarmUtil.disabledAlarm(
                context = requireContext(),
                requestCode = 0
            )
            sharedPrefHelper.disabledAlarm()
        }
    }

    companion object {

    }
}