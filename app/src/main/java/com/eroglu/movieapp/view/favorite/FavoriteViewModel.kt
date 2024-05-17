package com.eroglu.movieapp.view.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eroglu.movieapp.model.CommonModel
import com.eroglu.movieapp.service.Repository
import com.eroglu.movieapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val selectedFavoriteItem = SingleLiveEvent<CommonModel>()


    val favoriteMovieAdapter = FavoriteAdapter().apply {
        itemClickedListener = object : FavoriteItemClickedListener{
            override fun onItemClicked(commonModel: CommonModel) {
                selectedFavoriteItem.postValue(commonModel)
            }
        }
    }

    val favoriteTvSeriesAdapter = FavoriteAdapter().apply {
        itemClickedListener = object : FavoriteItemClickedListener{
            override fun onItemClicked(commonModel: CommonModel) {
                selectedFavoriteItem.postValue(commonModel)
            }
        }
    }

    val adapter = MutableLiveData<FavoriteAdapter>(favoriteMovieAdapter)
    val adapterMovie = MutableLiveData<FavoriteAdapter>(favoriteMovieAdapter)
    val adapterTv = MutableLiveData<FavoriteAdapter>(favoriteTvSeriesAdapter)





}