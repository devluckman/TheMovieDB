package com.man.movies.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.domain.model.movie.MovieItemsModel
import com.man.movies.R
import com.man.movies.extentions.fromUrl
import com.man.movies.extentions.inflate
import kotlinx.android.synthetic.main.item_movies.view.*

class MovieViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(item : MovieItemsModel?){
        with(itemView){
            item?.let {
                img_poster_movie.fromUrl(item.backdropPath)
                txt_title_movie.text = item.title
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): MovieViewHolder =
             MovieViewHolder(parent.inflate(R.layout.item_movies))

    }
}