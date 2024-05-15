package com.eroglu.movieapp.view.tvSeries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eroglu.movieapp.model.tvSeries.TVSeriesResult
import com.eroglu.movieapp.service.Repository
import com.eroglu.movieapp.util.SingleLiveEvent
import com.eroglu.movieapp.view.detail.viewpager.ViewPagerItemClickedListenerTv
import com.eroglu.movieapp.view.detail.viewpager.ViewPagerTvAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var isPopularLoading = MutableLiveData(false)
    var isAiringTodayLoading = MutableLiveData(false)

    val selectedTvSeries = SingleLiveEvent<TVSeriesResult>()

    init {
        getPopularTvSeries()
        getAiringTodayTvSeries()
        getTopRatedTvSeries()
    }

    val popularTvSeriesAdapter = TvSeriesAdapter().apply {
        itemClickedListenerTv = object : ItemClickedListenerTv {
            override fun onItemClickedTv(item: TVSeriesResult) {
                selectedTvSeries.postValue(item)
            }
        }
    }

    val airingTodayTvSeriesAdapter = TvSeriesAdapter().apply {
        itemClickedListenerTv = object : ItemClickedListenerTv {
            override fun onItemClickedTv(item: TVSeriesResult) {
                selectedTvSeries.postValue(item)
            }
        }
    }

    val topRatedTvAdapter = ViewPagerTvAdapter().apply {
        itemClickedListenerTv = object : ViewPagerItemClickedListenerTv {
            override fun onItemClickedTv(item: TVSeriesResult) {
                selectedTvSeries.postValue(item)
            }
        }
    }

    private fun getPopularTvSeries() {
        viewModelScope.launch {
            isPopularLoading.value = true
            val result = repository.getPopularTvSeries()

            if (result.isSuccessful) {
                result.body()?.let {
                    it.results?.let {
                        popularTvSeriesAdapter.list = it
                    }
                }
            }
            isPopularLoading.value = false
        }
    }

    private fun getAiringTodayTvSeries() {
        viewModelScope.launch {
            isAiringTodayLoading.value = true
            val result = repository.getAiringTodayTvSeries()

            if (result.isSuccessful) {
                result.body()?.let {
                    it.results?.let {
                        airingTodayTvSeriesAdapter.list = it
                    }
                    isAiringTodayLoading.value = false
                }
            }
        }
    }

    private fun getTopRatedTvSeries(){
        viewModelScope.launch {
            val result = repository.getTopRatedTvSeries()
            if (result.isSuccessful) {
                result.body()?.let {
                    it.results?.let {
                        topRatedTvAdapter.viewPagerTvList = it
                    }
                }
            }
        }
    }


}