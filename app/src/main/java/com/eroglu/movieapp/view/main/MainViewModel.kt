package com.eroglu.movieapp.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eroglu.movieapp.model.MovieResult
import com.eroglu.movieapp.service.Repository
import com.eroglu.movieapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var isPopularLoading = MutableLiveData(false)
    var isUpcomingLoading = MutableLiveData(false)

    val selectedMovie = SingleLiveEvent<MovieResult>()

    init {
        getPopularMovies()
        getUpcomingMovies()
    }

    val moviesAdapter = MoviesAdapter().apply {
        itemClickedListener = object : ItemClickedListener {
            override fun onItemClicked(item: MovieResult) {
                selectedMovie.postValue(item)
            }
        }
    }

    val upcomingMoviesAdapter = MoviesAdapter().apply {
        itemClickedListener = object : ItemClickedListener {
            override fun onItemClicked(item: MovieResult) {
                selectedMovie.postValue(item)
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            isPopularLoading.value = true
            val result = repository.getPopularMovies()

            if (result.isSuccessful) {
                result.body()?.let {
                    it.results?.let {
                        moviesAdapter.list = it
                    }
                }
            }
            isPopularLoading.value = false
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            isUpcomingLoading.value = true

            val result = repository.getUpcomingMovies()

            if (result.isSuccessful) {

                result.body()?.let {
                    it.results?.let {
                        upcomingMoviesAdapter.list = it
                    }
                    isUpcomingLoading.value = false
                }
            }
        }
    }
}