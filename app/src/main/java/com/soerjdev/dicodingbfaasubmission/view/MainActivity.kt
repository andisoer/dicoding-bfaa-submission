package com.soerjdev.dicodingbfaasubmission.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.soerjdev.dicodingbfaasubmission.R
import com.soerjdev.dicodingbfaasubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setContentView(binding.root)

        initView()
        initNavController()
    }

    private fun initView() {
        supportActionBar?.hide()
    }

    private fun initNavController() {
        val navController = findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.navHostFragment).navigateUp()

}