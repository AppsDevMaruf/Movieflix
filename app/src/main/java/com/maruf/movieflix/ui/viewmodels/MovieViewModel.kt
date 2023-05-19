package com.maruf.movieflix.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.maruf.movieflix.data.model.DetailsMovie
import com.maruf.movieflix.data.repo.MovieRepo
import com.maruf.movieflix.data.model.DiscoverMovie
import com.maruf.movieflix.utils.NetworkResult
import com.maruf.movieflix.utils.NoInternetException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val movieRepo: MovieRepo
) : ViewModel() {

    private var currentMovieId: Int = 0

    fun setCurrentMovieId(movieId: Int) {
        currentMovieId = movieId
    }

    fun getCurrentMovieId(): Int {
        return currentMovieId
    }

// discover movies start

    val movieListVMLD = movieRepo.discoverMovieRepo().cachedIn(viewModelScope)

    private var _detailsMovieVMLD =
        MutableLiveData<NetworkResult<DetailsMovie>>()
    val detailsMovieVMLD: LiveData<NetworkResult<DetailsMovie>>
        get() = _detailsMovieVMLD

    fun getDetailsMovieVM(movieId: Int) {

        _detailsMovieVMLD.postValue(NetworkResult.Loading())


        viewModelScope.launch {

            try {
                val response = movieRepo.detailsMovieRepo(movieId)

                if (response.isSuccessful && response.body() != null) {


                    _detailsMovieVMLD.postValue(NetworkResult.Success(response.body()!!))

                } else if (response.errorBody() != null) {

                    val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                    _detailsMovieVMLD.postValue(NetworkResult.Error(errorObj.getString("message")))

                }
            } catch (noInternetException: NoInternetException) {
                _detailsMovieVMLD.postValue(noInternetException.localizedMessage?.let {
                    NetworkResult.Error(
                        it
                    )
                })
            }
        }

    }


}