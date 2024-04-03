package com.eroglu.movieapp.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eroglu.movieapp.model.MovieResult
import com.eroglu.movieapp.service.Repository
import com.eroglu.movieapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    //val popularMoviesList = MutableLiveData<PopularMovies>()

    val selectedPopularMovie = SingleLiveEvent<MovieResult>()
    val selectedUpcommingMovie = SingleLiveEvent<MovieResult>()

    init {
        getPopularMovies()
        getUpcomingMovies()
    }

    val popularMoviesAdapter =  PopularMoviesAdapter().apply {
        itemClickedListener = object : ItemClickedListener{
            override fun onItemClicked(item: MovieResult) {
                selectedPopularMovie.postValue(item)
            }
        }
    }

    val upcomingMoviesAdapter =  PopularMoviesAdapter().apply {
        itemClickedListener = object : ItemClickedListener{
            override fun onItemClicked(item: MovieResult) {
                selectedUpcommingMovie.postValue(item)
            }
        }
    }


    private fun getPopularMovies(){
        viewModelScope.launch {
            val result = repository.getPopularMovies()

            if (result.isSuccessful){
                //popularMoviesList.postValue(result.body())

                result.body()?.results?.let {
                    popularMoviesAdapter.list = it
                }
            }
        }
    }

    private fun getUpcomingMovies(){
        viewModelScope.launch {
            val result = repository.getUpcomingMovies()

            if (result.isSuccessful){
                result.body()?.results?.let {
                    upcomingMoviesAdapter.list = it
                }
            }
        }
    }
}