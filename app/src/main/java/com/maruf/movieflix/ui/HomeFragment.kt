package com.maruf.movieflix.ui

import android.util.Log
import androidx.fragment.app.viewModels
import com.maruf.movieflix.BaseFragment
import com.maruf.movieflix.R
import com.maruf.movieflix.databinding.FragmentHomeBinding
import com.maruf.movieflix.ui.viewmodels.MovieViewModel
import com.maruf.movieflix.utils.Constants
import com.maruf.movieflix.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val movieViewModel by viewModels<MovieViewModel>()

    override fun getFragmentView(): Int {
        return R.layout.fragment_home
    }

    override fun configUi() {
        movieViewModel.getDiscoverMovieVM(1)


    }

    override fun binObserver() {
        movieViewModel.discoverMovieVMLD.observe(viewLifecycleOwner) {
            //progressBar.isVisible = false
            when (it) {

                is NetworkResult.Error -> {
                    Log.i("Error", "NetworkResult.Error: ${it.data!!}")
                    //Log.i("TAG1", "binObserver: ${it.data!!.message.toString()}")
                }
                is NetworkResult.Loading -> {
                    // progressBar.isVisible = true

                }
                is NetworkResult.Success -> {
                    Log.i("SuccessTAG", "Success: ${it.data!!}")

                }

            }

        }


    }


}