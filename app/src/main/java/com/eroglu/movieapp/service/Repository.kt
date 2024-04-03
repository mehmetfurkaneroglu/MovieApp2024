package com.eroglu.movieapp.service

import com.eroglu.movieapp.model.PopularMovies
import com.eroglu.movieapp.model.UpcommingMovies
import retrofit2.Response

class Repository(private val api: MovieAPI) {

    suspend fun getPopularMovies(): Response<PopularMovies>{
        return api.getPopularMovies()
    }

    suspend fun getUpcomingMovies(): Response<UpcommingMovies>{
        return api.getUpcomingMovies()
    }

}