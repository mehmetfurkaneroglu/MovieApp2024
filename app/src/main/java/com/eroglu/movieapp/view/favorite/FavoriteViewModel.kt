package com.eroglu.movieapp.view.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eroglu.movieapp.service.Repository
import com.eroglu.movieapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val selectedFavoriteItem = SingleLiveEvent<FavoriteModel>()




    val favoriteMovieAdapter = FavoriteAdapter().apply {
        itemClickedListener = object : ItemClickedListener{
            override fun onItemClicked(favoriteModel: FavoriteModel) {
                selectedFavoriteItem.postValue(favoriteModel)
            }
        }
    }

    val favoriteTvSeriesAdapter = FavoriteAdapter().apply {
        itemClickedListener = object : ItemClickedListener{
            override fun onItemClicked(favoriteModel: FavoriteModel) {
                selectedFavoriteItem.postValue(favoriteModel)
            }
        }
    }

    val adapter = MutableLiveData<FavoriteAdapter>(favoriteMovieAdapter)





}