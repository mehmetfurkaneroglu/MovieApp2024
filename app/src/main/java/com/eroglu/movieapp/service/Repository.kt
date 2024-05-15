package com.eroglu.movieapp.service

import com.eroglu.movieapp.model.movies.MovieDetails
import com.eroglu.movieapp.model.movies.PopularMovies
import com.eroglu.movieapp.model.movies.TopRated
import com.eroglu.movieapp.model.movies.UpcommingMovies
import com.eroglu.movieapp.model.tvSeries.TvSeriesAiringToday
import com.eroglu.movieapp.model.tvSeries.TvSeriesDetail
import com.eroglu.movieapp.model.tvSeries.TvSeriesPopular
import com.eroglu.movieapp.util.Constants.API_KEY
import retrofit2.Response

class Repository(private val api: MovieAPI) {

    suspend fun getPopularMovies(): Response<PopularMovies>{
        return api.getPopularMovies()
    }

    suspend fun getUpcomingMovies(): Response<UpcommingMovies>{
        return api.getUpcomingMovies()
    }

    suspend fun getMoviesDetail(id: String): Response<MovieDetails> {
        return api.getMovieDetail(id,API_KEY)
    }

    suspend fun getPopularTvSeries(): Response<TvSeriesPopular> {
        return api.getPopularTvSeries()
    }

    suspend fun getAiringTodayTvSeries(): Response<TvSeriesAiringToday> {
        return api.getAiringTodayTvSeries()
    }

    suspend fun getTvSeriesDetail(id: String): Response<TvSeriesDetail> {
        return api.getTvSeriesDetail(id,API_KEY)
    }

    suspend fun getTopRatedMovies(): Response<TopRated> {
        return api.getTopRatedMovies()
    }

}