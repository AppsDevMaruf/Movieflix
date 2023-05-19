package com.maruf.movieflix.ui

import android.util.Log
import androidx.fragment.app.viewModels
import com.maruf.movieflix.paging.LoaderAdapter
import com.maruf.movieflix.BaseFragment
import com.maruf.movieflix.R
import com.maruf.movieflix.databinding.FragmentHomeBinding
import com.maruf.movieflix.paging.DiscoverMovieAdapter
import com.maruf.movieflix.ui.viewmodels.MovieViewModel
import com.maruf.movieflix.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val movieViewModel by viewModels<MovieViewModel>()
    private lateinit var adapter: DiscoverMovieAdapter

    override fun getFragmentView(): Int {
        return R.layout.fragment_home
    }

    override fun configUi() {

        adapter = DiscoverMovieAdapter()
        binding.discoverMovieRecView.setHasFixedSize(true)
        binding.discoverMovieRecView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )
        movieViewModel.movieListVMLD

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun binObserver() {

        GlobalScope.launch(Dispatchers.Main) {
            movieViewModel.movieListVMLD.observe(viewLifecycleOwner) {
                adapter.submitData(lifecycle, it)
                Log.i(TAG, "binObserver: $it")

            }
        }



    }

}