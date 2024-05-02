package com.eroglu.movieapp.view.favorite

data class FavoriteModel(
    val movieId: String? = "",
    val movieName: String? = "",
    val moviePicture: String? = "",
    val type: FavoriteItemTypeEnum? = FavoriteItemTypeEnum.MOVIES
)

enum class FavoriteItemTypeEnum{
    MOVIES,TV_SERIES
}