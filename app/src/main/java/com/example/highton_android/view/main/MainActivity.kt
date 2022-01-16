package com.example.highton_android.view.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseActivity
import com.example.highton_android.databinding.ActivityMainBinding
import com.example.highton_android.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpToolbar()
        setUpBottomNavigationView()
        getUser()
    }

    private fun getUser() {
        if (mainViewModel.token.value == null) {
            mainViewModel.getToken()
        }

        mainViewModel.token.observe(this) {
            mainViewModel.getUser()
        }

        mainViewModel.user.observe(this) { response ->
            when(response) {
                is NetworkResult.Success -> {

                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    fun setMenuVisibility(isVisible: Boolean) {
        if (isVisible) supportActionBar?.show() else supportActionBar?.hide()
        binding.mainBottomNav.visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.fabAdd.visibility = if (isVisible) View.VISIBLE else View.GONE
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

        binding.fabAdd.setOnClickListener {
            when(navController.currentDestination?.id){
                    R.id.diaryFragment->{
                        navController.navigate(R.id.action_diaryFragment_to_diaryWriteFragment)
                    }
            }
        }

    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onNavigateUp()

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_notification -> {
                makeToast("알림 설정 TODO")
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }
}