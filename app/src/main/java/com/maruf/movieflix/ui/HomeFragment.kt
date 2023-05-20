package com.maruf.movieflix.ui

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.maruf.movieflix.paging.LoaderAdapter
import com.maruf.movieflix.BaseFragment
import com.maruf.movieflix.R
import com.maruf.movieflix.databinding.FragmentHomeBinding
import com.maruf.movieflix.adapters.DiscoverMovieAdapter
import com.maruf.movieflix.ui.viewmodels.MovieViewModel
import com.maruf.movieflix.utils.Constants.TAG
import com.maruf.movieflix.utils.hide
import com.maruf.movieflix.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(),DiscoverMovieAdapter.MovieListener {
    private val movieViewModel by activityViewModels<MovieViewModel>()
    private lateinit var adapter: DiscoverMovieAdapter
    override fun getFragmentView(): Int {
        return R.layout.fragment_home
    }

    override fun configUi() {
        binding.progressBar.show()
        adapter = DiscoverMovieAdapter(this)
        binding.discoverMovieRecView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )


    }

    override fun binObserver() {

        GlobalScope.launch(Dispatchers.Main) {
            movieViewModel.discoverMovieListVMLD.observe(viewLifecycleOwner) {
                adapter.submitData(lifecycle, it)
                binding.progressBar.hide()
            }
        }
    }

    override fun movieItemClick(movieId: Int) {
        movieViewModel.setCurrentMovieId(movieId)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)

    }

}