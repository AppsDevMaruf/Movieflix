package com.maruf.movieflix.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.maruf.movieflix.BaseFragment
import com.maruf.movieflix.R
import com.maruf.movieflix.databinding.FragmentDetailsBinding
import com.maruf.movieflix.ui.viewmodels.MovieViewModel
import com.maruf.movieflix.utils.Constants.IMG_PREFIX
import com.maruf.movieflix.utils.Constants.TAG
import com.maruf.movieflix.utils.NetworkResult
import com.maruf.movieflix.utils.timeFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    private val movieViewModel by activityViewModels<MovieViewModel>()

    override fun getFragmentView(): Int {
        return R.layout.fragment_details
    }

    override fun configUi() {
        val currentMovieId = movieViewModel.getCurrentMovieId()
        movieViewModel.getDetailsMovieVM(currentMovieId)
    }

    override fun setupNavigation() {
        binding.backBtnMovieDetails.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun binObserver() {
        movieViewModel.detailsMovieVMLD.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Error -> {
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Success -> {
                    it.data?.let {
                        binding.apply {
                            movieImg.load(IMG_PREFIX + it.posterPath)
                            movieName.text = it.title
                            durationMovieDetails.text = "${it.runtime} minutes"
                            rating.text = it.voteAverage.toString()
                            releaseDateMovieDetails.text = it.releaseDate
                            genreMovieDetails.text = it.genres[0].name
                            movieOverviewMovieDetails.text = it.overview
                            if (it.adult) {
                                adultCheckMovieSlider.setTextColor(Color.parseColor("#FF0000"));
                            }


                        }


                    }

                }
            }
        }
    }

}