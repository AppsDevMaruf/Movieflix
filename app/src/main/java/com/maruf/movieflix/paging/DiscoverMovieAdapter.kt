package com.maruf.movieflix.paging
import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.maruf.movieflix.data.model.DiscoverMovie
import com.maruf.movieflix.databinding.SingleMovieBinding
import com.maruf.movieflix.utils.Constants


class DiscoverMovieAdapter :
    PagingDataAdapter<DiscoverMovie.Result, DiscoverMovieAdapter.DiscoverMovieViewHolder>(Comparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverMovieViewHolder {

        val binding = SingleMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DiscoverMovieViewHolder(binding)


    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DiscoverMovieViewHolder, position: Int) {
        getItem(position)?.let {
            Log.i(Constants.TAG, "binObserverDM: $it")
            val poster = Constants.IMG_PREFIX+it.posterPath
            holder.binding.moviePoster.load(poster)
            holder.binding.movieName.text = it.originalTitle
        }

    }


  inner  class DiscoverMovieViewHolder(val binding: SingleMovieBinding) : RecyclerView.ViewHolder(binding.root)


    companion object {
        private val Comparator =
            object : DiffUtil.ItemCallback<DiscoverMovie.Result>() {
                override fun areItemsTheSame(oldItem: DiscoverMovie.Result, newItem: DiscoverMovie.Result): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: DiscoverMovie.Result, newItem: DiscoverMovie.Result): Boolean {
                    return oldItem == newItem
                }


            }

    }


}