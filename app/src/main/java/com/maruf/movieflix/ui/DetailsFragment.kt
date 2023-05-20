package com.maruf.movieflix.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.maruf.movieflix.BaseFragment
import com.maruf.movieflix.R
import com.maruf.movieflix.adapters.DiscoverMovieAdapter
import com.maruf.movieflix.adapters.RelatedMovieAdapter
import com.maruf.movieflix.data.model.DetailsMovie
import com.maruf.movieflix.databinding.FragmentDetailsBinding
import com.maruf.movieflix.paging.LoaderAdapter
import com.maruf.movieflix.ui.viewmodels.MovieViewModel
import com.maruf.movieflix.utils.Constants.IMG_PREFIX
import com.maruf.movieflix.utils.Constants.TAG
import com.maruf.movieflix.utils.NetworkResult
import com.maruf.movieflix.utils.hide
import com.maruf.movieflix.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    private val movieViewModel by activityViewModels<MovieViewModel>()
    private lateinit var adapter: RelatedMovieAdapter
    override fun getFragmentView(): Int {
        return R.layout.fragment_details
    }

    override fun configUi() {

        val currentMovieId = movieViewModel.getCurrentMovieId()
        movieViewModel.getDetailsMovieVM(currentMovieId)

        adapter = RelatedMovieAdapter()
        binding.relatedRecView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )
        GlobalScope.launch(Dispatchers.Main) {
            movieViewModel.relatedMovieListVMLD.observe(viewLifecycleOwner) {
                adapter.submitData(lifecycle, it)
                Log.i(TAG, "binObserver: $it")

            }
        }

    }

    override fun setupNavigation() {
        binding.backBtnMovieDetails.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun binObserver() {
        movieViewModel.detailsMovieVMLD.observe(viewLifecycleOwner) {
            binding.spinKitMovieDetails.hide()
            when (it) {
                is NetworkResult.Error -> {
                }
                is NetworkResult.Loading -> {
                    binding.spinKitMovieDetails.show()
                }
                is NetworkResult.Success -> {
                    it.data?.let {details->
                     setDetails(details)


                    }

                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setDetails(details: DetailsMovie) {
        binding.apply {
            movieImg.load(IMG_PREFIX + details.posterPath)
            movieName.text = details.title
            durationMovieDetails.text = "${details.runtime} minutes"
            rating.text = "${details.voteAverage} IMDb"
            releaseDateMovieDetails.text = details.releaseDate
            genreMovieDetails.text = details.genres[0].name
            movieOverviewMovieDetails.text = details.overview
            if (details.adult) {
                adultCheckMovieSlider.setTextColor(Color.parseColor("#FF0000"));
            }


        }

    }

}

