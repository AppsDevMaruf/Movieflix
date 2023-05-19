package com.maruf.movieflix.api

import com.maruf.movieflix.data.model.DetailsMovie
import com.maruf.movieflix.data.model.DiscoverMovie
import com.maruf.movieflix.utils.Constants
import retrofit2.Response
import retrofit2.http.*


interface MovieApi {
    //discover Movie
    // https://api.themoviedb.org/3/discover/movie?api_key=cd890f94a756b1518a2a17617a5b430e
    @GET("discover/movie?api_key=${Constants.API_KEY}")
    suspend fun getDiscoverMovie(@Query("page") page: Int): Response<DiscoverMovie>
/*
    @GET("discover/movie?")
    suspend fun getDiscoverMovie(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<DiscoverMovie>*/
    //details Movie
    // https://api.themoviedb.org/3/movie/862?api_key=cd890f94a756b1518a2a17617a5b430e
    @GET("movie/{movie_id}?")
    fun getDetailsMovie(@Part("movie_id") movieId: Int,@Query("api_key") apiKey: String,): Response<DetailsMovie>
}