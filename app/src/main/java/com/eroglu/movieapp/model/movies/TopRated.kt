package com.eroglu.movieapp.model.movies

import com.google.gson.annotations.SerializedName


data class TopRated(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<MovieResult?>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)