package com.man.movies.screen.dashboard.ui.nowplaying

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.man.movies.R
import com.man.movies.base.BaseFragment
import com.man.movies.data.NetworkState
import com.man.movies.data.source.MoviePagedListRepository
import com.man.movies.extentions.gone
import com.man.movies.extentions.visible
import com.man.movies.screen.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_nowplaying.*
import timber.log.Timber
import javax.inject.Inject

class NowPlayingFragment : BaseFragment() {

    private lateinit var viewModel: NowPlayingViewModel
    private lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var repository: MoviePagedListRepository

    override fun getLayoutResource(): Int = R.layout.fragment_nowplaying

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieAdapter = MovieAdapter()
    }

    override fun initComponent() {
        fragmentComponent.inject(this)
        viewModel = playingViewModel()
        showLoadingContainer(true)
        setupView()
    }

    private fun playingViewModel() : NowPlayingViewModel{
        return ViewModelProvider(this, NowPlayingViewModel.Factory(repository))[NowPlayingViewModel::class.java]
    }

    private fun setupView(){
        viewModel.moviePagedList.observe(viewLifecycleOwner, Observer {
            movieAdapter.submitList(it)
            rv_movie_nowplaying.layoutManager = LinearLayoutManager(context)
            rv_movie_nowplaying.setHasFixedSize(true)
            rv_movie_nowplaying.adapter = movieAdapter
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            if (viewModel.movieListIsEmpty() && it == NetworkState.LOADING) showLoadingContainer(true) else showLoadingContainer(false)
            if (!viewModel.movieListIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })
    }

    private fun showLoadingContainer(active : Boolean){
        if (active){
            rv_movie_nowplaying.gone()
            shimmer_view_container.visible()
            shimmer_view_container.startShimmer()
        }else{
            Handler().postDelayed({
                shimmer_view_container.stopShimmer()
                shimmer_view_container.gone()
                rv_movie_nowplaying.visible()
            },2000)
        }
    }


}