package com.eroglu.movieapp.service

import com.eroglu.movieapp.model.movies.MovieDetails
import com.eroglu.movieapp.model.movies.PopularMovies
import com.eroglu.movieapp.model.movies.TopRated
import com.eroglu.movieapp.model.movies.UpcommingMovies
import com.eroglu.movieapp.model.tvSeries.TopRatedTv
import com.eroglu.movieapp.model.tvSeries.TvSeriesAiringToday
import com.eroglu.movieapp.model.tvSeries.TvSeriesDetail
import com.eroglu.movieapp.model.tvSeries.TvSeriesPopular
import com.eroglu.movieapp.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<PopularMovies>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<UpcommingMovies>

    @GET("movie/{id}}")
    suspend fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieDetails>

    @GET("tv/popular")
    suspend fun getPopularTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<TvSeriesPopular>

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<TvSeriesAiringToday>

    @GET("tv/{id}")
    suspend fun getTvSeriesDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<TvSeriesDetail>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<TopRated>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvSeries(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<TopRatedTv>

}