package com.man.movies.screen.dashboard.ui.nowplaying

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.man.movies.R
import com.man.movies.base.BaseFragment
import com.man.movies.data.NetworkState
import com.man.movies.data.TypeMovie
import com.man.movies.data.source.MoviePagedListRepository
import com.man.movies.event.SearchEvent
import com.man.movies.extentions.gone
import com.man.movies.extentions.visible
import com.man.movies.screen.adapter.MovieAdapter
import com.man.movies.screen.adapter.SearchAdapter
import com.man.movies.screen.dashboard.ui.ViewModelMovies
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class NowPlayingFragment : BaseFragment() {

    private lateinit var viewModel: ViewModelMovies
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var searchAdapter: SearchAdapter

    @Inject
    lateinit var repository: MoviePagedListRepository

    override fun getLayoutResource(): Int = R.layout.fragment_movie



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieAdapter = MovieAdapter()
        searchAdapter = SearchAdapter()
    }

    override fun initComponent() {
        fragmentComponent.inject(this)
        viewModel = viewModelMovies()
        setupView()
        setupViewSearch()
        showLoadingContainer(true)
    }

    private fun viewModelMovies(): ViewModelMovies {
        return ViewModelProvider(
            this,
            ViewModelMovies.Factory(repository, TypeMovie.NOWPLAYING)
        )[ViewModelMovies::class.java]
    }

    private fun setupView() {
        viewModel.moviePagedList.observe(viewLifecycleOwner, Observer {
            movieAdapter.submitList(it)
            rv_movie.layoutManager = GridLayoutManager(activity, 2)
            rv_movie.setHasFixedSize(true)
            rv_movie.adapter = movieAdapter
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            if (viewModel.movieListIsEmpty() && it == NetworkState.LOADING) showLoadingContainer(
                true
            ) else showLoadingContainer(false)
            if (!viewModel.movieListIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })
    }

    private fun setupViewSearch() {
        rv_movie_search.layoutManager = GridLayoutManager(activity, 2)
        rv_movie_search.setHasFixedSize(true)
        rv_movie_search.adapter = searchAdapter
    }

    private fun showLoadingContainer(active: Boolean) {
        if (context == null) return
        layoutView?.let {
            it.vaMovie.displayedChild = if (active) 0 else 1
        }
    }

    @Subscribe
    fun onReceiveEventBus(event: SearchEvent) {
        if (event.query.isEmpty()) {
            showMovie()
        } else if(flag) {
            viewModel.getFilteredList(event.query).observe(viewLifecycleOwner, Observer {
                if (it.isEmpty()){
                    Toast.makeText(context, "Not Found Or Try Scroll List Movie", Toast.LENGTH_LONG).show()
                    showMovie()
                }else{
                    searchAdapter.setNewData(it)
                    showSearchResult()
                }
            })
        }
    }

    private fun showMovie(){
        rv_movie.visible()
        rv_movie_search.gone()
    }

    private fun showSearchResult(){
        rv_movie.gone()
        rv_movie_search.visible()
    }


}