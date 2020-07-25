package com.man.movies.screen.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.man.domain.model.movie.MovieItemsModel
import com.man.movies.R
import com.man.movies.data.NetworkState

class MovieAdapter : PagedListAdapter<MovieItemsModel, ViewHolder>(UserDiffCallback){

    private var networkState: NetworkState? = null
    val MOVIE_TYPE_VIEW = R.layout.movie_list_item
    val NETWORK_TYPE_VIEW = R.layout.network_state_item
    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<MovieItemsModel>() {
            override fun areItemsTheSame(oldItem: MovieItemsModel, newItem: MovieItemsModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieItemsModel, newItem: MovieItemsModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            MOVIE_TYPE_VIEW -> MovieViewHolder.create(parent)
            NETWORK_TYPE_VIEW -> NetworkStateViewHolder.create(parent)
            else -> throw IllegalArgumentException("no view holder")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == MOVIE_TYPE_VIEW ){
            (holder as MovieViewHolder).bindTo(getItem(position))
        }else{
            (holder as NetworkStateViewHolder).bind(networkState)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasAnyExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasAnyExtraRow() && position == itemCount - 1) {
            NETWORK_TYPE_VIEW
        } else {
            MOVIE_TYPE_VIEW
        }
    }

    private fun hasAnyExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState: NetworkState? = this.networkState
        val hadExtraRow: Boolean = hasAnyExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow: Boolean = hasAnyExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(super.getItemCount() - 1)
        }
    }

}