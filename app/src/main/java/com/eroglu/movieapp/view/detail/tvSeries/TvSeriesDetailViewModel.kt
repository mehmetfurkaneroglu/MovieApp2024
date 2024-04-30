package com.eroglu.movieapp.view.detail.tvSeries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eroglu.movieapp.model.tvSeries.TvSeriesDetail
import com.eroglu.movieapp.service.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _selectedTvSeriesDetail = MutableLiveData<TvSeriesDetail>()
    val selectedTvSeriesDetail : LiveData<TvSeriesDetail> = _selectedTvSeriesDetail

    fun getTvSeriesDetail(id: Int) {
        viewModelScope.launch {
            val result = repository.getTvSeriesDetail(id.toString())
            if (result.isSuccessful) {
                result.body()?.let {
                    _selectedTvSeriesDetail.postValue(it)
                }
            }
        }
    }


}