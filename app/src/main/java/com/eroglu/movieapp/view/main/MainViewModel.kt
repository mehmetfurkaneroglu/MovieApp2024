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

    var isPopularLoading: Boolean = false
    var isUpcomingMovies: Boolean = false

// bunları observe ile liste gelip gelmediğini kontrol ederken kullanıyorduk. Onun yerine Data Binding ile yaptık.
//    val popularMoviesList = SingleLiveEvent<Resource<PopularMovies>>()
//    val upcomingMoviesList = SingleLiveEvent<Resource<UpcommingMovies>>()

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
            //popularMoviesList.postValue(Resource.Loading())
            isPopularLoading = true
            //delay(5000)
            val result = repository.getPopularMovies()

            if (result.isSuccessful){
                result.body()?.let {
                    //isPopularLoading = false eğer sonuç var ise progress barı kapat
                    //popularMoviesList.postValue(Resource.Success(it))
                    it.results?.let {
                        moviesAdapter.list = it
                    }
                }//?:kotlin.run {
                    //popularMoviesList.postValue(Resource.Error("List Null"))
                //}
            }//else{
//                popularMoviesList.postValue(Resource.Error("Call Not Successful"))
//            }
            isPopularLoading = false
        }
    }

    private fun getUpcomingMovies(){
        viewModelScope.launch {
//            upcomingMoviesList.postValue(Resource.Loading())
            isUpcomingMovies = true

            val result = repository.getUpcomingMovies()

            if (result.isSuccessful){

                result.body()?.let {
//                    upcomingMoviesList.postValue(Resource.Success(it))
                    it.results?.let {
                        upcomingMoviesAdapter.list = it
                    }
                }//?:kotlin.run {
//                    upcomingMoviesList.postValue(Resource.Error("List Null"))
//                }
            }//else{
//                upcomingMoviesList.postValue(Resource.Error("Call Not Successful"))
//            }
            isUpcomingMovies = false
        }
    }

}