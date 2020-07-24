package com.man.movies.screen.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.movies.R
import com.man.movies.data.NetworkState
import com.man.movies.extentions.gone
import com.man.movies.extentions.inflate
import com.man.movies.extentions.visible
import kotlinx.android.synthetic.main.network_state_item.view.*

class NetworkStateViewHolder (view: View) : RecyclerView.ViewHolder(view)  {

    fun bind(networkState: NetworkState?) {
        with(itemView){
            if (networkState != null && networkState == NetworkState.LOADING) {
                networkStateBar.visible()
            } else {
                networkStateBar.gone()
            }

            if (networkState != null && networkState == NetworkState.ERROR) {
                networkTextView.visible()
                networkTextView.text = networkState.msg
            } else if (networkState != null && networkState == NetworkState.ENDOFLIST) {
                networkTextView.visible()
                networkTextView.text = "No more items"
            } else {
                networkTextView.gone()
            }
        }

    }

    companion object {
        fun create(parent: ViewGroup): NetworkStateViewHolder =
            NetworkStateViewHolder(parent.inflate(R.layout.network_state_item))
    }
}