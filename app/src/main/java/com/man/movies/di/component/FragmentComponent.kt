package com.man.movies.di.component

import com.man.movies.di.module.FragmentModule
import com.man.movies.di.scope.PerFragment
import com.man.movies.screen.dashboard.ui.nowplaying.NowPlayingFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(nowPlayingFragment: NowPlayingFragment)
}