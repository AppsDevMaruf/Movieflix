package com.maruf.movieflix.paging
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maruf.movieflix.api.MovieApi
import com.maruf.movieflix.data.model.DiscoverMovie
import com.maruf.movieflix.utils.Constants.TAG
import java.lang.Exception

class DiscoverMoviePagingSource(private val movieApi: MovieApi) :
    PagingSource<Int, DiscoverMovie.Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovie.Result> {

        return try {

            val position = params.key ?: 1
            val response = movieApi.getDiscoverMovie(position)
            Log.i(TAG, "loadMovieApi: $response")
            LoadResult.Page(
                data = response.results,

                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }


    override fun getRefreshKey(state: PagingState<Int, DiscoverMovie.Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}