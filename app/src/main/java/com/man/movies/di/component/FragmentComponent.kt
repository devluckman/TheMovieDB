package com.man.movies.di.component

import com.man.movies.di.module.FragmentModule
import com.man.movies.di.scope.PerFragment
import com.man.movies.screen.dashboard.ui.nowplaying.NowPlayingFragment
import com.man.movies.screen.dashboard.ui.popular.PopularFragment
import com.man.movies.screen.dashboard.ui.upcoming.UpComingFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(nowPlayingFragment: NowPlayingFragment)
    fun inject(upComingFragment: UpComingFragment)
    fun inject(popularFragment: PopularFragment)
}