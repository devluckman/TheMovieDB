package com.man.movies.screen.dashboard.ui.nowplaying

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.man.movies.R
import com.man.movies.base.BaseFragment
import com.man.movies.data.NetworkState
import com.man.movies.data.TypeMovie
import com.man.movies.data.source.MoviePagedListRepository
import com.man.movies.extentions.gone
import com.man.movies.extentions.visible
import com.man.movies.screen.adapter.MovieAdapter
import com.man.movies.screen.dashboard.ui.ViewModelMovies
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class NowPlayingFragment : BaseFragment() {

    private lateinit var viewModel: ViewModelMovies
    private lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var repository: MoviePagedListRepository

    override fun getLayoutResource(): Int = R.layout.fragment_movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieAdapter = MovieAdapter()
    }

    override fun initComponent() {
        fragmentComponent.inject(this)
        viewModel = viewModelMovies()
        showLoadingContainer(true)
        setupView()
    }

    private fun viewModelMovies() : ViewModelMovies {
        return ViewModelProvider(this, ViewModelMovies.Factory(repository, TypeMovie.NOWPLAYING))[ViewModelMovies::class.java]
    }

    private fun setupView(){
        viewModel.moviePagedList.observe(viewLifecycleOwner, Observer {
            movieAdapter.submitList(it)
            rv_movie_nowplaying.layoutManager = GridLayoutManager(activity, 2)
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