package com.man.movies.screen.dashboard

import android.content.Context
import android.content.Intent
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.man.movies.R
import com.man.movies.base.BaseActivity

class DashboardActivity : BaseActivity() {

    companion object {
        fun newInstance(context: Context) : Intent {
            return Intent(context, DashboardActivity::class.java)
        }
    }

    override fun getLayoutResource(): Int = R.layout.activity_dashboard

    override fun initComponent() {
        activityComponent.inject(this)
        setupNavigation()
    }

    private fun setupNavigation(){
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_popular, R.id.navigation_nowplaying, R.id.navigation_upcoming
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}