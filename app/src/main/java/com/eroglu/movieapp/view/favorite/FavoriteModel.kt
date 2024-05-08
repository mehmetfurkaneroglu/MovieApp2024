package com.eroglu.movieapp.view.favorite

data class FavoriteModel(
    val itemId: String? = "",
    val itemName: String? = "",
    val itemPicture: String? = "",
    val itemType: FavoriteItemTypeEnum? = FavoriteItemTypeEnum.MOVIES
)

enum class FavoriteItemTypeEnum {
    MOVIES, TV_SERIES
}