package com.man.movies.screen.splash

import android.os.Handler
import com.man.movies.R
import com.man.movies.base.BaseActivity
import com.man.movies.screen.dashboard.DashboardActivity

class SplashActivity : BaseActivity() {
    override fun getLayoutResource(): Int =
        R.layout.activity_splash

    override fun initComponent() {
        activityComponent.inject(this)
        Handler().postDelayed({
            startActivity(DashboardActivity.newInstance(this))
            finish()
        }, 2000)
    }

}