package com.eroglu.movieapp.model.tvSeries


import com.google.gson.annotations.SerializedName

data class TvSeriesPopular(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<TVSeriesResult>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)