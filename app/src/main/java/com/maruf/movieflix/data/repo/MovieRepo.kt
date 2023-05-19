package com.maruf.movieflix.data.repo
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.maruf.movieflix.api.MovieApi
import com.maruf.movieflix.paging.MoviePagingSource
import javax.inject.Inject

class MovieRepo @Inject constructor(private val movieApi: MovieApi) {


    //discover movies
    fun discoverMovieRepo() = Pager(
        config = PagingConfig(pageSize =20 , maxSize =100),
        pagingSourceFactory = {MoviePagingSource(movieApi)}
    ).liveData

    //details movies
    suspend fun detailsMovieRepo(movieId:Int,apiKey:String) = movieApi.getDetailsMovie(movieId,apiKey)


}