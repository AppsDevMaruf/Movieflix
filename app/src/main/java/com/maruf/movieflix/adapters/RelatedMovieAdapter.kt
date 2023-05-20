package com.maruf.movieflix.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.maruf.movieflix.data.model.DiscoverMovie
import com.maruf.movieflix.databinding.RelatedMovieBinding
import com.maruf.movieflix.databinding.SingleMovieBinding
import com.maruf.movieflix.utils.Constants


class RelatedMovieAdapter() :
    PagingDataAdapter<DiscoverMovie.Result, RelatedMovieAdapter.RelatedMovieViewHolder>(Comparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedMovieViewHolder {

        val binding =
            RelatedMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RelatedMovieViewHolder(binding)


    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RelatedMovieViewHolder, position: Int) {
        getItem(position)?.let {

            val poster = Constants.IMG_PREFIX + it.posterPath
            holder.binding.relatedMovieImg.load(poster)
            holder.binding.relatedMovieName.text = it.title
            holder.binding.relatedMoviesReleaseYear.text = it.releaseDate
        }

    }


    inner class RelatedMovieViewHolder(val binding: RelatedMovieBinding) :
        RecyclerView.ViewHolder(binding.root)


    companion object {
        private val Comparator =
            object : DiffUtil.ItemCallback<DiscoverMovie.Result>() {
                override fun areItemsTheSame(
                    oldItem: DiscoverMovie.Result,
                    newItem: DiscoverMovie.Result
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: DiscoverMovie.Result,
                    newItem: DiscoverMovie.Result
                ): Boolean {
                    return oldItem == newItem
                }


            }

    }


}