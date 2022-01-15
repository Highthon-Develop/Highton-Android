package com.example.highton_android.view.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseActivity
import com.example.highton_android.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpToolbar()
        setUpBottomNavigationView()
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setUpBottomNavigationView() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.feedFragment,
                R.id.diaryFragment,
                R.id.homeFragment,
                R.id.calendarFragment,
                R.id.profileFragment
            )
        )

        binding.mainBottomNav.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.mainBottomNav.selectedItemId = R.id.homeFragment
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onNavigateUp()
}