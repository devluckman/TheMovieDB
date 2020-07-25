package com.man.movies.di.component

import com.man.movies.di.module.ActivityModule
import com.man.movies.di.scope.PerActivity
import com.man.movies.screen.dashboard.DashboardActivity
import com.man.movies.screen.detail.DetailActivity
import com.man.movies.screen.splash.SplashActivity
import dagger.Component


@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(splashActivity: SplashActivity)
    fun inject(dashboardActivity: DashboardActivity)
    fun inject(detailActivity: DetailActivity)
}