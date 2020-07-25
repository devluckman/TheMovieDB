package com.man.movies.screen.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.domain.model.movie.MovieItemsModel
import com.man.movies.R
import com.man.movies.extentions.fromUrl
import com.man.movies.extentions.inflate
import com.man.movies.screen.detail.DetailActivity
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(item : MovieItemsModel?){
        with(itemView){
            item?.let {
                img_poster_movie.fromUrl(item.posterPath)
                txt_title_movie.text = item.title
                txt_rating.text = "${item.voteAverage}"
            }

            movie_card.setOnClickListener {
                context.startActivity(DetailActivity.newInstance(context, item!!))
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): MovieViewHolder =
             MovieViewHolder(parent.inflate(R.layout.movie_list_item))

    }
}