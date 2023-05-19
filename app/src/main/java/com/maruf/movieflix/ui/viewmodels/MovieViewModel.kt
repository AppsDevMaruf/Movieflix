package com.maruf.movieflix.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruf.movieflix.MovieRepo
import com.maruf.movieflix.data.model.DiscoverMovie
import com.maruf.movieflix.utils.NetworkResult
import com.maruf.movieflix.utils.NoInternetException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {


// discover movies start

    private var _discoverMovieVMLD =
        MutableLiveData<NetworkResult<DiscoverMovie>>()
    val discoverMovieVMLD: LiveData<NetworkResult<DiscoverMovie>>
        get() = _discoverMovieVMLD

    fun getDiscoverMovieVM(page: Int) {

        _discoverMovieVMLD.postValue(NetworkResult.Loading())


        viewModelScope.launch {

            try {
                val response = movieRepo.discoverMovieRepo(page)

                if (response.isSuccessful && response.body() != null) {


                    _discoverMovieVMLD.postValue(NetworkResult.Success(response.body()!!))

                } else if (response.errorBody() != null) {

                    val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                    _discoverMovieVMLD.postValue(NetworkResult.Error(errorObj.getString("message")))

                }
            } catch (noInternetException: NoInternetException) {
                _discoverMovieVMLD.postValue(noInternetException.localizedMessage?.let {
                    NetworkResult.Error(
                        it
                    )
                })
            }
        }

    }


}