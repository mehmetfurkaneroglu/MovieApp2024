package com.eroglu.movieapp.view.detail.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eroglu.movieapp.model.movies.Genre
import com.eroglu.movieapp.model.movies.MovieDetails
import com.eroglu.movieapp.model.movies.ProductionCompany
import com.eroglu.movieapp.model.movies.ProductionCountry
import com.eroglu.movieapp.service.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _selectedMovieDetail = MutableLiveData<MovieDetails>()
    val selectedMovieDetail : LiveData<MovieDetails> = _selectedMovieDetail


    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            val result = repository.getMoviesDetail(id.toString())
            if (result.isSuccessful) {
                result.body()?.let {
                    _selectedMovieDetail.postValue(it)
                }
            }else{
                println("")
            }
        }
    }

    fun getProductionCompanies(companies: List<ProductionCompany>?): String {
        val companyNames = StringBuilder()
        companies?.forEach { company ->
            companyNames.append(company.name).append(", ")
        }
        // Son virgülü kaldır
        if (companyNames.isNotEmpty()) {
            companyNames.deleteCharAt(companyNames.length - 2)
        }
        return companyNames.toString()
    }

    fun getGenres(genres: List<Genre>?): String {
        val genreNames = StringBuilder()
        genres?.forEach { genre ->
            genreNames.append(genre.name).append(", ")
        }
        // Son virgülü kaldır
        if (genreNames.isNotEmpty()) {
            genreNames.deleteCharAt(genreNames.length - 2)
        }
        return genreNames.toString()
    }

    fun getProductionCountries(countries: List<ProductionCountry>?): String {
        val countryNames = StringBuilder()
        countries?.forEach { country ->
            countryNames.append(country.name).append(", ")
        }
        // Son virgülü kaldır
        if (countryNames.isNotEmpty()) {
            countryNames.deleteCharAt(countryNames.length - 2)
        }
        return countryNames.toString()
    }


}