package com.maruf.movieflix

import com.maruf.movieflix.api.MovieApi
import javax.inject.Inject

class MovieRepo @Inject constructor(private val movieApi: MovieApi) {


    //discover movies
    suspend fun discoverMovieRepo(page: Int) = movieApi.getDiscoverMovie(page)
    //details movies
    suspend fun detailsMovieRepo(movieId:Int,apiKey:String) = movieApi.getDetailsMovie(movieId,apiKey)


}