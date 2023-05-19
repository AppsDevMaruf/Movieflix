package com.maruf.movieflix.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maruf.movieflix.databinding.ItemQuoteProgressBinding

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoadViewHolder>() {

    class LoadViewHolder(private val binding: ItemQuoteProgressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindState(loadState: LoadState) {
            binding.quoteTvProgress.isVisible = loadState is LoadState.Loading
        }

    }

    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
        return LoadViewHolder(
            ItemQuoteProgressBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

}