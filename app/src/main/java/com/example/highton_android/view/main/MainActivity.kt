package com.example.highton_android.view.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.highton_android.R
import com.example.highton_android.base.BaseActivity
import com.example.highton_android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        when(navController.currentDestination?.getId()){
            R.id.diaryFragment->{
                binding.fabAdd.setOnClickListener {
                    findNavController(R.id.diaryFragment).navigate(R.id.action_diaryFragment_to_diaryWriteFragment)
                }
            }
        }
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onNavigateUp()

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId) {
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