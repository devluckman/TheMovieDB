package com.man.movies.screen.dashboard

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search_bar)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()
                sendQuery("$query")
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean = false
        })
        return true
    }

    private fun sendQuery(query : String){
        Toast.makeText(this, query, Toast.LENGTH_LONG).show()
    }
}