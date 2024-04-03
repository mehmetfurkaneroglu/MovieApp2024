package com.eroglu.movieapp.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eroglu.movieapp.model.MovieResult
import com.eroglu.movieapp.model.PopularMovies
import com.eroglu.movieapp.model.UpcommingMovies
import com.eroglu.movieapp.service.Repository
import com.eroglu.movieapp.util.Resource
import com.eroglu.movieapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    val popularMoviesList = SingleLiveEvent<Resource<PopularMovies>>()
    val upcomingMoviesList = SingleLiveEvent<Resource<UpcommingMovies>>()

    val selectedPopularMovie = SingleLiveEvent<MovieResult>()
    val selectedUpcommingMovie = SingleLiveEvent<MovieResult>()

    init {
        getPopularMovies()
        getUpcomingMovies()
    }

    val moviesAdapter =  MoviesAdapter().apply {
        itemClickedListener = object : ItemClickedListener{
            override fun onItemClicked(item: MovieResult) {
                selectedPopularMovie.postValue(item)
            }
        }
    }

    val upcomingMoviesAdapter =  MoviesAdapter().apply {
        itemClickedListener = object : ItemClickedListener{
            override fun onItemClicked(item: MovieResult) {
                selectedUpcommingMovie.postValue(item)
            }
        }
    }

    private fun getPopularMovies(){
        viewModelScope.launch {
            popularMoviesList.postValue(Resource.Loading())
//            delay(5000)
            val result = repository.getPopularMovies()

            if (result.isSuccessful){
                result.body()?.let {
                    popularMoviesList.postValue(Resource.Success(it))
                    it.results?.let {
                        moviesAdapter.list = it
                    }
                }?:kotlin.run {
                    popularMoviesList.postValue(Resource.Error("List Null"))
                }
            }else{
                popularMoviesList.postValue(Resource.Error("Call Not Successful"))
            }
        }
    }

    private fun getUpcomingMovies(){
        viewModelScope.launch {
            upcomingMoviesList.postValue(Resource.Loading())

            val result = repository.getUpcomingMovies()

            if (result.isSuccessful){

                result.body()?.let {
                    upcomingMoviesList.postValue(Resource.Success(it))
                    it.results?.let {
                        upcomingMoviesAdapter.list = it
                    }
                }?:kotlin.run {
                    upcomingMoviesList.postValue(Resource.Error("List Null"))
                }
            }else{
                upcomingMoviesList.postValue(Resource.Error("Call Not Successful"))
            }
        }
    }
}