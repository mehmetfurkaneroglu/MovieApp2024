package com.eroglu.movieapp.model

data class CommonModel(
    val itemId: Int? = 0,
    val itemName: String? = "",
    val itemPicture: String? = "",
    val itemImdb: Double? = 0.0,
    val itemType: FavoriteItemTypeEnum? = FavoriteItemTypeEnum.MOVIES
)

enum class FavoriteItemTypeEnum {
    MOVIES, TV_SERIES
}