package com.eroglu.movieapp.service

import com.eroglu.movieapp.model.MovieDetails
import com.eroglu.movieapp.model.PopularMovies
import com.eroglu.movieapp.model.UpcommingMovies
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

}