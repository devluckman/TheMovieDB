package com.man.movies.screen.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.domain.model.movie.MovieItemsModel
import com.man.movies.R
import com.man.movies.extentions.fromUrl
import com.man.movies.extentions.inflate
import kotlinx.android.synthetic.main.movie_list_item.view.*

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var listMovie = arrayListOf<MovieItemsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(parent.inflate(R.layout.movie_list_item))
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    fun setNewData(list: List<MovieItemsModel>){
        listMovie.clear()
        listMovie.addAll(list)
        notifyDataSetChanged()
    }

    class SearchViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(item: MovieItemsModel){
            with(itemView){
                item.let {
                    img_poster_movie.fromUrl(item.posterPath)
                    txt_title_movie.text = item.title
                    txt_rating.text = "${item.voteAverage}"
                }
            }
        }
    }

}