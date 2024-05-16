package com.eroglu.movieapp.view.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eroglu.movieapp.model.CommonModel
import com.eroglu.movieapp.model.FavoriteItemTypeEnum
import com.eroglu.movieapp.service.Repository
import com.eroglu.movieapp.util.SingleLiveEvent
import com.eroglu.movieapp.view.detail.viewpager.ViewPagerAdapter
import com.eroglu.movieapp.view.tvSeries.ItemClickedListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var isPopularLoading = MutableLiveData(false)
    var isUpcomingLoading = MutableLiveData(false)

    val selectedMovie = SingleLiveEvent<Int>()

    init {
        getPopularMovies()
        getUpcomingMovies()
        getTopRatedMovies()
    }

    val moviesAdapter = MoviesAdapter().apply {
        itemClickedListener = object : ItemClickedListener {
            override fun onItemClicked(item: Int) {
                selectedMovie.postValue(item)
            }
        }
    }

    val upcomingMoviesAdapter = MoviesAdapter().apply {
        itemClickedListener = object : ItemClickedListener {
            override fun onItemClicked(item: Int) {
                selectedMovie.postValue(item)
            }
        }
    }

    val topRatedAdapter = ViewPagerAdapter().apply {
        itemClickedListener = object : ItemClickedListener {
            override fun onItemClicked(item: Int) {
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

    private fun getTopRatedMovies(){
        viewModelScope.launch {
            val result = repository.getTopRatedMovies()
            if (result.isSuccessful) {
                result.body()?.let {
                    it.results?.let {
                        val a = arrayListOf<CommonModel>()
                        it.forEach{
                            a.add(
                                CommonModel(
                                    itemId = it?.id,
                                    itemName = it?.title,
                                    itemPicture = it?.posterPath,
                                    itemType = FavoriteItemTypeEnum.TV_SERIES)
                            )
                        }
                        topRatedAdapter.list= a
                    }
                }
            }
        }
    }


}